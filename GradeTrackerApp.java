import java.util.Scanner;
import java.util.ArrayList;

/**
 * GradeTrackerApp - Main console application for managing student grades
 */
public class GradeTrackerApp {
    private GradeManager gradeManager;
    private Scanner scanner;

    /**
     * Constructor to initialize the application
     */
    public GradeTrackerApp() {
        this.gradeManager = new GradeManager();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display the main menu
     */
    public void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        STUDENT GRADE TRACKER SYSTEM");
        System.out.println("=".repeat(50));
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View Student Details");
        System.out.println("4. Remove Student");
        System.out.println("5. Display All Students");
        System.out.println("6. Display Summary Report");
        System.out.println("7. Search Student by ID");
        System.out.println("8. Exit");
        System.out.println("=".repeat(50));
        System.out.print("Enter your choice (1-8): ");
    }

    /**
     * Add a new student
     */
    public void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty!");
            return;
        }

        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Error: ID cannot be empty!");
            return;
        }

        if (gradeManager.addStudent(name, id)) {
            System.out.println("✓ Student added successfully!");
        } else {
            System.out.println("✗ Error: A student with this ID already exists!");
        }
    }

    /**
     * Add a grade to a student
     */
    public void addGrade() {
        System.out.println("\n--- Add Grade to Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        Student student = gradeManager.findStudent(id);
        if (student == null) {
            System.out.println("✗ Error: Student not found!");
            return;
        }

        System.out.print("Enter Grade (0-100): ");
        try {
            double grade = Double.parseDouble(scanner.nextLine().trim());
            if (grade < 0 || grade > 100) {
                System.out.println("✗ Error: Grade must be between 0 and 100!");
                return;
            }
            student.addGrade(grade);
            System.out.println("✓ Grade added successfully!");
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Invalid grade format!");
        }
    }

    /**
     * View details of a specific student
     */
    public void viewStudentDetails() {
        System.out.println("\n--- View Student Details ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        Student student = gradeManager.findStudent(id);
        if (student == null) {
            System.out.println("✗ Error: Student not found!");
            return;
        }

        displayStudentInfo(student);
    }

    /**
     * Display information for a student
     */
    private void displayStudentInfo(Student student) {
        System.out.println("\n" + "-".repeat(50));
        System.out.println("Student Information");
        System.out.println("-".repeat(50));
        System.out.println("Name: " + student.getName());
        System.out.println("ID: " + student.getId());
        System.out.println("Total Grades: " + student.getGradeCount());

        if (student.getGradeCount() > 0) {
            System.out.println("\nGrades: " + student.getGrades());
            System.out.printf("Average Grade: %.2f\n", student.getAverageGrade());
            System.out.printf("Highest Grade: %.2f\n", student.getHighestGrade());
            System.out.printf("Lowest Grade: %.2f\n", student.getLowestGrade());
            System.out.println("Letter Grade: " + student.getLetterGrade());
        } else {
            System.out.println("No grades recorded yet.");
        }
        System.out.println("-".repeat(50));
    }

    /**
     * Remove a student
     */
    public void removeStudent() {
        System.out.println("\n--- Remove Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        if (gradeManager.removeStudent(id)) {
            System.out.println("✓ Student removed successfully!");
        } else {
            System.out.println("✗ Error: Student not found!");
        }
    }

    /**
     * Display all students
     */
    public void displayAllStudents() {
        System.out.println("\n--- All Students ---");

        ArrayList<Student> students = gradeManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        System.out.println("-".repeat(80));
        System.out.printf("%-10s | %-20s | %-15s | %-10s | %-8s\n", 
                "ID", "Name", "Average", "Highest", "Grade");
        System.out.println("-".repeat(80));

        for (Student student : students) {
            if (student.getGradeCount() > 0) {
                System.out.printf("%-10s | %-20s | %-15.2f | %-10.2f | %-8s\n",
                        student.getId(),
                        student.getName(),
                        student.getAverageGrade(),
                        student.getHighestGrade(),
                        student.getLetterGrade());
            } else {
                System.out.printf("%-10s | %-20s | %-15s | %-10s | %-8s\n",
                        student.getId(),
                        student.getName(),
                        "N/A",
                        "N/A",
                        "N/A");
            }
        }
        System.out.println("-".repeat(80));
    }

    /**
     * Display comprehensive summary report
     */
    public void displaySummaryReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           CLASS SUMMARY REPORT");
        System.out.println("=".repeat(50));

        if (gradeManager.getStudentCount() == 0) {
            System.out.println("No students in the system.");
            return;
        }

        // Overall Statistics
        System.out.println("\nOverall Statistics:");
        System.out.println("-".repeat(50));
        System.out.println("Total Students: " + gradeManager.getStudentCount());

        if (gradeManager.getHighestAverage() > 0) {
            System.out.printf("Class Average: %.2f\n", gradeManager.getClassAverage());
            System.out.printf("Highest Average: %.2f\n", gradeManager.getHighestAverage());
            System.out.printf("Lowest Average: %.2f\n", gradeManager.getLowestAverage());
            System.out.printf("Highest Grade (Overall): %.2f\n", gradeManager.getHighestGradeOverall());
            System.out.printf("Lowest Grade (Overall): %.2f\n", gradeManager.getLowestGradeOverall());
        }

        // Top and Bottom Performers
        System.out.println("\nTop Performers:");
        System.out.println("-".repeat(50));
        Student topStudent = gradeManager.getTopStudent();
        if (topStudent != null && topStudent.getGradeCount() > 0) {
            System.out.println("Top Student: " + topStudent.getName() + " (ID: " + topStudent.getId() + ")");
            System.out.printf("Average: %.2f | Grade: %s\n", topStudent.getAverageGrade(), topStudent.getLetterGrade());
        }

        System.out.println("\nBottom Performers:");
        System.out.println("-".repeat(50));
        Student bottomStudent = gradeManager.getBottomStudent();
        if (bottomStudent != null && bottomStudent.getGradeCount() > 0) {
            System.out.println("Bottom Student: " + bottomStudent.getName() + " (ID: " + bottomStudent.getId() + ")");
            System.out.printf("Average: %.2f | Grade: %s\n", bottomStudent.getAverageGrade(), bottomStudent.getLetterGrade());
        }

        // Grade Distribution
        System.out.println("\nGrade Distribution:");
        System.out.println("-".repeat(50));
        System.out.println("A Grade (90-100): " + gradeManager.countByGrade("A") + " students");
        System.out.println("B Grade (80-89):  " + gradeManager.countByGrade("B") + " students");
        System.out.println("C Grade (70-79):  " + gradeManager.countByGrade("C") + " students");
        System.out.println("D Grade (60-69):  " + gradeManager.countByGrade("D") + " students");
        System.out.println("F Grade (0-59):   " + gradeManager.countByGrade("F") + " students");

        System.out.println("=".repeat(50));
    }

    /**
     * Search for a student by ID
     */
    public void searchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();

        Student student = gradeManager.findStudent(id);
        if (student == null) {
            System.out.println("✗ Error: Student not found!");
        } else {
            displayStudentInfo(student);
        }
    }

    /**
     * Main application loop
     */
    public void run() {
        System.out.println("\nWelcome to Student Grade Tracker!");
        boolean running = true;

        while (running) {
            displayMainMenu();
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        addStudent();
                        break;
                    case "2":
                        addGrade();
                        break;
                    case "3":
                        viewStudentDetails();
                        break;
                    case "4":
                        removeStudent();
                        break;
                    case "5":
                        displayAllStudents();
                        break;
                    case "6":
                        displaySummaryReport();
                        break;
                    case "7":
                        searchStudent();
                        break;
                    case "8":
                        System.out.println("\nThank you for using Student Grade Tracker!");
                        running = false;
                        break;
                    default:
                        System.out.println("✗ Invalid choice! Please enter a number between 1 and 8.");
                }
            } catch (Exception e) {
                System.out.println("✗ An error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }

    /**
     * Main method to start the application
     */
    public static void main(String[] args) {
        GradeTrackerApp app = new GradeTrackerApp();
        app.run();
    }
}
