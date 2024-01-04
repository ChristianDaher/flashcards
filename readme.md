# Flashcards

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Introduction](#introduction)
- [Technology Stack](#technology-stack)
- [Features](#features)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [Future Improvements & Fixes](#future-improvements--fixes)

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

## Introduction

Flashcards is a dynamic web application designed to enhance the learning experience by enabling the creation and utilization of customizable flashcards. These flashcards can be organized into collections and categories, providing a structured approach to learning.

This application serves as a versatile tool for self-assessment, allowing users to create flashcards on any topic of their choice. It's not just limited to academic learning; it can be used for a wide range of subjects, from language learning to professional skill development.

The user-friendly interface ensures a seamless experience, making it easy for anyone to create, manage, and review flashcards. With Flashcards, you can transform the way you learn and make the process more engaging and effective.

### Why flashcards?

Research consistently underscores the efficacy of flashcards as a learning tool. Studies have shown that active recall, a key component of flashcard use, enhances long-term retention. In a seminal study by Roediger and Karpicke (2006), it was demonstrated that the retrieval practice involved in flashcard use significantly improves memory recall compared to passive review methods.

> "Taking a memory test not only assesses what one knows, but also enhances later retention, a phenomenon known as the testing effect. We studied this effect with educationally relevant materials and investigated whether testing facilitates learning only because tests offer an opportunity to restudy material. In two experiments, students studied prose passages and took one or three immediate free-recall tests, without feedback, or restudied the material the same number of times as the students who received tests. Students then took a final retention test 5 min, 2 days, or 1 week later. When the final test was given after 5 min, repeated studying improved recall relative to repeated testing. However, on the delayed tests, prior testing produced substantially greater retention than studying, even though repeated studying increased students' confidence in their ability to remember the material. Testing is a powerful means of improving learning, not just assessing it." - [Roediger and Karpicke (2006)](https://pubmed.ncbi.nlm.nih.gov/16507066/)

Moreover, a meta-analysis by Dunlosky et al. (2013) found that self-testing, a technique inherent in flashcard usage, is one of the most effective strategies for improving long-term retention of information. The process of actively engaging with flashcards stimulates the brain, promoting deeper understanding and knowledge retention.

> "Note that we use the term practice testing here (a) to distinguish testing that is completed as a low-stakes or no-stakes practice or learning activity outside of class from summative assessments that are administered by an instructor in class, and (b) to encompass any form of practice testing that students would be able to engage in on their own. For example, practice testing could involve practicing recall of target information via the use of actual or virtual flashcards, completing practice problems or questions included at the end of textbook chapters, or completing practice tests included in the electronic supplemental materials that increasingly accompany textbooks." - [Dunlosky et al. (2013)](https://pcl.sitehost.iu.edu/rgoldsto/courses/dunloskyimprovinglearning.pdf)

## Technology Stack

- <span style="color: #69B0FF">**Backend**</span>: [Spring Boot](https://spring.io/projects/spring-boot) - A powerful framework that simplifies the setup and development of Spring applications. It provides default configurations and a stand-alone environment to run applications.

- <span style="color: #69B0FF">**Frontend Templates**</span>: [JSP](https://docs.oracle.com/javaee/5/tutorial/doc/bnagx.html) - JavaServer Pages (JSP) is a technology used for building dynamic web pages based on HTML, XML, or other document types. It is used in this project to manage and render the views.

- <span style="color: #69B0FF">**Frontend Design**</span>: [Tailwind CSS](https://tailwindcss.com/) - A utility-first CSS framework that is highly customizable and allows for building modern and responsive designs. It is used in this project for styling the frontend.

- <span style="color: #69B0FF">**Database**</span>: [MySQL](https://www.mysql.com/) - An open-source relational database management system. In this project, it is used for data persistence and to perform CRUD operations.

- <span style="color: #69B0FF">**Version Control**</span>: [Git](https://git-scm.com/) / [GitHub](https://github.com/) - Git is a distributed version control system that tracks changes to source code. GitHub is a cloud-based hosting service for Git repositories. In this project, they are used for version control and source code management.

- <span style="color: #69B0FF">**Build Tool**</span>: [Maven](https://maven.apache.org/) - A build automation tool used primarily for Java projects. In this project, it is used to manage dependencies and build the project.

## Features

- <span style="color: #69B0FF">**Data Management**</span>: CRUD (Create, Read, Update, Delete) operations for managing data in the MySQL database. You can do all these actions for both Models (Collection & Flashcard)

## Usage

## Screenshots

## Future Improvements & Fixes

- <span style="color: #69B0FF">**Light/Dark Mode Support**</span>: Plan to implement a feature that allows users to toggle between light and dark themes. This will enhance the user experience, especially for those who prefer a darker interface during night-time usage.

- <span style="color: #69B0FF">**User Authentication**</span>: Intend to add a secure login and registration system to safeguard user data and provide personalized experiences.

- <span style="color: #69B0FF">**Authorization**</span>: After implementing user registration, we will ensure that users can only update, edit, or delete their own collections and flashcards, preventing unauthorized access and modification of other users' data.

- <span style="color: #69B0FF">**Flashcard Sharing**</span>: Aim to develop a feature enabling users to view and learn from flashcards created by others, fostering a collaborative learning environment.

- <span style="color: #69B0FF">**Error Handling**</span>: Plan to improve the error handling system. Currently, all errors redirect to a generic error page. The goal is to provide more specific error messages, especially when a user answers the last flashcard in a collection, to enhance user experience, understanding and troubleshooting.

- <span style="color: #69B0FF">**Enhanced Animations**</span>: Plan to incorporate more animations throughout the application to boost user engagement and improve the overall aesthetic. For instance, an animation could be added when the user clicks the "Show Answer" button while playing a collection.
