# Musify Android App

full log analyzed
```
Down 1
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0, clickCount=0, repeatCount=0 longPress=false
=== handleCallMediaButton: Headset Hook/Play/ Pause, clickCount=1
== clickTimer scheduled (1750367916680): clicks=1, hold=true ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0 ====

Up 1
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=1, repeatCount=0 longPress=false
=== clickTimer cancelled: clicks=1, hold=false,  id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
== clickTimer scheduled (1750367916680): clicks=1, hold=false ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====

Down 2
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0, clickCount=1, repeatCount=0 longPress=false
=== handleCallMediaButton: Headset Hook/Play/ Pause, clickCount=2
=== clickTimer cancelled: clicks=2, hold=true,  id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0 ====
== clickTimer scheduled (1750367916680): clicks=2, hold=true ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0 ====

Up 2
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=2, repeatCount=0 longPress=false
=== clickTimer cancelled: clicks=2, hold=false,  id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
== clickTimer scheduled (1750367916680): clicks=2, hold=false ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
!!! === clickTimer executed: clicks=2, hold=false, id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
!!! === handleClicks: count=2,hold=false

details:
    2025-06-19 23:18:52.365 28111-28111 === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=2, repeatCount=0 longPress=false
    2025-06-19 23:18:52.365 28111-28111 === clickTimer cancelled: clicks=2, hold=false,  id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
    2025-06-19 23:18:52.366 28111-28111 == clickTimer scheduled (1750367916680): clicks=2, hold=false ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
    2025-06-19 23:18:53.017 28111-28206 === clickTimer executed: clicks=2, hold=false, id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
    2025-06-19 23:18:53.017 28111-28206 === handleClicks: count=2,hold=false



Down and hold 3
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2, clickCount=0, repeatCount=2 longPress=false
=== handleCallMediaButton: Headset Hook/Play/ Pause, clickCount=1
== clickTimer scheduled (1750367916680): clicks=1, hold=true ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2 ====
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=3, clickCount=1, repeatCount=3 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=4, clickCount=1, repeatCount=4 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=5, clickCount=1, repeatCount=5 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=6, clickCount=1, repeatCount=6 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=7, clickCount=1, repeatCount=7 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=8, clickCount=1, repeatCount=8 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=9, clickCount=1, repeatCount=9 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=10, clickCount=1, repeatCount=10 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=11, clickCount=1, repeatCount=11 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=12, clickCount=1, repeatCount=12 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=13, clickCount=1, repeatCount=13 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=14, clickCount=1, repeatCount=14 longPress=false
=== clickTimer executed: clicks=1, hold=true, id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2 ====
=== handleClicks: count=1,hold=true
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=15, clickCount=0, repeatCount=15 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=16, clickCount=0, repeatCount=16 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=17, clickCount=0, repeatCount=17 longPress=false

Up 3
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=0, repeatCount=0 longPress=false
== clickTimer scheduled (1750367916680): clicks=0, hold=false ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
=== clickTimer executed: clicks=0, hold=false, id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
=== handleClicks: count=0,hold=false

```

full log
```
2025-06-19 23:18:52.106 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0, clickCount=0, repeatCount=0 longPress=false
2025-06-19 23:18:52.106 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === handleCallMediaButton: Headset Hook/Play/ Pause, clickCount=1
2025-06-19 23:18:52.106 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  == clickTimer scheduled (1750367916680): clicks=1, hold=true ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0 ====
2025-06-19 23:18:52.107 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=1, repeatCount=0 longPress=false
2025-06-19 23:18:52.107 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === clickTimer cancelled: clicks=1, hold=false,  id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
2025-06-19 23:18:52.108 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  == clickTimer scheduled (1750367916680): clicks=1, hold=false ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
2025-06-19 23:18:52.364 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0, clickCount=1, repeatCount=0 longPress=false
2025-06-19 23:18:52.364 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === handleCallMediaButton: Headset Hook/Play/ Pause, clickCount=2
2025-06-19 23:18:52.364 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === clickTimer cancelled: clicks=2, hold=true,  id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0 ====
2025-06-19 23:18:52.365 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  == clickTimer scheduled (1750367916680): clicks=2, hold=true ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0 ====
2025-06-19 23:18:52.365 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=2, repeatCount=0 longPress=false
2025-06-19 23:18:52.365 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === clickTimer cancelled: clicks=2, hold=false,  id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
2025-06-19 23:18:52.366 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  == clickTimer scheduled (1750367916680): clicks=2, hold=false ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
2025-06-19 23:18:53.017 28111-28206 MediaSessi...Callback() com.codewithfk.musify_android        D  === clickTimer executed: clicks=2, hold=false, id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
2025-06-19 23:18:53.017 28111-28206 MediaSessi...Callback() com.codewithfk.musify_android        D  === handleClicks: count=2,hold=false
2025-06-19 23:18:53.405 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2, clickCount=0, repeatCount=2 longPress=false
2025-06-19 23:18:53.405 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === handleCallMediaButton: Headset Hook/Play/ Pause, clickCount=1
2025-06-19 23:18:53.405 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  == clickTimer scheduled (1750367916680): clicks=1, hold=true ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2 ====
2025-06-19 23:18:53.456 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=3, clickCount=1, repeatCount=3 longPress=false
2025-06-19 23:18:53.507 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=4, clickCount=1, repeatCount=4 longPress=false
2025-06-19 23:18:53.560 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=5, clickCount=1, repeatCount=5 longPress=false
2025-06-19 23:18:53.608 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=6, clickCount=1, repeatCount=6 longPress=false
2025-06-19 23:18:53.663 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=7, clickCount=1, repeatCount=7 longPress=false
2025-06-19 23:18:53.710 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=8, clickCount=1, repeatCount=8 longPress=false
2025-06-19 23:18:53.763 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=9, clickCount=1, repeatCount=9 longPress=false
2025-06-19 23:18:53.817 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=10, clickCount=1, repeatCount=10 longPress=false
2025-06-19 23:18:53.868 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=11, clickCount=1, repeatCount=11 longPress=false
2025-06-19 23:18:53.918 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=12, clickCount=1, repeatCount=12 longPress=false
2025-06-19 23:18:53.970 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=13, clickCount=1, repeatCount=13 longPress=false
2025-06-19 23:18:54.017 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=14, clickCount=1, repeatCount=14 longPress=false
2025-06-19 23:18:54.056 28111-28206 MediaSessi...Callback() com.codewithfk.musify_android        D  === clickTimer executed: clicks=1, hold=true, id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2 ====
2025-06-19 23:18:54.056 28111-28206 MediaSessi...Callback() com.codewithfk.musify_android        D  === handleClicks: count=1,hold=true
2025-06-19 23:18:54.071 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=15, clickCount=0, repeatCount=15 longPress=false
2025-06-19 23:18:54.122 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=16, clickCount=0, repeatCount=16 longPress=false
2025-06-19 23:18:54.174 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=17, clickCount=0, repeatCount=17 longPress=false
2025-06-19 23:18:54.192 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  === debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=0, repeatCount=0 longPress=false
2025-06-19 23:18:54.192 28111-28111 MediaSessi...Callback() com.codewithfk.musify_android        D  == clickTimer scheduled (1750367916680): clicks=0, hold=false ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
2025-06-19 23:18:54.844 28111-28206 MediaSessi...Callback() com.codewithfk.musify_android        D  === clickTimer executed: clicks=0, hold=false, id=1750367916680 ==== keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0 ====
2025-06-19 23:18:54.844 28111-28206 MediaSessi...Callback() com.codewithfk.musify_android        D  === handleClicks: count=0,hold=false

```


```
# event phase of a 2 click, long press handling
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0, clickCount=0, repeatCount=0 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=1, repeatCount=0 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=0, clickCount=1, repeatCount=0 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=2, repeatCount=0 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=2, clickCount=0, repeatCount=2 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=3, clickCount=1, repeatCount=3 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=4, clickCount=1, repeatCount=4 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=5, clickCount=1, repeatCount=5 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=6, clickCount=1, repeatCount=6 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=7, clickCount=1, repeatCount=7 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=8, clickCount=1, repeatCount=8 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=9, clickCount=1, repeatCount=9 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=10, clickCount=1, repeatCount=10 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=11, clickCount=1, repeatCount=11 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=12, clickCount=1, repeatCount=12 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=13, clickCount=1, repeatCount=13 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=14, clickCount=1, repeatCount=14 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=15, clickCount=0, repeatCount=15 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=16, clickCount=0, repeatCount=16 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_DOWN, repeatCount=17, clickCount=0, repeatCount=17 longPress=false
=== debounceKeyEvent: keyCode=KEYCODE_HEADSETHOOK, action=ACTION_UP, repeatCount=0, clickCount=0, repeatCount=0 longPress=false

```


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
