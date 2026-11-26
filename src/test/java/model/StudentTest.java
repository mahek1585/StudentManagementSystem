
package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void testStudentConstructorAndGetters() {

        Student student = new Student(
            101,
            "Mahek",
            "mahek@gmail.com",
            "Java",
            95
        );

        assertEquals(101, student.getId());
        assertEquals("Mahek", student.getName());
        assertEquals("mahek@gmail.com", student.getEmail());
        assertEquals("Java", student.getCourse());
        assertEquals(95.0, student.getMarks());
    }

    @Test
void testStudentSetters() {

    Student student = new Student(
        101,
        "Mahek",
        "mahek@gmail.com",
        "Java",
        95
    );

    student.setName("Heena");
    student.setEmail("heena@gmail.com");
    student.setCourse("Python");
    student.setMarks(90);

    assertEquals("Heena", student.getName());
    assertEquals("heena@gmail.com", student.getEmail());
    assertEquals("Python", student.getCourse());
    assertEquals(90.0, student.getMarks());
}
}
