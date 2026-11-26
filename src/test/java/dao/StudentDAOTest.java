
package dao;

import model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOTest {

    private final StudentDAO studentDAO = new StudentDAO();

   @AfterEach
    void cleanup() throws SQLException {

        studentDAO.deleteStudent(999);
        studentDAO.deleteStudent(101);
        studentDAO.deleteStudent(102);
        studentDAO.deleteStudent(103);
    }

    @Test
    void testAddStudent() throws SQLException {

        Student student = new Student(
                999,
                "JUnit Test",
                "junit@test.com",
                "Java",
                85.5
        );

        boolean result = studentDAO.addStudent(student);

        assertTrue(result);

        Student savedStudent = studentDAO.searchStudentById(999);

        assertNotNull(savedStudent);
        assertEquals("JUnit Test", savedStudent.getName());
    }

    @Test
    void testGetAllStudents() throws SQLException {

        List<Student> students = studentDAO.getAllStudents();

        assertNotNull(students);
    }

    @Test
    void testSearchStudentById() throws SQLException {

        Student student = new Student(
                101,
                "Search Test",
                "search@test.com",
                "Java",
                85.5
        );

        studentDAO.addStudent(student);

        Student savedStudent = studentDAO.searchStudentById(101);

        assertNotNull(savedStudent);
        assertEquals(101, savedStudent.getId());
        assertEquals("Search Test", savedStudent.getName());
    }

    @Test
void testUpdateStudent() throws SQLException {

    Student student = new Student(
            102,
            "Update Test",
            "update@test.com",
            "Java",
            80.0
    );

    studentDAO.addStudent(student);

    Student updatedStudent = new Student(
            102,
            "Updated Name",
            "updated@test.com",
            "Java Full Stack",
            95.0
    );

    boolean result = studentDAO.updateStudent(updatedStudent);

    assertTrue(result);

    Student savedStudent = studentDAO.searchStudentById(102);

    assertEquals("Updated Name", savedStudent.getName());
    assertEquals("updated@test.com", savedStudent.getEmail());
    assertEquals("Java Full Stack", savedStudent.getCourse());
    assertEquals(95.0, savedStudent.getMarks());
}
@Test
void testDeleteStudent() throws SQLException {

    Student student = new Student(
            103,
            "Delete Test",
            "delete@test.com",
            "Java",
            75.0
    );

    studentDAO.addStudent(student);

    boolean result = studentDAO.deleteStudent(103);

    assertTrue(result);

    Student deletedStudent = studentDAO.searchStudentById(103);

    assertNull(deletedStudent);
}
}
