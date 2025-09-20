import java.util.*;

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Registration> registrations = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Course Registration System ===");
            System.out.println("1. Add Course");
            System.out.println("2. Add Student");
            System.out.println("3. Register Student for Course");
            System.out.println("4. View Registrations");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    addCourse(sc);
                    break;
                case 2:
                    addStudent(sc);
                    break;
                case 3:
                    registerStudent(sc);
                    break;
                case 4:
                    viewRegistrations();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addCourse(Scanner sc) {
        System.out.print("Enter Course ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Course Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course Credits: ");
        int credits = sc.nextInt();

        courses.add(new Course(id, name, credits));
        System.out.println("Course added successfully.");
    }

    private static void addStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Student Email: ");
        String email = sc.nextLine();

        students.add(new Student(id, name, email));
        System.out.println("Student added successfully.");
    }

    private static void registerStudent(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int sid = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int cid = sc.nextInt();

        Student student = null;
        Course course = null;

        for (Student s : students) {
            if (s.getId() == sid) student = s;
        }
        for (Course c : courses) {
            if (c.getId() == cid) course = c;
        }

        if (student != null && course != null) {
            registrations.add(new Registration(student, course));
            System.out.println("Registration successful.");
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    private static void viewRegistrations() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations yet.");
        } else {
            for (Registration r : registrations) {
                System.out.println(r);
            }
        }
    }
}
