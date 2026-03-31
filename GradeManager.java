import java.util.ArrayList;

/**
 * GradeManager class to manage multiple students and their grades
 */
public class GradeManager {
    private ArrayList<Student> students;

    /**
     * Constructor to create a new GradeManager
     */
    public GradeManager() {
        this.students = new ArrayList<>();
    }

    /**
     * Add a new student to the system
     * @param name the student's name
     * @param id the student's ID
     * @return true if added successfully, false if student with same ID exists
     */
    public boolean addStudent(String name, String id) {
        // Check if student with this ID already exists
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return false;
            }
        }
        students.add(new Student(name, id));
        return true;
    }

    /**
     * Find a student by ID
     * @param id the student's ID
     * @return the Student object, or null if not found
     */
    public Student findStudent(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    /**
     * Remove a student by ID
     * @param id the student's ID
     * @return true if removed successfully, false if student not found
     */
    public boolean removeStudent(String id) {
        return students.removeIf(student -> student.getId().equals(id));
    }

    /**
     * Get all students
     * @return ArrayList of all students
     */
    public ArrayList<Student> getAllStudents() {
        return students;
    }

    /**
     * Get the number of students
     * @return number of students
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Calculate the class average
     * @return average of all student averages, or 0 if no students
     */
    public double getClassAverage() {
        if (students.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (Student student : students) {
            sum += student.getAverageGrade();
        }
        return sum / students.size();
    }

    /**
     * Get the highest average grade among all students
     * @return highest average grade, or 0 if no students
     */
    public double getHighestAverage() {
        if (students.isEmpty()) {
            return 0;
        }
        double highest = students.get(0).getAverageGrade();
        for (Student student : students) {
            if (student.getAverageGrade() > highest) {
                highest = student.getAverageGrade();
            }
        }
        return highest;
    }

    /**
     * Get the lowest average grade among all students
     * @return lowest average grade, or 0 if no students
     */
    public double getLowestAverage() {
        if (students.isEmpty()) {
            return 0;
        }
        double lowest = students.get(0).getAverageGrade();
        for (Student student : students) {
            if (student.getAverageGrade() < lowest) {
                lowest = student.getAverageGrade();
            }
        }
        return lowest;
    }

    /**
     * Get the highest individual grade across all students
     * @return highest grade, or 0 if no grades
     */
    public double getHighestGradeOverall() {
        double highest = 0;
        for (Student student : students) {
            if (student.getHighestGrade() > highest) {
                highest = student.getHighestGrade();
            }
        }
        return highest;
    }

    /**
     * Get the lowest individual grade across all students
     * @return lowest grade, or 0 if no grades
     */
    public double getLowestGradeOverall() {
        if (students.isEmpty()) {
            return 0;
        }
        double lowest = 100;
        for (Student student : students) {
            if (student.getGradeCount() > 0 && student.getLowestGrade() < lowest) {
                lowest = student.getLowestGrade();
            }
        }
        return lowest == 100 ? 0 : lowest;
    }

    /**
     * Get the top performing student
     * @return Student with highest average, or null if no students
     */
    public Student getTopStudent() {
        if (students.isEmpty()) {
            return null;
        }
        Student topStudent = students.get(0);
        for (Student student : students) {
            if (student.getAverageGrade() > topStudent.getAverageGrade()) {
                topStudent = student;
            }
        }
        return topStudent;
    }

    /**
     * Get the lowest performing student
     * @return Student with lowest average, or null if no students
     */
    public Student getBottomStudent() {
        if (students.isEmpty()) {
            return null;
        }
        Student bottomStudent = students.get(0);
        for (Student student : students) {
            if (student.getAverageGrade() < bottomStudent.getAverageGrade()) {
                bottomStudent = student;
            }
        }
        return bottomStudent;
    }

    /**
     * Count students with a specific letter grade
     * @param letterGrade the letter grade to count
     * @return number of students with that grade
     */
    public int countByGrade(String letterGrade) {
        int count = 0;
        for (Student student : students) {
            if (student.getLetterGrade().equals(letterGrade)) {
                count++;
            }
        }
        return count;
    }
}
