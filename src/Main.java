// Main.java
// Simple menu-driven program to demonstrate OOP classes

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(1, "Alice", "alice@example.com");
        s1.register();

        Course c1 = new Course(101, "Java Programming", 4);
        c1.addCourse();

        Registration r1 = new Registration(1, s1.getStudentId(), c1.getCourseId());
        r1.enroll();

        System.out.println("✅ Course Registration System sample run complete.");
    }
}
