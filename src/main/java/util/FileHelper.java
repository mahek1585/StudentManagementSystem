
package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.Student;

public class FileHelper {

    private static final String FILE_PATH = "students.txt";

    // Create file automatically if it does not exist
    public static void createFile() {

        File file = new File(FILE_PATH);

        try {
            if (file.createNewFile()) {
                System.out.println("File created successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    // Save students to file
    public static void saveStudents(List<Student> students) {

        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Student student : students) {

                writer.write(
                    student.getId() + "|" +
                    student.getName() + "|" +
                    student.getEmail() + "|" +
                    student.getCourse() + "|" +
                    student.getMarks()
                );

                writer.newLine();
            }

            System.out.println("Student data saved successfully!");

        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // Load students from file
    public static List<Student> loadStudents() {

        List<Student> students = new ArrayList<>();

        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(FILE_PATH))) {

            String line;

            while ((line = reader.readLine()) != null) {

                try {
                    String[] data = line.split("\\|", -1);

                    if (data.length != 5) {
                        System.out.println("Skipping invalid student record.");
                        continue;
                    }

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String course = data[3];
                    double marks = Double.parseDouble(data[4]);

                    if (name.trim().isEmpty()
                            || email.trim().isEmpty()
                            || course.trim().isEmpty()
                            || marks < 0 || marks > 100) {

                        System.out.println("Skipping invalid student data.");
                        continue;
                    }

                    Student student = new Student(
                        id, name, email, course, marks
                    );

                    students.add(student);

                } catch (NumberFormatException e) {
                    System.out.println("Skipping corrupted student record.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

        return students;
    }
}
