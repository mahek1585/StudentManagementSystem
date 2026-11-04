
package service;

import java.util.ArrayList;
import model.Student;

public class StudentService {

    // Store multiple student objects
    private ArrayList<Student> students = new ArrayList<>();

    // Add student
    public void addStudent(Student student) {

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

        Student student = searchStudent(id);

        if (student == null) {
            return false;
        }

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
