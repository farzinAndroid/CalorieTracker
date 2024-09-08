## CalorieTracker
Overview
CalorieTracker is an Android application designed to help users track their daily calorie intake and manage their diet effectively. The app is built using a multi-modular architecture with MVVM (Model-View-ViewModel) pattern, ensuring a clean separation of concerns and maintainability. It leverages Retrofit for network operations and Room for local database management.


### Features
- Calorie Tracking: Track how much calories you should it based on your gender - age and activity level.
- Custom Goals: Set and track custom calorie goals.
- Set Intake Ratio: Set your intake ration (fat - carbs - protein).



### Architecture
The project follows the MVVM architecture pattern, which helps in separating the UI logic from the business logic, making the codebase more modular and testable. The architecture includes:

- Model: Represents the data layer, including the Room database and Retrofit API service.
- View: Represents the UI layer, which displays data to the user and handles user interactions.
- ViewModel: Acts as a bridge between the Model and View, providing data to the UI and handling user actions.


### Modules
The project is divided into multiple modules to enhance modularity and reusability.It combines both Modularization by Feature and Modularization by Layer.



### Technologies Used

- Kotlin: Programming language used for the development.
- MVVM: Architecture pattern for separation of concerns.
- Retrofit: HTTP client for network operations.
- Room: Persistence library for local database management.
- Dagger-Hilt: Dependency injection framework.
- Coroutines: For asynchronous programming.
- Unit Test : For logic tests.
- Mockito : For UI testing


![1](https://github.com/user-attachments/assets/c092bfc2-82c5-4bb2-a843-500a97ba82d7)


![2](https://github.com/user-attachments/assets/139f3a9c-64a4-4405-8bdb-4f2dfe6884de)




