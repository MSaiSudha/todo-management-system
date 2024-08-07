Todo Management System - Frontend Application

Project Setup

1. Prerequisites
    Node.js: Ensure you have Node.js installed. You can download it from Node.js website.
    VS Code: This project uses Visual Studio Code as the preferred IDE.

2. Project Setup in VS Code
    Open the Project Folder:
    Open Visual Studio Code.
    Go to File > Open Folder....
    Navigate to the frontend directory of your cloned repository and select the todo-app folder.

3. Install Dependencies:
    Open a terminal in VS Code.

    Run the following command to install the required dependencies specified in package.json:
        npm install
4. Run the Project:
    After the dependencies are installed, run the following command to start the development server:
    npm start
    This will start the React application and open it in your default web browser at http://localhost:3000.

5. Project Structure
    src: Contains the source code for the React application.
        components: Contains the React components.
        assets: Contains images.
        styles: Contains external css files realated to components.
    App.js: The main application component.
    index.js: The entry point of the React application.

6. Endpoints
    The frontend application communicates with the backend RESTful API endpoints:

7. Scripts
    The following scripts are available in the package.json:
    npm start: Starts the development server.
    npm build: Builds the application for production.

Ensure the backend server is running on http://localhost:8080 for the frontend application to interact with it correctly.