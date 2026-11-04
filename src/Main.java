
import java.util.Scanner;
import model.Student;
import service.StudentService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        StudentService studentService = new StudentService();

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1: {
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Student Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Student Course: ");
                    String course = sc.nextLine();

                    System.out.print("Enter Student Marks: ");
                    double marks = sc.nextDouble();

                    Student student = new Student(id, name, email, course, marks);

                    studentService.addStudent(student);

                    break;
                }

                case 2: {
                    studentService.viewStudents();
                    break;
                }

                case 3: {
                    System.out.print("Enter Student ID to search: ");
                    int searchId = sc.nextInt();

                    Student foundStudent = studentService.searchStudent(searchId);

                    if (foundStudent != null) {
                        System.out.println("\nStudent found!");
                        System.out.println("ID: " + foundStudent.getId());
                        System.out.println("Name: " + foundStudent.getName());
                        System.out.println("Email: " + foundStudent.getEmail());
                        System.out.println("Course: " + foundStudent.getCourse());
                        System.out.println("Marks: " + foundStudent.getMarks());
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;
                }

                case 4: {
                    System.out.print("Enter Student ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new Student Name: ");
                    String updatedName = sc.nextLine();

                    System.out.print("Enter new Student Email: ");
                    String updatedEmail = sc.nextLine();

                    System.out.print("Enter new Student Course: ");
                    String updatedCourse = sc.nextLine();

                    System.out.print("Enter new Student Marks: ");
                    double updatedMarks = sc.nextDouble();

                    boolean isUpdated = studentService.updateStudent(
                        updateId,
                        updatedName,
                        updatedEmail,
                        updatedCourse,
                        updatedMarks
                    );

                    if (isUpdated) {
                        System.out.println("Student updated successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;
                }

                case 5: {
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();

                    boolean isDeleted = studentService.deleteStudent(deleteId);

                    if (isDeleted) {
                        System.out.println("Student deleted successfully!");
                    } else {
                        System.out.println("Student not found.");
                    }

                    break;
                }

                case 6: {
                    System.out.println("Thank you for using Student Management System!");
                    break;
                }

                default: {
                    System.out.println("Invalid choice! Please try again.");
                    break;
                }
            }

        } while (choice != 6);

        sc.close();
    }
}
