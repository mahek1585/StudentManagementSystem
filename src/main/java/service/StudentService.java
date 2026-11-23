
package service;

import java.util.ArrayList;
import model.Student;
import java.util.Comparator;
import dao.StudentDAO;

public class StudentService implements StudentOperations {

  
    private StudentDAO studentDAO = new StudentDAO();
    
    

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

        boolean added = studentDAO.addStudent(student);

        if (added) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student!");
        }
    }

    // View all students
    @Override
    public void viewStudents() {

        ArrayList<Student> students = new ArrayList<>(
            studentDAO.getAllStudents()
        );

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            student.display();
            System.out.println("-------------------------");
        }
    }

    // Sort students by name (A to Z)
    public void sortStudentsByName() {

            ArrayList<Student> students = new ArrayList<>(
                studentDAO.getAllStudents()
            );

            students.sort(
                Comparator.comparing(Student::getName)
            );

            System.out.println("Students sorted by name successfully!");

            for (Student student : students) {
                student.display();
                System.out.println("-------------------------");
            }
        }
        // Sort students by marks (Highest to Lowest)
        public void sortStudentsByMarks() {

        ArrayList<Student> students = new ArrayList<>(
            studentDAO.getAllStudents()
        );

        students.sort(
            Comparator.comparingDouble(Student::getMarks).reversed()
        );

        System.out.println("Students sorted by marks successfully!");

        for (Student student : students) {
            student.display();
            System.out.println("-------------------------");
        }
    }
    // Sort students by ID (Ascending)
    public void sortStudentsById() {

        ArrayList<Student> students = new ArrayList<>(
            studentDAO.getAllStudents()
        );

        students.sort(
            Comparator.comparingInt(Student::getId)
        );

        System.out.println("Students sorted by ID successfully!");

        for (Student student : students) {
            student.display();
            System.out.println("-------------------------");
        }
    }

    @Override
    public Student searchStudent(int id) {
        return studentDAO.searchStudentById(id);
    }

    // Search student by name (Method Overloading)
    @Override
    public Student searchStudent(String name) {

        for (Student student : studentDAO.getAllStudents()) {

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

            Student updatedStudent = new Student(
                id, name, email, course, marks
            );

            return studentDAO.updateStudent(updatedStudent);
        }
    
    @Override
    public boolean deleteStudent(int id) {

        Student student = searchStudent(id);

        if (student == null) {
            return false;
        }

        return studentDAO.deleteStudent(id);
    }

    


}
