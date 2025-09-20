import java.io.Serializable;

public class Registration implements Serializable {
    private static final long serialVersionUID = 1L;

    private Student student;
    private Course course;

    public Registration(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }

    @Override
    public String toString() {
        return student.getName() + " registered for " + course.getName();
    }
}
