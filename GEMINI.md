# Diary Application Project (Java Swing + MySQL)

## Overview
A beautiful, private diary application with a modern dark theme. Built using Java Swing and MySQL, featuring full data management (CRUD) and image support.

## Project Structure
- **`DiaryLogin.java`**: Simple password protection to keep your diary private. (Default: `1234`)
- **`DiaryDTO.java`**: Data Transfer Object for clean data management.
- **`DiaryDAO.java`**: Handles all MySQL operations (Insert, Update, Delete, Search).
- **`DiaryUI.java`**: The main interface with image preview and history list.

## Core Features
1. **Security**: Password-protected entry.
2. **Rich Content**: Title, multiline content, and image support.
3. **Image Preview**: Real-time thumbnail preview when selecting or viewing entries.
4. **History & Search**: Easily browse past entries or search by title keyword.
5. **Full CRUD**: Create new stories, update existing ones, or delete unwanted memories.

## Tech Stack
- **Language**: Java
- **UI**: Swing (Modern Dark Theme)
- **Database**: MySQL
- **Connectivity**: JDBC (MySQL Connector/J)

## Setup & Running
1. **Database Setup**:
   Execute the following in MySQL:
   ```sql
   CREATE DATABASE diary_db;
   USE diary_db;
   CREATE TABLE entries (
       id INT AUTO_INCREMENT PRIMARY KEY,
       entry_date DATE DEFAULT (CURRENT_DATE),
       title VARCHAR(255) NOT NULL,
       content TEXT,
       image_path VARCHAR(512)
   );
   ```
2. **JDBC Driver**:
   Add `mysql-connector-java.jar` to your project's build path/libraries.
3. **Configuration**:
   Open `src/diary/DiaryDAO.java` and update your MySQL `password`.
4. **Launch**:
   Run `src/diary/DiaryLogin.java` to start the application.

## Development Principles
- **High Cohesion**: Each file has a clear, single responsibility.
- **Decoupled Architecture**: UI doesn't contain SQL; DAO doesn't contain UI logic.
- **Simplicity**: Code remains easy to read and modify.
