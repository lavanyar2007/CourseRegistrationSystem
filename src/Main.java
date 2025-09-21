import java.util.*;
import java.io.*;

// Main class
public class Main {
    // Lists to store objects in memory
    private static List<Student> studentList = new ArrayList<>();
    private static List<Course> courseList = new ArrayList<>();
    private static List<Registration> regList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //this will  Load previous stored data
        loadData();

        System.out.println("------ Welcome to Simple Course Registration System ------");

        int choice;  
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Add Course");
            System.out.println("2. Update Course");
            System.out.println("3. Delete Course");
            System.out.println("4. Add Student");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. View All Courses");
            System.out.println("8. View All Students");
            System.out.println("9. Register Student to Course");
            System.out.println("10. View All Registrations");
            System.out.println("11. View Student Registrations");
            System.out.println("12. Delete Registration");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // nectInt() only take token & leave newline so we use nextLine()  to ignore the next input take this empty newline as input

            switch(choice){
                case 1: 
                   addCourse(sc); 
                   break;
                case 2: 
                   updateCourse(sc); 
                   break;
                case 3: 
                   deleteCourse(sc); 
                   break;
                case 4: 
                   addStudent(sc); 
                   break;
                case 5: 
                   updateStudent(sc); 
                   break;
                case 6: 
                   deleteStudent(sc); 
                   break;
                case 7: 
                   showCourses(); 
                   break;
                case 8: 
                   showStudents(); 
                   break;
                case 9: 
                   registerStudent(sc); 
                   break;
                case 10: 
                   showRegistrations(); 
                   break;
                case 11: 
                   showStudentRegistrations(sc); 
                   break;
                case 12: 
                   deleteRegistration(sc); 
                   break;
                case 13: 
                    saveData(); // save all the data before exit
                    System.out.println("System Exiting......!");
                    break;
                default: 
                    System.out.println("Invalid option. Try again...");
            }

        } while(choice != 13);

        sc.close();
    }

    // Cource functions
    private static void addCourse(Scanner sc){
        System.out.print("Course ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Course Name: ");
        String name = sc.nextLine();
        System.out.print("Credits: ");
        int cr = sc.nextInt();
        sc.nextLine();

        courseList.add(new Course(id, name, cr));
        System.out.println("Course added.");
    }

    private static void updateCourse(Scanner sc){
        System.out.print("Enter course id to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        Course found = null;

        for(Course c: courseList){
            if(c.getId() == id){
                found = c;
                break;
            }
        }

        if(found != null){
            System.out.print("New name: ");
            String n = sc.nextLine();
            System.out.print("New credits: ");
            int cr = sc.nextInt();
            sc.nextLine();
            courseList.remove(found);
            courseList.add(new Course(id, n, cr));
            System.out.println("Course updated.");
        } else {
            System.out.println("Course not found.");
        }
    }

    private static void deleteCourse(Scanner sc){
        System.out.print("Course id to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        Course todel = null;
        for(Course c: courseList){
            if(c.getId() == id){
                todel = c;
                break;
            }
        }

        if(todel != null){
            // remove registrations with this course
            regList.removeIf(r -> r.getCourse().getId() == id);
            courseList.remove(todel);
            System.out.println("Course deleted.");
        } else {
            System.out.println("Course not found.");
        }
    }
    //Student functions
    private static void addStudent(Scanner sc){
        System.out.print("Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Name: ");
        String nm = sc.nextLine();
        System.out.print("Email: ");
        String mail = sc.nextLine();

        studentList.add(new Student(id, nm, mail));
        System.out.println("Student added.");
    }

    private static void updateStudent(Scanner sc){
        System.out.print("Enter student id to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student st = null;
        for(Student s: studentList){
            if(s.getId() == id){
                st = s;
                break;
            }
        }

        if(st != null){
            System.out.print("New name: ");
            String n = sc.nextLine();
            System.out.print("New email: ");
            String e = sc.nextLine();

            studentList.remove(st);
            studentList.add(new Student(id, n, e));
            System.out.println("Student updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudent(Scanner sc){
        System.out.print("Student id to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        Student del = null;
        for(Student s: studentList){
            if(s.getId() == id){
                del = s;
                break;
            }
        }

        if(del != null){
            studentList.remove(del);
            regList.removeIf(r -> r.getStudent().getId() == id);
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    // registration functions
    private static void registerStudent(Scanner sc){
        System.out.print("Student id: ");
        int sid = sc.nextInt();
        sc.nextLine();
        System.out.print("Course id: ");
        int cid = sc.nextInt();
        sc.nextLine();

        Student st = null;
        for(Student s: studentList){
            if(s.getId() == sid){ st = s; break; }
        }

        Course crs = null;
        for(Course c: courseList){
            if(c.getId() == cid){ crs = c; break; }
        }

        if(st != null && crs != null){
            regList.add(new Registration(st, crs));
            System.out.println("Registered successfully.");
        } else {
            System.out.println("Student or Course not found.");
        }
    }

    private static void showRegistrations(){
        if(regList.isEmpty()){
            System.out.println("No registrations.");
            return;
        }
        System.out.println("=== All Registrations ===");
        for(Registration r: regList){
            System.out.println(r);
        }
    }

    private static void showStudentRegistrations(Scanner sc){
        System.out.print("Enter student id: ");
        int sid = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        for(Registration r: regList){
            if(r.getStudent().getId() == sid){
                System.out.println(r);
                found = true;
            }
        }
        if(!found){
            System.out.println("No registrations for this student.");
        }
    }

    private static void deleteRegistration(Scanner sc){
        System.out.print("Student id: ");
        int sid = sc.nextInt();
        System.out.print("Course id: ");
        int cid = sc.nextInt();
        sc.nextLine();

        boolean removed = regList.removeIf(r -> r.getStudent().getId() == sid && r.getCourse().getId() == cid);

        if(removed) System.out.println("Registration deleted.");
        else System.out.println("Not found.");
    }

    // display functions
    private static void showCourses(){
        if(courseList.isEmpty()){
            System.out.println("No courses available.");
            return;
        }
        for(Course c: courseList){
            System.out.println(c);
        }
    }

    private static void showStudents(){
        if(studentList.isEmpty()){
            System.out.println("No students exist.");
            return;
        }
        for(Student s: studentList){
            System.out.println(s);
        }
    }
    // save and load function for previous data
    @SuppressWarnings("unchecked")
    private static void loadData(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"))){
            studentList = (List<Student>) in.readObject();
            courseList = (List<Course>) in.readObject();
            regList = (List<Registration>) in.readObject();
            System.out.println("Previous data loaded.");
        } catch(Exception e){
            System.out.println("No saved file. Starting fresh.");
        }
    }

    private static void saveData(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"))){
            out.writeObject(studentList);
            out.writeObject(courseList);
            out.writeObject(regList);
            System.out.println("Data saved.");
        } catch(Exception e){
            System.out.println("Error while saving.");
        }
    }
}
