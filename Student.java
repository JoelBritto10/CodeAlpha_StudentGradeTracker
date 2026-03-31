import java.util.ArrayList;

/**
 * Student class to represent a student and their grades
 */
public class Student {
    private String name;
    private String id;
    private ArrayList<Double> grades;

    /**
     * Constructor to create a new Student
     * @param name the student's name
     * @param id the student's ID
     */
    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.grades = new ArrayList<>();
    }

    /**
     * Add a grade to the student's record
     * @param grade the grade to add
     */
    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            grades.add(grade);
        } else {
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
    }

    /**
     * Get the student's name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the student's ID
     * @return the student ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get all grades for this student
     * @return ArrayList of grades
     */
    public ArrayList<Double> getGrades() {
        return grades;
    }

    /**
     * Calculate the average grade
     * @return average grade, or 0 if no grades
     */
    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (double grade : grades) {
            sum += grade;
        }
        return sum / grades.size();
    }

    /**
     * Get the highest grade
     * @return highest grade, or 0 if no grades
     */
    public double getHighestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double highest = grades.get(0);
        for (double grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }

    /**
     * Get the lowest grade
     * @return lowest grade, or 0 if no grades
     */
    public double getLowestGrade() {
        if (grades.isEmpty()) {
            return 0;
        }
        double lowest = grades.get(0);
        for (double grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }

    /**
     * Get the number of grades
     * @return number of grades
     */
    public int getGradeCount() {
        return grades.size();
    }

    /**
     * Get letter grade based on average
     * @return letter grade (A, B, C, D, F)
     */
    public String getLetterGrade() {
        double average = getAverageGrade();
        if (average >= 90) return "A";
        if (average >= 80) return "B";
        if (average >= 70) return "C";
        if (average >= 60) return "D";
        return "F";
    }

    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Average: %.2f | Grade: %s", 
                id, name, getAverageGrade(), getLetterGrade());
    }
}
