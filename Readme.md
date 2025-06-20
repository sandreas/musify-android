# Musify Android App

## Todo / Issues

### Introduction
On some devices the headset button has a delay of `1000ms` when the button is held / pressed down for longer.

The pattern `ACTION_DOWN / ACTION_UP / ACTION_DOWN+hold` produces the following event log on an Android 13 device (Pixel 4a, GrapheneOS):

```
2025-06-20 09:06:21.635 12076-12076 === keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0
2025-06-20 09:06:21.637 12076-12076 === keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0
# > 1 second event delay on hold key????
2025-06-20 09:06:22.642 12076-12076 === keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2
2025-06-20 09:06:22.698 12076-12076 === keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=3
2025-06-20 09:06:22.748 12076-12076 === keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=4
2025-06-20 09:06:22.800 12076-12076 === keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=5
```

This shows that holding the headset button down produces a delay of `1000ms` followed by a `ACTION_DOWN` with a `repeatCount=2` skipping the `repeatCount=1` event

### The problem
The problem with this is, that I developed a pattern-recognition algorithm to react to these specific tap codes:

- `TAP` - play/pause
- `TAP-TAP` - next (chapter or track)
- `TAP-TAP-TAP` - prev (chapter or track)
- `TAP+hold` - go back 30 seconds
- `TAP-TAP+hold` - fast-forward
- `TAP-TAP-TAP+hold` - rewind

The algorithm is based on accurate timing, where the desired action is executed after `650ms` of receiving no further events,
which is (in my opinion) the perfect timing to execute fast with enough flexibility. However, this
is no longer possible with a delay of `1000ms`, because the delayed action is getting executed too early.
The `TAP+hold` patterns are getting messed up due to this.

### Possible solutions
- Merge events (e.g. detect double tap, perform action "next", then detect long press and undo "next" and perform rewind)
- Workaround the 1000ms delay and get every event in onMediaButton
- A totally custom `MediaButtonReceiver` handling everything on my own (is that possible)?


## Custom media button receiver
```xml
<receiver android:name=".MediaButtonReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter android:priority="999">
        <action android:name="android.intent.action.MEDIA_BUTTON" />
    </intent-filter>
</receiver>
```

```kotlin
class MediaButtonReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        if (intent.action == Intent.ACTION_MEDIA_BUTTON) {
            val event = intent.getParcelableExtra<KeyEvent>(Intent.EXTRA_KEY_EVENT)
            event?.let {
                when (it.action) {
                    KeyEvent.ACTION_DOWN -> {
                        // Handle the key press here immediately
                        // For example, trigger the media action
                        handleMediaButton(it.keyCode)
                    }
                }
                // Prevent further processing by the system
                abortBroadcast()
            }
        }
    }

    private fun handleMediaButton(keyCode: Int) {
        // Your logic here, like playing/pausing, next track, etc.
    }
}
```

## Description

Musify Android App is a sample Android application built to demonstrate modern Android development practices. It leverages the MVVM architectural pattern with a repository pattern, Jetpack Compose for building the UI, and Compose Navigation for handling app navigation. The project is organized in a modular way to ensure scalability and ease of maintenance.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Overview

Musify Android App serves as a tutorial-style project to help you understand and implement key Android development components such as:
- **MVVM Architecture**: Separation of concerns using ViewModels, state management, and the Repository pattern.
- **Jetpack Compose**: Modern UI development with declarative components.
- **Navigation Compose**: Seamless, programmatic navigation between different screens.

## Features

- **MVVM Architecture**: Organized code structure that separates data, UI.
- **Modern UI**: Built entirely using Jetpack Compose.
- **Asynchronous Data Loading**: Utilizes Kotlin Coroutines and StateFlow to handle asynchronous operations.
- **Modular Navigation**: Easy-to-understand navigation with Compose Navigation.
- **Dependency Injection**: Example module provided in `di/NetworkModule.kt` for setting up network-related dependencies.

## Project Structure

```
app/
└── src/
    └── main/
        ├── java/com/codewithfk/musify_android/
        │   ├── data/
        │   │   ├── model/
        │   │   │   └── Model.kt          // Data model
        │   │   ├── network/
        │   │   │   └── ApiService.kt     // API interface (e.g., Retrofit)
        │   │   └── repository/
        │   │       └── Repository.kt     // Data operations and network call management
        │   ├── di/
        │   │   └── NetworkModule.kt      // Dependency Injection module for network related classes
        │   ├── ui/
        │   │   ├── feature/
        │   │   │   └── home/
        │   │   │       ├── HomeScreen.kt    // Composable for the Home screen
        │   │   │       ├── HomeViewModel.kt // ViewModel for home screen logic
        │   │   │       ├── HomeState.kt     // UI state definitions
        │   │   │       └── HomeEvent.kt     // Navigation/one-time UI events
        │   │   ├── navigation/
        │   │   │   └── NavGraph.kt        // Navigation graph using Compose Navigation
        │   │   └── MainActivity.kt        // Single Activity hosting the Compose UI
        │   └── utils/
        │       └── (Helper classes and utilities can be added here)
        └── res/
            └── values/
                └── themes.xml           // Theme definitions
```

## Architecture

- **Data Layer**:
    - `data/model/Model.kt`: Defines the data models.
    - `data/network/ApiService.kt`: Contains the Retrofit (or similar) API interface for network requests.
    - `data/repository/Repository.kt`: Acts as the single source of truth for data, handling network requests and data processing.

- **Dependency Injection**:
    - `di/NetworkModule.kt`: Provides network-related dependencies. This can be extended using DI frameworks such as Hilt or Dagger.

- **UI Layer**:
    - **Feature Module (Home)**:
        - `ui/feature/home/HomeScreen.kt`: Composable that renders the Home screen.
        - `ui/feature/home/HomeViewModel.kt`: Manages UI logic and state for the Home screen.
        - `ui/feature/home/HomeState.kt`: Defines various UI states (e.g., loading, success, error).
        - `ui/feature/home/HomeEvent.kt`: Defines navigation or one-time UI events.
    - **Navigation**:
        - `ui/navigation/NavGraph.kt`: Manages navigation using Compose Navigation.
    - **MainActivity**:
        - `ui/MainActivity.kt`: The single Activity that sets the Compose UI content and hosts the navigation graph.

- **Theme & Resources**:
    - `res/values/themes.xml`: Contains theme and style definitions used across the application.

## Setup and Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/musify-android.git
   cd musify-android
   ```

2. **Open in Android Studio**
    - Launch Android Studio.
    - Choose "Open an Existing Project" and select the project directory.

3. **Configure Dependencies**
    - Ensure you have the latest version of Android Studio (Arctic Fox or later recommended) that supports Jetpack Compose.
    - Sync the project with the Gradle files. The project uses dependencies for Compose, Retrofit, and other Jetpack libraries.

4. **Run the App**
    - Select an emulator or connect a physical device.
    - Click the "Run" button in Android Studio to build and run the project.

## Usage

- **Exploring the Code**:  
  The project is organized by features and layers, making it simple to locate files related to data, UI, navigation, and dependency injection.

- **Extending Functionality**:  
  You can add additional features in the `ui/feature/` directory, create new data models, and update the navigation graph accordingly.

- **Navigation**:  
  Navigation is managed in `NavGraph.kt` via Compose Navigation, allowing you to manage routes and transitions between screens easily.
