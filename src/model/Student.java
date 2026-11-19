
package model;

public class Student extends Person {

    // Student-specific properties
    private int id;
    private String course;
    private double marks;

    // Constructor
    public Student(int id, String name, String email,
                   String course, double marks) {

        super(name, email);

        this.id = id;
        this.course = course;
        this.marks = marks;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    // Setter methods
    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}
