# Musify Android App

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
