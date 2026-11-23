
package dao;

import model.Student;

public class StudentDAOTest {

    public static void main(String[] args) {

        StudentDAO studentDAO = new StudentDAO();

        Student student = new Student(
                101,
                "Rahul Sharma",
                "rahul@gmail.com",
                "Java",
                85.5
        );

        // boolean result = studentDAO.addStudent(student);

        // if (result) {
        //     System.out.println("Student added to database successfully!");
        // } else {
        //     System.out.println("Failed to add student.");
        // }
        System.out.println("\n===== ALL STUDENTS =====");
        studentDAO.getAllStudents();

        System.out.println("\n===== SEARCH STUDENT =====");
        studentDAO.searchStudentById(101);

        System.out.println("\n===== UPDATE STUDENT =====");
        Student updatedStudent = new Student(
                101,
                "Rahul Sharma",
                "rahul@gmail.com",
                "Java Full Stack",
                90.0
        );

        boolean updateResult = studentDAO.updateStudent(updatedStudent);

        if (updateResult) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student update failed!");
        }

        System.out.println("\n===== DELETE STUDENT =====");
        boolean deleteResult = studentDAO.deleteStudent(101);

        if (deleteResult) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student deletion failed!");
        }
    }
}
