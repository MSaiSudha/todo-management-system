Todo Management System - Backend Application

Project Setup

1. Prerequisites
    Java Development Kit (JDK) 8 or higher: Ensure you have JDK installed. You can download it from Oracle's website or use a package manager.
    Spring Boot: This project is built using Spring Boot.
    MySQL: The application uses MySQL as the database.

2. IDE Setup
    You can use either Eclipse or IntelliJ IDEA to set up the project. Follow the steps below to import and run the backend application.

    2.1 Eclipse Setup
        Open Eclipse.
        Go to File > Import.
        Select Maven > Existing Maven Projects and click Next.
        Browse to the directory where you cloned the repository and select the backend folder.
        Click Finish to import the project.

    2.2 IntelliJ IDEA Setup
        Open IntelliJ IDEA.
        Go to File > New > Project from Existing Sources.
        Browse to the directory where you cloned the repository and select the backend folder.
        Choose Import project from external model and select Maven.
        Click Next and follow the prompts to complete the import.

3. MySQL Setup
    Open MySQL Workbench.
    Connect to your MySQL server.
    Execute the following command to create the database:
    sql command:
        CREATE DATABASE todo_management_db;

4. Application Configuration
    Open the src/main/resources/application.properties file.

    Update the following properties with your MySQL credentials:
        spring.datasource.url=jdbc:mysql://localhost:3306/todo_management_db
        spring.datasource.username=your_username
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect

5. Running the Application
    In your IDE, locate the main class file TodoManagementSystemApplication.java (usually found in the src/main/java directory).
    Right-click the file and select Run or use the Run button in the toolbar to start the application.
    
   
Ensure the backend server is running on http://localhost:8080.