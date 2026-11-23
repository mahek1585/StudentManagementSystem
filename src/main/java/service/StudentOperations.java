package service;

import model.Student;

public interface StudentOperations {

    void addStudent(Student student);

    void viewStudents();

    Student searchStudent(int id);

    Student searchStudent(String name);

    boolean updateStudent(int id, String name, String email,
                          String course, double marks);

    boolean deleteStudent(int id);
}