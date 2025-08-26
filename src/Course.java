public class Course {
    private int courseId;
    private String courseName;
    private int credits;

    public Course(int courseId, String courseName, int credits) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
    }

    // Methods
    public void addCourse() {
        System.out.println("Course " + courseName + " added.");
    }
}
