
package service;

import java.util.ArrayList;
import model.Student;
import util.FileHelper;
import java.util.HashMap;

public class StudentService implements StudentOperations {

    // Store multiple student objects
    private ArrayList<Student> students = new ArrayList<>();
    
    // Store students using ID as key
    private HashMap<Integer, Student> studentMap = new HashMap<>();

    // Constructor - Load students when application starts
    public StudentService() {

        students.addAll(FileHelper.loadStudents());

        // Add loaded students to HashMap
        for (Student student : students) {
            studentMap.put(student.getId(), student);
        }

        System.out.println("Student data loaded successfully!");
    }

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
            throw new IllegalArgumentException(
                "Marks must be between 0 and 100!"
            );
        }

        students.add(student);

        // Add student to HashMap
        studentMap.put(student.getId(), student);

        FileHelper.saveStudents(students);

        System.out.println("Student added successfully!");
    }

    // View all students
    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            student.display();
            System.out.println("-------------------------");
        }
    }


    public Student searchStudent(int id) {

        return studentMap.get(id);

    }

    // Search student by name (Method Overloading)
    public Student searchStudent(String name) {

        for (Student student : students) {

            if (student.getName().equalsIgnoreCase(name)) {
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

        // Save updated student data
        FileHelper.saveStudents(students);

        return true;
    }
    
    public boolean deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student == null) {
            return false;
        }

        students.remove(student);

        // Remove student from HashMap
        studentMap.remove(id);

        // Save updated student data
        FileHelper.saveStudents(students);

        return true;
    }


    


}
