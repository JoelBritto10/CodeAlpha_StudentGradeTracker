# Student Grade Tracker - Complete Guide

A comprehensive Java and Python application for managing and tracking student grades with statistical analysis.

## 🎯 Now Available in Two Versions!

### Version 1: Console Application (Java)
- Traditional command-line interface
- Requires Java
- Run with: `java GradeTrackerApp`

### Version 2: Web Application (Python + Streamlit) ⭐ NEW
- Modern, interactive web interface
- Beautiful dashboards and charts
- Requires Python
- Run with: `streamlit run app.py`

---

## Features

- ✓ Add students with unique IDs
- ✓ Add, store, and manage multiple grades per student
- ✓ Calculate individual statistics: average, highest, and lowest scores
- ✓ Calculate class-wide statistics
- ✓ Display detailed student profiles
- ✓ Display comprehensive summary reports
- ✓ Grade distribution analysis
- ✓ Top and bottom performer identification
- ✓ Student search functionality
- ✓ Remove students from the system
- ✓ Interactive visualizations (Streamlit)
- ✓ Export to CSV (Streamlit)

## Project Structure

```
├── Student.java          - Represents a single student and their grades
├── GradeManager.java     - Manages all students and system-wide operations
├── GradeTrackerApp.java  - Main console interface and user interaction
├── app.py                - NEW: Streamlit web application
├── requirements.txt      - NEW: Python dependencies
├── run.bat               - Batch script to run console app
├── run.ps1               - PowerShell script to run console app
├── run_streamlit.bat     - NEW: Batch script to run web app
└── run_streamlit.ps1     - NEW: PowerShell script to run web app
```

---

## 🚀 QUICK START: Web Version (Streamlit) ⭐ RECOMMENDED

### Requirements: Python 3.8+

### Easiest Way to Run:
```powershell
cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"
run_streamlit.bat
```

This automatically installs dependencies and launches at http://localhost:8501

**Features:**
- 📊 Interactive Dashboard
- 📈 Beautiful Charts & Visualizations  
- 📋 Export to CSV
- 👥 Easy Student Management
- 📉 Grade Analysis

---

## Class Descriptions (Java Console Version)

### Student.java
- Stores individual student data (name, ID, grades)
- Methods for adding grades and calculating statistics
- Calculates letter grades (A, B, C, D, F)
- Grade validation (0-100 range)

### GradeManager.java
- Manages collection of students using ArrayList
- Provides class-wide statistical calculations
- Student search, add, and remove operations
- Grade distribution analysis

### GradeTrackerApp.java
- Console-based user interface
- Interactive menu system
- Input validation and error handling
- Formatted report generation

## How to Compile

### On Windows (Command Prompt):
```cmd
cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"
javac Student.java GradeManager.java GradeTrackerApp.java
```

### On Windows (PowerShell):
```powershell
cd "C:\Users\Ruchitha\OneDrive\Student grade tracker"
javac *.java
```

### On Mac/Linux:
```bash
cd "/path/to/Student grade tracker"
javac *.java
```

## How to Run

### On Windows:
```cmd
java GradeTrackerApp
```

### On All Platforms:
```bash
java GradeTrackerApp
```

## Usage Guide

### Main Menu Options:

1. **Add Student**
   - Enter student name and unique ID
   - Student is created and ready to receive grades

2. **Add Grade to Student**
   - Specify student by ID
   - Enter a grade (0-100)
   - Grade is stored and statistics are automatically updated

3. **View Student Details**
   - View individual student information
   - displays: name, ID, all grades, average, highest, lowest, letter grade

4. **Remove Student**
   - Remove a student from the system
   - Confirmation message provided

5. **Display All Students**
   - View a table of all students
   - Shows: ID, Name, Average, Highest Grade, Letter Grade

6. **Display Summary Report**
   - Comprehensive class statistics
   - Class average, highest/lowest averages
   - Top and bottom performers
   - Grade distribution (A, B, C, D, F counts)

7. **Search Student by ID**
   - Find and display specific student information

8. **Exit**
   - Close the application

## Example Workflow

```
1. Start the application: java GradeTrackerApp
2. Select "1" to add students
   - Add "Alice Smith" with ID "S001"
   - Add "Bob Johnson" with ID "S002"
   
3. Select "2" to add grades
   - Add grades 95, 87, 92 to S001
   - Add grades 78, 85, 88 to S002
   
4. Select "6" to view summary report
   - See class averages, top performers, grade distribution
   
5. Select "5" to see all students in a table format
```

## Technical Details

### Data Structures
- **ArrayList<Double>**: Stores grades for each student
- **ArrayList<Student>**: Stores all students in the system

### Validation
- Student IDs must be unique
- Grades must be between 0-100
- Name and ID fields cannot be empty
- Comprehensive error handling

### Statistics Calculated

**Per Student:**
- Average grade
- Highest grade
- Lowest grade
- Number of grades
- Letter grade (A-F)

**Class-Wide:**
- Class average
- Highest student average
- Lowest student average
- Highest individual grade
- Lowest individual grade
- Grade distribution counts

## Possible Extensions

- Export data to CSV/JSON
- Persist data to file storage
- GUI implementation with Swing/JavaFX
- Subject-specific grades
- Weighted grade calculations
- Grade history tracking
- Email notifications for grades

## Requirements

- Java 8 or higher
- Command line/Terminal

## Notes

- All grades are stored in memory (not persisted)
- The application can handle unlimited students
- Floating-point grades (e.g., 85.5) are supported
- Letter grades: A (90-100), B (80-89), C (70-79), D (60-69), F (0-59)
