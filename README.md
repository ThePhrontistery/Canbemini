# Canbemini 
Easily create Kanbans in a moment.

# **Table of Contents**

## Introduction:
Canbemini is an easy-to-use tool for creating Kanbans in a moment. With a simple user interface, you can quickly set up and configure your Kanbans for different process flows. Canbemini also allows for shared access and manipulation of notes by multiple users, as well as control of access and sharing with coworkers. In addition to these key features, Canbemini offers swimlanes for state management, attachments to notes, and shared roles such as owner, editor, and viewer. Try Canbemini now to streamline your workflows and improve collaboration with your team.

## Features

### Key Features
 * Easy creation and configuration of Kanbans with a simple user interface.
 * Shared access to Kanbans with other users and manipulation of notes (text cards) by all users.
 * Control of access to Kanbans and sharing with coworkers to work privately with a selected group of people.
 * Attachments to each note to add additional information.
 * Shared roles in Kanbans such as owner, editor, and viewer to control who can modify or delete notes and swimlanes.

### Additional Features
 * Different Kanbans for different process flows.
 * Swimlanes for state management and not for notes, to keep notes simple and text-based.
 * Option to view or download attachments by clicking on them.
 * Owner option to ensure that unwanted users cannot delete or modify notes/swimlanes if the link is shared.
 * Editor option to do the same as the owner, except delete the entire Kanban or change a user to owner.
 * viewer option to move swimlanes and notes.

 ## Prerequisites

 To run and use Canbemini, you will need the following:

A computer with a compatible operating system (such as Windows, macOS, or Linux).
A compatible version of the Java Development Kit (JDK) installed on your computer.
A compatible version of Gradle installed on your computer.
A compatible version of Node.js installed on your computer.

You will also need a web browser installed on your computer to access the application through the browser. It is recommended to use an up-to-date version of a popular web browser such as Google Chrome, Mozilla Firefox, or Safari.

## Requirements

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle 7.6](https://gradle.org/install/)
- [Node.js](https://nodejs.org/en/about/) --> [download node.js 18.12.1 LTS](https://nodejs.org/dist/v18.12.1/node-v18.12.1-x64.msi)

You will also need a web browser installed on your computer to access the application through the browser. It is recommended to use an up-to-date version of a popular web browser such as Google Chrome, Mozilla Firefox, or Safari.

## Working of the project

Canbemini is a web application built using a combination of Spring Boot and Angular. It uses a [H2 Data Base](https://www.h2database.com/html/main.html) to store data and [JPA](https://spring.io/projects/spring-data-jpa) for data persistence.

The application consists of a backend API built with [Spring Boot](http://projects.spring.io/spring-boot/) and a frontend built with [Angular](https://angular.io/guide/what-is-angular). The backend handles all the logic for storing and retrieving data from the database, while the frontend is responsible for displaying the data to the user and handling user interactions.

The frontend communicates with the backend through a series of RESTful API endpoints. When a user performs an action, such as creating a new Kanban or moving a note, the frontend sends a request to the appropriate API endpoint on the backend. The backend then performs the necessary logic and returns a response to the frontend, which is used to update the user interface.

The application also uses [Angular Material](https://material.angular.io/guide/getting-started) for its user interface components, such as buttons, forms, and tables. This helps to provide a consistent and visually appealing user experience.

In terms of security, the application uses [JSON Web Tokens (JWTs)](https://jwt.io/introduction) for authentication and authorization. When a user logs in, the backend generates a JWT and sends it back to the frontend, which is then stored in local storage. The frontend then sends this JWT with each subsequent request to the backend, which is used to verify the user's identity and authorize their actions. The JWT is stored in local storage instead of a cookie to prevent it from being accessed by third parties.

The application also has role-based access control, which allows the owner of a Kanban to invite collaborators and control their level of access. There are three roles: owner, editor, and collaborator. Owners have full control over the Kanban and can invite and remove collaborators, change their roles, and delete the Kanban itself. Editors have the same permissions as owners, except they cannot delete the Kanban or change the role of a user to owner. Collaborators can only move notes and swimlanes within the Kanban. This helps to ensure that only authorized users can make changes to the Kanban.

## Installation Guide

For building and running the application you need:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Gradle 7.6](https://gradle.org/install/)
- [Node.js](https://nodejs.org/en/about/) --> [download node.js 18.12.1 LTS](https://nodejs.org/dist/v18.12.1/node-v18.12.1-x64.msi)

### Running the application

To run the Canbemini application, follow these steps:

*  [Clone](https://github.com/ThePhrontistery/Canbemini.git) this repository onto your computer.
* Make sure you are using JDK 17 and Gradle 7.6.

- Open a terminal.
 * Install necessary dependencies: npm install
 * Run the project: ng serve
 * Open a web browser and go to http://localhost:4200 to see the application in action.

You can also run the Canbemini Spring Boot application as follows:

- Open a terminal in canbemini/api.
 * Run the following command: gradle bootRun. [More info about bootRun](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#running-your-application)
 * This will start the Spring Boot application and make it available at http://localhost:8080.


## Using the aplication

**To use the Canbemini application, follow these steps:**

* Open a web browser and go to http://localhost:4200.
* Click on the blue "Login" button located in the center of the screen.
* Enter one of the following emails and passwords to log in:
- **cesar@email.com**, password: 123
- **mercedes@email.com**, password: 123
- **jacques@email.com**, password: 123
- **fredy@email.com**, password: 123
- **raul@email.com**, password: 123

* Once you are logged in, you will see a list of your existing Kanbans. Click on one of them to open it.
* In the Kanban view, you can click and drag notes to move them between swimlanes and you can add new notes by clicking the "Add Note" button.
* You can also use the options in the top menu to add or delete swimlanes, change the Kanban's name, and invite collaborators.
* To attach a file to a note, click on the note and then click the "Add Attachment" button. You can either choose a file from your computer or enter a URL to attach it to the note.
* To view or download an attachment, click on it in the attachment list for the note.

<img width="517" alt="image" src="https://user-images.githubusercontent.com/102371536/210051462-a586f042-310c-4075-90a4-108f97d94aeb.png">

<img width="952" alt="image" src="https://user-images.githubusercontent.com/102371536/210051781-ecdf83ca-5d96-45c0-a979-c5ad53b4a0bc.png">

<img width="958" alt="image" src="https://user-images.githubusercontent.com/102371536/210052568-d769220b-7616-42a6-8b29-a8d5f1a23f1c.png">

## Contributing Guidelines

We welcome contributions to Canbemini! If you would like to report a bug or request a new feature, please open an issue on the [GitHub repository](https://github.com/ThePhrontistery/Canbemini).

If you would like to submit a bug fix or new feature, please follow these steps:

- Fork the repository and create a new branch for your changes.
- Make the necessary changes, including appropriate test cases.
- Update the documentation as needed.
- Submit a pull request for review.
- Please note that all contributions must follow our code of conduct and coding standards. We will review all pull requests and may request additional changes before merging.

#### Code of Conduct
We expect all contributors and users of Canbemini to treat each other with respect and kindness. Harassment of any kind will not be tolerated.

Please be considerate of others and their contributions to the project. Do not engage in any behavior that could be perceived as intimidating, threatening, or disrespectful.

We welcome contributions from people of all backgrounds and experiences. We encourage diversity and inclusivity and will make an effort to provide a safe and welcoming environment for everyone.

#### Coding Standards
We follow the [Angular Style Guide](https://angular.io/guide/styleguide) for Angular code, the Spring Boot Style Guide is currently in development . Please ensure that your code follows these guidelines before submitting a pull request.

## Known issues

Currently, it has been reported that the sharing option creates the share url, but the Kanban does not appear in the list of the person who logs in with that url.


## License
Canbemini is licensed under the Apache License 2.0. This license allows users to use the project for commercial purposes, modify it, distribute it, and use it privately, as long as the copyright is acknowledged and a copy of the license is included with the project. However, it limits the user's liability and does not offer any warranties.

For more information, please see the [Apache License 2.0.](https://www.apache.org/licenses/LICENSE-2.0)
