package service;

import model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private final StudentService studentService = new StudentService();

   @AfterEach
    void cleanup() {
        studentService.deleteStudent(201);
        studentService.deleteStudent(202);
        studentService.deleteStudent(203);
        studentService.deleteStudent(204);
        studentService.deleteStudent(205);
        studentService.deleteStudent(206);
        studentService.deleteStudent(207);
        studentService.deleteStudent(208);
        studentService.deleteStudent(209);
        studentService.deleteStudent(210);
        studentService.deleteStudent(211);
    }

    @Test
    void testAddStudent() {

        Student student = new Student(
                201,
                "Service Test",
                "service@test.com",
                "Java",
                90.0
        );

        studentService.addStudent(student);

        Student savedStudent = studentService.searchStudent(201);

        assertNotNull(savedStudent);
        assertEquals("Service Test", savedStudent.getName());
    }

    @Test
    void testDuplicateStudentId() {

        Student firstStudent = new Student(
                202,
                "First Student",
                "first@test.com",
                "Java",
                85.0
        );

        Student duplicateStudent = new Student(
                202,
                "Second Student",
                "second@test.com",
                "Python",
                90.0
        );

        studentService.addStudent(firstStudent);

        studentService.addStudent(duplicateStudent);

        Student savedStudent = studentService.searchStudent(202);

        assertNotNull(savedStudent);
        assertEquals("First Student", savedStudent.getName());
    }

    @Test
    void testAddStudentWithInvalidMarks() {

        Student student = new Student(
                203,
                "Invalid Marks",
                "invalid@test.com",
                "Java",
                150.0
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> studentService.addStudent(student)
        );

        assertNull(studentService.searchStudent(203));
    }

        @Test
    void testAddStudentWithNegativeMarks() {

        Student student = new Student(
                204,
                "Negative Marks",
                "negative@test.com",
                "Java",
                -10.0
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> studentService.addStudent(student)
        );

        assertNull(studentService.searchStudent(204));
    }

    @Test
    void testAddStudentWithEmptyName() {

        Student student = new Student(
                205,
                "   ",
                "emptyname@test.com",
                "Java",
                85.0
        );

        studentService.addStudent(student);

        assertNull(studentService.searchStudent(205));
    }

    @Test
    void testAddStudentWithEmptyEmail() {

        Student student = new Student(
                206,
                "Email Test",
                "   ",
                "Java",
                85.0
        );

        studentService.addStudent(student);

        assertNull(studentService.searchStudent(206));
    }

    @Test
    void testAddStudentWithEmptyCourse() {

        Student student = new Student(
                207,
                "Course Test",
                "course@test.com",
                "   ",
                85.0
        );

        studentService.addStudent(student);

        assertNull(studentService.searchStudent(207));
    }

    @Test
    void testUpdateStudentWithInvalidMarks() {

        Student student = new Student(
                208,
                "Update Validation",
                "updatevalidation@test.com",
                "Java",
                80.0
        );

        studentService.addStudent(student);

        boolean result = studentService.updateStudent(
                208,
                "Updated Name",
                "updated@test.com",
                "Python",
                150.0
        );

        assertFalse(result);

        Student savedStudent = studentService.searchStudent(208);

        assertNotNull(savedStudent);
        assertEquals("Update Validation", savedStudent.getName());
        assertEquals(80.0, savedStudent.getMarks());
    }

    @Test
    void testUpdateStudentWithEmptyName() {

        Student student = new Student(
                209,
                "Original Student",
                "original@test.com",
                "Java",
                80.0
        );

        studentService.addStudent(student);

        boolean result = studentService.updateStudent(
                209,
                "   ",
                "updated@test.com",
                "Python",
                90.0
        );

        assertFalse(result);

        Student savedStudent = studentService.searchStudent(209);

        assertNotNull(savedStudent);
        assertEquals("Original Student", savedStudent.getName());
        assertEquals(80.0, savedStudent.getMarks());
    }

    @Test
    void testUpdateStudentWithEmptyEmail() {

        Student student = new Student(
                210,
                "Email Update Test",
                "originalemail@test.com",
                "Java",
                80.0
        );

        studentService.addStudent(student);

        boolean result = studentService.updateStudent(
                210,
                "Updated Name",
                "   ",
                "Python",
                90.0
        );

        assertFalse(result);

        Student savedStudent = studentService.searchStudent(210);

        assertNotNull(savedStudent);
        assertEquals("Email Update Test", savedStudent.getName());
        assertEquals("originalemail@test.com", savedStudent.getEmail());
        assertEquals(80.0, savedStudent.getMarks());
    }

    @Test
    void testUpdateStudentWithEmptyCourse() {

        Student student = new Student(
                211,
                "Course Update Test",
                "courseupdate@test.com",
                "Java",
                80.0
        );

        studentService.addStudent(student);

        boolean result = studentService.updateStudent(
                211,
                "Updated Name",
                "updatedcourse@test.com",
                "   ",
                90.0
        );

        assertFalse(result);

        Student savedStudent = studentService.searchStudent(211);

        assertNotNull(savedStudent);
        assertEquals("Course Update Test", savedStudent.getName());
        assertEquals("Java", savedStudent.getCourse());
        assertEquals(80.0, savedStudent.getMarks());
    }

    @Test
    void testDeleteNonExistingStudent() {

        boolean result = studentService.deleteStudent(9999);

        assertFalse(result);
    }
}