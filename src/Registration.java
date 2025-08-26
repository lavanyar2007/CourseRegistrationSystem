// Registration.java
// Represents the enrollment of a student in a course

public class Registration {
    private int regId;
    private int studentId;
    private int courseId;

    // Constructor
    public Registration(int regId, int studentId, int courseId) {
        this.regId = regId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    // Getters & Setters
    public int getRegId() { return regId; }
    public void setRegId(int regId) { this.regId = regId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public void enroll() {
        System.out.println("Student " + studentId + " enrolled in course " + courseId);
    }
}
