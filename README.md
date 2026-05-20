
# Library Management System

A comprehensive Java-based library management system with both command-line and graphical user interfaces.

## 📋 Project Overview

This project provides two implementations of a Library Management System:
- **CLI (Command-Line Interface)**: Menu-driven console application for managing a library
- **GUI (Graphical User Interface)**: Window-based application with an interactive form and table

Both versions manage the same library data and support the same core features.

## ✨ Features

### Core Operations
- **Add Book**: Create new book records with title, author, genre, and publication year
- **View All Books**: Display all books in the library with their details
- **Update Book**: Modify existing book information
- **Delete Book**: Remove books from the library
- **Search Books**: Find books by title or author
- **Sort Books**: Organize books by title, author, or year
- **Borrow/Return Books**: Track book availability status
- **Export Report**: Generate file output with library data

### Data Persistence
- All data is automatically saved to `library_data.txt`
- Books are loaded from the data file when the application starts
- Changes are persisted immediately after each operation

## 📁 Repository Structure

- `CLI/` - Command-line application
  - `src/` - Java source files (LibraryApp.java, Library.java, Book.java)
  - `data/` - Data and report files
  - `MANIFEST.MF` - Manifest for JAR compilation
  
- `GUI/` - Graphical user interface application
  - `src/` - Java source files (LibraryGUI.java, Library.java, Book.java)
  - `data/` - Data and report files
  - `MANIFEST.MF` - Manifest for JAR compilation

- `common/` - Shared classes
  - `Book.java` - Book data model
  - `Library.java` - Core library logic

- `build/` - Generated output files (JAR files, reports)

## 🚀 How to Run

### Prerequisites
- **Java 8 or later** installed
- **javac** (Java compiler) in PATH

### Option 1: Run CLI Application (Command-Line)

**Windows:**
```powershell
cd CLI/src
javac -cp . *.java
java -cp . LibraryApp
```

**Linux/Mac:**
```bash
cd CLI/src
javac -cp . *.java
java -cp . LibraryApp
```

**Using the compiled JAR (if available):**
```bash
java -cp CLI/bin CLI.LibraryApp
```

**What CLI does:**
- Displays an interactive menu in the console
- Prompts you to select operations (1-9)
- All input/output happens through the terminal
- Perfect for scripting or server environments

### Option 2: Run GUI Application (Graphical Interface)

**Windows:**
```powershell
cd GUI/src
javac -cp . *.java
java -cp . LibraryGUI
```

**Linux/Mac:**
```bash
cd GUI/src
javac -cp . *.java
java -cp . LibraryGUI
```

**Using the compiled JAR (if available):**
```bash
java -cp GUI/bin GUI.LibraryGUI
```

**What GUI does:**
- Opens a window with a user-friendly interface
- Input form at the top for book details
- Table display of all books in the library
- Click buttons to add, update, delete, search, or view books
- Easier to use for non-technical users


## 📊 Sample Data

Both applications automatically load 5 sample books on first run:
1. The Alchemist - Paulo Coelho (Fiction, 1988)
2. Atomic Habits - James Clear (Self-Help, 2018)
3. The Art of War - Sun Tzu (Philosophy, 1910)
4. Sapiens - Yuval Noah Harari (History, 2011)
5. Rich Dad Poor Dad - Robert Kiyosaki (Finance, 1997)

## 💾 Data Files

- `CLI/data/library_data.txt` - CLI application data storage
- `GUI/data/library_data.txt` - GUI application data storage
- `library_report_*.txt` - Auto-generated reports with timestamp

## 🛠️ Compilation Details

Both applications require:
- `Book.java` - Book model class
- `Library.java` - Core library operations
- `LibraryApp.java` (CLI) or `LibraryGUI.java` (GUI) - Main application class

All classes are compiled together and reference the common `library_data.txt` file.

## 📝 Git Upload Guidance

1. **Keep in repository:**
   - Source files in `CLI/src/`, `GUI/src/`, `common/`
   - `MANIFEST.MF` files
   - `data/library_data.txt` (initial data)
   - This `README.md`

2. **Ignore (in `.gitignore`):**
   - `build/` directory (compiled output)
   - Generated `.jar` files
   - `*.class` files
   - Report files with timestamps

## 🎯 Which Version to Use?

- **Use CLI** for: Server deployment, automated testing, command-line workflows
- **Use GUI** for: End-user applications, interactive data entry, visual management
