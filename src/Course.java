

public class Course  {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int credits;

    public Course(int id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCredits() { return credits; }

    @Override
    public String toString() {
        return "Course ID: " + id + ", Name: " + name + ", Credits: " + credits;
    }
}
