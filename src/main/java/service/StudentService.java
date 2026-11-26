
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

        try {
        boolean added = studentDAO.addStudent(student);

        if (added) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student!");
        }

        } catch (java.sql.SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // View all students
   @Override
    public void viewStudents() {

        try {
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

        } catch (java.sql.SQLException e) {
            System.out.println("Database error while retrieving students: " + e.getMessage());
        }
    }

    // Sort students by name (A to Z)
    public void sortStudentsByName() {

        try {
            ArrayList<Student> students = new ArrayList<>(
                studentDAO.getAllStudents()
            );

            students.sort(
                Comparator.comparing(Student::getName)
            );

            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }

            System.out.println("Students sorted by name successfully!");

            for (Student student : students) {
                student.display();
                System.out.println("-------------------------");
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Database error while sorting students: " + e.getMessage());
        }
    }

    // Sort students by marks (Highest to Lowest)
    public void sortStudentsByMarks() {

        try {
            ArrayList<Student> students = new ArrayList<>(
                studentDAO.getAllStudents()
            );

            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }

            students.sort(
                Comparator.comparingDouble(Student::getMarks).reversed()
            );

            System.out.println("Students sorted by marks successfully!");

            for (Student student : students) {
                student.display();
                System.out.println("-------------------------");
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Database error while sorting students: " + e.getMessage());
        }
    }

    // Sort students by ID (Ascending)
    public void sortStudentsById() {

        try {
            ArrayList<Student> students = new ArrayList<>(
                studentDAO.getAllStudents()
            );

            if (students.isEmpty()) {
                System.out.println("No students found.");
                return;
            }

            students.sort(
                Comparator.comparingInt(Student::getId)
            );

            System.out.println("Students sorted by ID successfully!");

            for (Student student : students) {
                student.display();
                System.out.println("-------------------------");
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Database error while sorting students: " + e.getMessage());
        }
    }

    @Override
    public Student searchStudent(int id) {

        try {
            return studentDAO.searchStudentById(id);

        } catch (java.sql.SQLException e) {
            System.out.println("Database error while searching student: " + e.getMessage());
            return null;
        }
    }

    // Search student by name (Method Overloading)
    @Override
    public Student searchStudent(String name) {

        try {
            for (Student student : studentDAO.getAllStudents()) {

                if (student.getName().equalsIgnoreCase(name)) {
                    return student;
                }
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Database error while searching student: " + e.getMessage());
        }

        return null;
    }

    
    public boolean updateStudent(int id, String name, String email,
                             String course, double marks) {

       

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

            try {
                return studentDAO.updateStudent(updatedStudent);

            } catch (java.sql.SQLException e) {
                System.out.println("Database error while updating student: " + e.getMessage());
                return false;
            }
        }
    
    @Override
    public boolean deleteStudent(int id) {

        try {
            return studentDAO.deleteStudent(id);

        } catch (java.sql.SQLException e) {
            System.out.println("Database error while deleting student: " + e.getMessage());
            return false;
        }
    }

    

    


}
