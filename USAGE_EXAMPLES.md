# Sample Usage Examples

This document shows examples of how to use the Student Grade Tracker application.

## Example 1: Basic Workflow

### Step 1: Start the Application
```
java GradeTrackerApp
```

### Step 2: Add Students (Menu Option 1)
```
Enter your choice (1-8): 1

--- Add New Student ---
Enter Student Name: Alice Smith
Enter Student ID: S001
✓ Student added successfully!

Enter your choice (1-8): 1
--- Add New Student ---
Enter Student Name: Bob Johnson
Enter Student ID: S002
✓ Student added successfully!

Enter your choice (1-8): 1
--- Add New Student ---
Enter Student Name: Carol Davis
Enter Student ID: S003
✓ Student added successfully!
```

### Step 3: Add Grades (Menu Option 2)
```
Enter your choice (1-8): 2

--- Add Grade to Student ---
Enter Student ID: S001
Enter Grade (0-100): 95
✓ Grade added successfully!

--- Add Grade to Student ---
Enter Student ID: S001
Enter Grade (0-100): 87
✓ Grade added successfully!

--- Add Grade to Student ---
Enter Student ID: S001
Enter Grade (0-100): 92
✓ Grade added successfully!

--- Add Grade to Student ---
Enter Student ID: S002
Enter Grade (0-100): 78
✓ Grade added successfully!

--- Add Grade to Student ---
Enter Student ID: S002
Enter Grade (0-100): 85
✓ Grade added successfully!

--- Add Grade to Student ---
Enter Student ID: S003
Enter Grade (0-100): 88
✓ Grade added successfully!

--- Add Grade to Student ---
Enter Student ID: S003
Enter Grade (0-100): 91
✓ Grade added successfully!
```

### Step 4: View Individual Student Details (Menu Option 3)
```
Enter your choice (1-8): 3

--- View Student Details ---
Enter Student ID: S001

--------------------------------------------------
Student Information
--------------------------------------------------
Name: Alice Smith
ID: S001
Total Grades: 3

Grades: [95.0, 87.0, 92.0]
Average Grade: 91.33
Highest Grade: 95.00
Lowest Grade: 87.00
Letter Grade: A
--------------------------------------------------
```

### Step 5: Display All Students (Menu Option 5)
```
Enter your choice (1-8): 5

--- All Students ---
--------------------------------------------------------------------------------
ID         | Name                 | Average         | Highest    | Grade
--------------------------------------------------------------------------------
S001       | Alice Smith          | 91.33            | 95.00      | A
S002       | Bob Johnson          | 81.50            | 85.00      | B
S003       | Carol Davis          | 89.50            | 91.00      | B
--------------------------------------------------------------------------------
```

### Step 6: View Summary Report (Menu Option 6)
```
Enter your choice (1-8): 6

==================================================
           CLASS SUMMARY REPORT
==================================================

Overall Statistics:
--------------------------------------------------
Total Students: 3
Class Average: 87.44
Highest Average: 91.33
Lowest Average: 81.50
Highest Grade (Overall): 95.00
Lowest Grade (Overall): 78.00

Top Performers:
--------------------------------------------------
Top Student: Alice Smith (ID: S001)
Average: 91.33 | Grade: A

Bottom Performers:
--------------------------------------------------
Bottom Student: Bob Johnson (ID: S002)
Average: 81.50 | Grade: B

Grade Distribution:
--------------------------------------------------
A Grade (90-100): 1 students
B Grade (80-89):  2 students
C Grade (70-79):  0 students
D Grade (60-69):  0 students
F Grade (0-59):   0 students
==================================================
```

---

## Example 2: Search and View Specific Student (Menu Option 7)
```
Enter your choice (1-8): 7

--- Search Student ---
Enter Student ID: S002

--------------------------------------------------
Student Information
--------------------------------------------------
Name: Bob Johnson
ID: S002
Total Grades: 2

Grades: [78.0, 85.0]
Average Grade: 81.50
Highest Grade: 85.00
Lowest Grade: 78.00
Letter Grade: B
--------------------------------------------------
```

---

## Example 3: Remove a Student (Menu Option 4)
```
Enter your choice (1-8): 4

--- Remove Student ---
Enter Student ID: S003
✓ Student removed successfully!
```

---

## Example 4: Error Handling

### Attempting to add duplicate student ID:
```
Enter your choice (1-8): 1
--- Add New Student ---
Enter Student Name: David Lee
Enter Student ID: S001
✗ Error: A student with this ID already exists!
```

### Attempting to add invalid grade:
```
Enter your choice (1-8): 2
--- Add Grade to Student ---
Enter Student ID: S001
Enter Grade (0-100): 150
✗ Error: Grade must be between 0 and 100!
```

### Attempting to find non-existent student:
```
Enter your choice (1-8): 3
--- View Student Details ---
Enter Student ID: S999
✗ Error: Student not found!
```

---

## Example 5: Working with Decimal Grades
```
Enter your choice (1-8): 2
--- Add Grade to Student ---
Enter Student ID: S001
Enter Grade (0-100): 87.5
✓ Grade added successfully!
```

---

## Typical Workflow Summary

1. **Start app** → `java GradeTrackerApp`
2. **Add students** → Menu option 1
3. **Add their grades** → Menu option 2 (multiple grades per student)
4. **Check individual details** → Menu option 3
5. **View all students** → Menu option 5
6. **Review class report** → Menu option 6
7. **Search specific student** → Menu option 7
8. **Exit** → Menu option 8

---

## Tips and Tricks

- Each student can have **unlimited grades** added
- **Average automatically recalculates** when you add a new grade
- Use meaningful student IDs like `S001`, `S002`, or use initials like `AJS`, `BJN`
- The **letter grade** is automatically assigned based on average:
  - A: 90-100
  - B: 80-89
  - C: 70-79
  - D: 60-69
  - F: 0-59

---

## Advanced Usage

### Scenario: Track multiple classes

You can run the application multiple times:
- Each run starts with a fresh set of students
- For persistent storage, you would need to enhance the application with file I/O

### Scenario: Getting class statistics

Use Menu Option 6 (Display Summary Report) to:
- See class averages
- Identify top and bottom performers
- Analyze grade distribution
- Understand overall class performance

### Scenario: Class evaluation

1. Add all students (Option 1)
2. Add all grades (Option 2)
3. Review individual performance (Option 3)
4. Generate summary report (Option 6)
5. Identify students needing support (~Grade D or F)
6. Identify high performers (~Grade A)
