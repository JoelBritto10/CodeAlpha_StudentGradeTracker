# Architecture and Design Documentation

## Project Overview

The Student Grade Tracker is a command-line Java application designed following the Model-View-Controller (MVC) pattern with clear separation of concerns.

---

## Architecture Diagram

```
┌─────────────────────────────────────────────────┐
│          GradeTrackerApp (UI/Controller)         │
│  - User Interface (Console-based)               │
│  - Input validation                             │
│  - Menu management                              │
└──────────────────────┬──────────────────────────┘
                       │ uses
                       ▼
┌─────────────────────────────────────────────────┐
│           GradeManager (Service)                │
│  - Student collection management                │
│  - System-wide statistics                       │
│  - Search and filtering logic                   │
└──────────────────────┬──────────────────────────┘
                       │ contains many
                       ▼
┌─────────────────────────────────────────────────┐
│              Student (Model)                    │
│  - Student data (name, ID)                     │
│  - Individual grade storage                     │
│  - Personal statistics                          │
└─────────────────────────────────────────────────┘
```

---

## Class Relationships

### Student Class
**Role:** Data Model
**Responsibilities:**
- Store student information (name, ID)
- Manage grades collection (ArrayList)
- Calculate personal statistics (average, min, max)
- Assign letter grades

**Key Attributes:**
```java
- name: String
- id: String
- grades: ArrayList<Double>
```

**Key Methods:**
```java
+ addGrade(grade: double): void
+ getAverageGrade(): double
+ getHighestGrade(): double
+ getLowestGrade(): double
+ getLetterGrade(): String
+ getGrades(): ArrayList<Double>
```

---

### GradeManager Class
**Role:** Service/Business Logic
**Responsibilities:**
- Manage collection of Student objects
- Provide CRUD operations (Create, Read, Update, Delete students)
- Calculate class-wide statistics
- Perform student searches

**Key Attributes:**
```java
- students: ArrayList<Student>
```

**Key Methods:**
```java
+ addStudent(name: String, id: String): boolean
+ findStudent(id: String): Student
+ removeStudent(id: String): boolean
+ getClassAverage(): double
+ getHighestAverage(): double
+ getLowestAverage(): double
+ getTopStudent(): Student
+ getBottomStudent(): Student
+ countByGrade(letterGrade: String): int
```

**Design Pattern Used:** 
- Facade Pattern (provides simplified interface to complex operations)

---

### GradeTrackerApp Class
**Role:** View/Controller
**Responsibilities:**
- Display user interface menus
- Handle user input
- Validate input
- Call appropriate GradeManager methods
- Format and display results

**Key Methods:**
```java
+ displayMainMenu(): void
+ addStudent(): void
+ addGrade(): void
+ viewStudentDetails(): void
+ displayAllStudents(): void
+ displaySummaryReport(): void
+ searchStudent(): void
+ run(): void
+ main(args: String[]): void
```

---

## Data Flow Diagram

```
User Input (Console)
        │
        ▼
GradeTrackerApp (Validation & Formatting)
        │
        ▼
GradeManager (Business Logic)
        │
        ▼
Student (Data Model / Calculations)
        │
        ▼
Output (Formatted Reports)
```

---

## Key Design Principles

### 1. Separation of Concerns
- **Student**: Handles only student data
- **GradeManager**: Handles collection management
- **GradeTrackerApp**: Handles UI/UX

### 2. Encapsulation
- Private attributes with public getters/setters
- Data validation in methods
- Clear public API

### 3. Reusability
- GradeManager can be used by different UIs
- Student class is independent
- Methods are focused and single-purpose

### 4. Error Handling
- Input validation at all levels
- Meaningful error messages
- Graceful failure recovery

### 5. ArrayList Usage
- Dynamic sizing for flexibility
- No arbitrary limits on students or grades
- Efficient for iteration and search

---

## Statistics Calculation Methods

### Per-Student Statistics
```
Average = Sum of all grades / Number of grades
Highest = Maximum grade in collection
Lowest = Minimum grade in collection
Letter Grade = Based on average:
    - A: >= 90
    - B: >= 80 and < 90
    - C: >= 70 and < 80
    - D: >= 60 and < 70
    - F: < 60
```

### Class-Wide Statistics
```
Class Average = Sum of all student averages / Number of students
Highest Average = Max of all student averages
Lowest Average = Min of all student averages
Highest Grade Overall = Max grade across all students
Lowest Grade Overall = Min grade across all students
Grade Distribution = Count of students with each letter grade
```

---

## Data Structure Analysis

### Time Complexity

| Operation | Complexity | Notes |
|-----------|-----------|-------|
| Add Student | O(n) | Linear search for duplicate ID |
| Find Student | O(n) | Linear search through students |
| Remove Student | O(n) | Linear search and removal |
| Add Grade | O(1) | Direct ArrayList addition |
| Calculate Average | O(m) | m = number of grades |
| Calculate Class Avg | O(n) | n = number of students |
| Display All | O(n*m) | All students and their grades |

### Space Complexity
```
O(n*m) where:
  n = number of students
  m = average number of grades per student
```

---

## Possible Enhancements

### Performance Optimizations
- Use HashMap for O(1) student lookup by ID
- Cache calculated averages
- Implement sorting algorithms

### Feature Additions
- Subject/course-specific grades
- Weighted grade calculations
- Grade history/tracking
- Attendance tracking
- File persistence (save/load)
- Export to CSV/PDF

### UI Enhancements
- GUI using Swing or JavaFX
- Web interface using Spring MVC
- Mobile app API

### Data Management
- Database integration (SQL)
- Authentication and authorization
- Multi-user support
- Bulk grade import

---

## Testing Considerations

### Unit Tests (Recommended)
```java
// Test Student class
- testAddValidGrade()
- testAddInvalidGrade()
- testCalculateAverage()
- testLetterGradeAssignment()

// Test GradeManager class
- testAddStudent()
- testDuplicateStudentHandling()
- testFindStudent()
- testClassAverageCalculation()

// Integration tests
- testCompleteWorkflow()
- testErrorRecovery()
```

---

## Deployment

### Single File Distribution
- All .java source files
- README.md for instructions
- run.bat / run.ps1 for easy launching

### Compilation
```bash
javac Student.java GradeManager.java GradeTrackerApp.java
```

### Execution
```bash
java GradeTrackerApp
```

---

## Code Quality Metrics

- **Cyclomatic Complexity**: Low (mostly linear logic)
- **Code Duplication**: Minimal (some repeated patterns in statistics)
- **Coupling**: Loose (GradeTrackerApp depends on GradeManager)
- **Cohesion**: High (each class has clear single responsibility)

---

## Maintenance Guidelines

1. **Adding new features**: Extend GradeManager for logic, GradeTrackerApp for UI
2. **Changing Student data**: Update Student class and adjust GradeManager methods
3. **Refactoring**: Maintain ArrayList usage, avoid breaking public APIs
4. **Testing**: Add test cases before major changes
