# Flashcards

This is a Spring Boot application designed to facilitate the learning process through the creation and use of customizable flashcards. 

## Features

- JSP for views
- Tailwind for design
- Java for backend
- Ability to view/create/update/delete any collection/flashcard
- Everything is stored in MySQL
- Simple UI
- Good error handling and debugging
- Code very well structured and documented, easily scalable

## Prerequisites

- Java 17 or higher
- Maven
- MySQL

## Installation & Setup

Follow these steps to get the application up and running:

1. **Clone the repository**

    ```
    git clone https://github.com/ChristianDaher/flashcards.git
    ```

2. **Navigate to the project directory**

    ```
    cd flashcards
    ```

3. **Build the project**

    ```
    mvn clean install
    ```

4. **Create a MySQL database**

    ```
    CREATE DATABASE flashcards
    ```

5. **Update application.properties**

    Open the `application.properties` file and update the MySQL properties to match your environment.

6. **Run the application**

    ```
    mvn spring-boot:run
    ```

If all is working correctly, the database should now have a couple of tables and records inside each table.

The application should now be running on `localhost:8080` by default. Check the terminal logs to ensure there are no errors.