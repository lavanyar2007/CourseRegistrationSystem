import java.util.*;

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Registration> registrations = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Add default users
        users.add(new User("admin", "admin123", "Admin"));
        users.add(new User("alice", "pass1", "Student"));
        users.add(new User("bob", "pass2", "Student"));

        System.out.println("--- Welcome to Course Registration System ---");
        System.out.print("Username: ");
        String uname = sc.nextLine();
        System.out.print("Password: ");
        String pwd = sc.nextLine();

        User currentUser = null;
        for (User u : users) {
            if (u.getUsername().equals(uname) && u.getPassword().equals(pwd)) {
                currentUser = u;
                break;
            }
        }

        if (currentUser == null) {
            System.out.println("Invalid login. Exiting...");
            return;
        }

        System.out.println("Login successful! Role: " + currentUser.getRole());

        boolean exit = false;
        while (!exit) {
            if (currentUser.getRole().equalsIgnoreCase("Admin")) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Course");
                System.out.println("2. Add Student");
                System.out.println("3. View All Registrations");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1: addCourse(sc); break;
                    case 2: addStudent(sc); break;
                    case 3: viewRegistrations(); break;
                    case 4: exit = true; break;
                    default: System.out.println("Invalid choice."); 
                }
            } else { // Student role
                System.out.println("\n--- Student Menu ---");
                System.out.println("1. View Courses");
                System.out.println("2. Register for Course");
                System.out.println("3. View My Registrations");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine(); 

                // Find student object for logged-in user
                Student currentStudent = null;
                for (Student s : students) {
                    if (s.getName().equalsIgnoreCase(currentUser.getUsername())) {
                        currentStudent = s;
                        break;
                    }
                }

                switch (choice) {
                    case 1: viewCourses(); break;
                    case 2:
                        registerStudent(sc, currentStudent);
                        break;
                    case 3: viewMyRegistrations(currentStudent); break;
                    case 4: exit = true; break;
                    default: System.out.println("Invalid choice.");
                }
            }
        }

        System.out.println("Exiting system. Goodbye!");
        sc.close();
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

    private static void viewCourses() {
        System.out.println("\nCourses:");
        for (Course c : courses) System.out.println(c);
    }

    private static void registerStudent(Scanner sc, Student student) {
        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        System.out.print("Enter Course ID to register: ");
        int cid = sc.nextInt();

        Course course = null;
        for (Course c : courses) {
            if (c.getId() == cid) course = c;
        }

        if (course != null) {
            registrations.add(new Registration(student, course));
            System.out.println("Registration successful.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void viewRegistrations() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations yet.");
        } else {
            System.out.println("\nAll Registrations:");
            for (Registration r : registrations) System.out.println(r);
        }
    }

    private static void viewMyRegistrations(Student student) {
        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        System.out.println("\nMy Registrations:");
        boolean found = false;
        for (Registration r : registrations) {
            if (r.getStudent().getId() == student.getId()) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) System.out.println("No registrations yet.");
    }
}
