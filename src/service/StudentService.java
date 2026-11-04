
package service;

import java.util.ArrayList;
import model.Student;

public class StudentService {

    // Store multiple student objects
    private ArrayList<Student> students = new ArrayList<>();

    // Add student
    public void addStudent(Student student) {

        // Check duplicate ID
        if (searchStudent(student.getId()) != null) {
            System.out.println("Student ID already exists!");
            return;
        }

        // Validate student details
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            System.out.println("Student name cannot be empty!");
            return;
        }

        if (student.getEmail() == null || student.getEmail().trim().isEmpty()) {
            System.out.println("Student email cannot be empty!");
            return;
        }

        if (student.getCourse() == null || student.getCourse().trim().isEmpty()) {
            System.out.println("Student course cannot be empty!");
            return;
        }

        if (student.getMarks() < 0 || student.getMarks() > 100) {
            System.out.println("Marks must be between 0 and 100!");
            return;
        }

        students.add(student);

        System.out.println("Student added successfully!");
    }

    // View all students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {

            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Course: " + student.getCourse());
            System.out.println("Marks: " + student.getMarks());

            System.out.println("-------------------------");
        }
    }

    public Student searchStudent(int id){

        for (Student student : students) {

            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    
    public boolean updateStudent(int id, String name, String email,
                             String course, double marks) {

        // Search existing student
        Student student = searchStudent(id);

        // Check student exists
        if (student == null) {
            return false;
        }

        // Validate name
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Student name cannot be empty!");
            return false;
        }

        // Validate email
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Student email cannot be empty!");
            return false;
        }

        // Validate course
        if (course == null || course.trim().isEmpty()) {
            System.out.println("Student course cannot be empty!");
            return false;
        }

        // Validate marks
        if (marks < 0 || marks > 100) {
            System.out.println("Marks must be between 0 and 100!");
            return false;
        }

        // Update student details
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        student.setMarks(marks);

        return true;
    }
    
    public boolean deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student == null) {
            return false;
        }

        students.remove(student);

        return true;
    }


    


}
