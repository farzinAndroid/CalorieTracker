CalorieTracker
Overview
Android application for tracking daily calorie intake and managing diet.
Built with multi-modular architecture and MVVM pattern.
Uses Retrofit for network operations and Room for local database management.
Features
User Authentication: Secure authentication using Firebase.
Calorie Tracking: Log daily calorie intake and view historical data.
Food Database: Search and add food items from a comprehensive database.
Custom Goals: Set and track custom calorie goals.
Offline Support: Access and log data without an internet connection.
Notifications: Receive reminders to log meals.
Architecture
Model: Data layer with Room database and Retrofit API service.
View: UI layer for displaying data and handling user interactions.
ViewModel: Bridge between Model and View, providing data to UI and handling user actions.
Modules
app: Main application module.
data: Data layer with Room database and Retrofit API service.
domain: Business logic and use cases.
presentation: UI layer with activities, fragments, and view models.
Technologies Used
Android Studio: IDE for Android development.
Kotlin: Programming language.
MVVM: Architecture pattern.
Retrofit: HTTP client for network operations.
Room: Persistence library for local database management.
Firebase: Backend services for authentication and real-time database.
Dagger-Hilt: Dependency injection framework.
Coroutines: For asynchronous programming.
