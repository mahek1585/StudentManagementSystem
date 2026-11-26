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
}