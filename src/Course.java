// Course.java
// Represents a Course in the Course Registration System

public class Course {
    private int courseId;
    private String courseName;
    private int credits;

    // Constructor
    public Course(int courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }

    // Getters & Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public void addCourse() {
        System.out.println("Course " + courseName + " added.");
    }
}
