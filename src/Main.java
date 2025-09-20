import java.util.*;
import java.io.*;

public class Main {
    private static List<Student> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Registration> registrations = new ArrayList<>();
    private static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Load saved data
        loadData();

        // Always ensure at least one admin exists
        boolean hasAdmin = users.stream().anyMatch(u -> u.getRole().equalsIgnoreCase("Admin"));
        if (!hasAdmin) {
            users.add(new User("admin", "admin123", "Admin", -1));
        }

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
            sc.close();
            return;
        }

        System.out.println("Login successful! Role: " + currentUser.getRole());

        boolean exit = false;
        while (!exit) {
            if (currentUser.getRole().equalsIgnoreCase("Admin")) {
                System.out.println("\n--- Admin Menu ---");
                System.out.println("1. Add Course");
                System.out.println("2. Update Course");
                System.out.println("3. Delete Course");
                System.out.println("4. Add Student");
                System.out.println("5. Update Student");
                System.out.println("6. Delete Student");
                System.out.println("7. View All Registrations");
                System.out.println("8. Delete Registration");
                System.out.println("9. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: addCourse(sc); break;
                    case 2: updateCourse(sc); break;
                    case 3: deleteCourse(sc); break;
                    case 4: addStudentWithUser(sc); break;
                    case 5: updateStudent(sc); break;
                    case 6: deleteStudent(sc); break;
                    case 7: viewRegistrations(); break;
                    case 8: deleteRegistration(sc); break;
                    case 9: exit = true; break;
                    default: System.out.println("Invalid choice.");
                }

            } else { // Student role
                Student currentStudent = null;
                for (Student s : students) {
                    if (s.getId() == currentUser.getStudentId()) {
                        currentStudent = s;
                        break;
                    }
                }

                System.out.println("\n--- Student Menu ---");
                System.out.println("1. View Courses");
                System.out.println("2. Register for Course");
                System.out.println("3. View My Registrations");
                System.out.println("4. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: viewCourses(); break;
                    case 2: registerStudent(sc, currentStudent); break;
                    case 3: viewMyRegistrations(currentStudent); break;
                    case 4: exit = true; break;
                    default: System.out.println("Invalid choice.");
                }
            }
        }

        // Save all data before exiting
        saveData();

        System.out.println("Exiting system. Goodbye!");
        sc.close();
    }

    // --- Admin Methods ---
    private static void addCourse(Scanner sc) {
        System.out.print("Enter Course ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Course Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Course Credits: ");
        int credits = sc.nextInt();
        sc.nextLine();

        courses.add(new Course(id, name, credits));
        System.out.println("Course added successfully.");
    }

    private static void updateCourse(Scanner sc) {
        System.out.print("Enter Course ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Course courseToUpdate = null;
        for (Course c : courses) {
            if (c.getId() == id) {
                courseToUpdate = c;
                break;
            }
        }

        if (courseToUpdate != null) {
            System.out.print("Enter new Course Name: ");
            String name = sc.nextLine();
            System.out.print("Enter new Credits: ");
            int credits = sc.nextInt();
            sc.nextLine();

            courses.remove(courseToUpdate);
            courses.add(new Course(id, name, credits));
            System.out.println("Course updated successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void deleteCourse(Scanner sc) {
        System.out.print("Enter Course ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        Course courseToDelete = null;
        for (Course c : courses) {
            if (c.getId() == id) {
                courseToDelete = c;
                break;
            }
        }

        if (courseToDelete != null) {
            registrations.removeIf(r -> r.getCourse().getId() == id);
            courses.remove(courseToDelete);
            System.out.println("Course and related registrations deleted successfully.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void addStudentWithUser(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Student Email: ");
        String email = sc.nextLine();
        System.out.print("Enter username for login: ");
        String uname = sc.nextLine();
        System.out.print("Enter password: ");
        String pwd = sc.nextLine();

        students.add(new Student(id, name, email));
        users.add(new User(uname, pwd, "Student", id));
        System.out.println("Student and user added successfully.");
    }

    private static void updateStudent(Scanner sc) {
        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student studentToUpdate = null;
        for (Student s : students) {
            if (s.getId() == id) {
                studentToUpdate = s;
                break;
            }
        }

        if (studentToUpdate != null) {
            System.out.print("Enter new Name: ");
            String name = sc.nextLine();
            System.out.print("Enter new Email: ");
            String email = sc.nextLine();

            students.remove(studentToUpdate);
            students.add(new Student(id, name, email));
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent(Scanner sc) {
        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student studentToDelete = null;
        for (Student s : students) {
            if (s.getId() == id) {
                studentToDelete = s;
                break;
            }
        }

        if (studentToDelete != null) {
            students.remove(studentToDelete);
            users.removeIf(u -> u.getStudentId() == id);
            registrations.removeIf(r -> r.getStudent().getId() == id);

            System.out.println("Student, login, and related registrations deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void viewRegistrations() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations yet.");
        } else {
            System.out.println("\n--- All Registrations ---");
            for (Registration r : registrations) System.out.println(r);
        }
    }

    private static void deleteRegistration(Scanner sc) {
        System.out.print("Enter Student ID: ");
        int sid = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int cid = sc.nextInt();
        sc.nextLine();

        boolean removed = registrations.removeIf(r -> r.getStudent().getId() == sid && r.getCourse().getId() == cid);

        if (removed) System.out.println("Registration deleted successfully.");
        else System.out.println("Registration not found.");
    }

    // --- Student Methods ---
    private static void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("\n--- Available Courses ---");
            for (Course c : courses) System.out.println(c);
        }
    }

    private static void registerStudent(Scanner sc, Student student) {
        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        System.out.print("Enter Course ID to register: ");
        int cid = sc.nextInt();
        sc.nextLine();

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

    private static void viewMyRegistrations(Student student) {
        if (student == null) {
            System.out.println("Student record not found.");
            return;
        }

        System.out.println("\n--- My Registrations ---");
        boolean found = false;
        for (Registration r : registrations) {
            if (r.getStudent().getId() == student.getId()) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) System.out.println("No registrations yet.");
    }

    // --- Persistence Methods ---
    @SuppressWarnings("unchecked")
    private static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
            students = (List<Student>) ois.readObject();
            courses = (List<Course>) ois.readObject();
            registrations = (List<Registration>) ois.readObject();
            users = (List<User>) ois.readObject();
            System.out.println("Data loaded successfully.");
        } catch (Exception e) {
            System.out.println("No saved data found, starting fresh.");
        }
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(students);
            oos.writeObject(courses);
            oos.writeObject(registrations);
            oos.writeObject(users);
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}