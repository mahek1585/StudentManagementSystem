package util;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import model.Student;
import java.io.BufferedReader;
import java.io.FileReader;

public class FileHelper {

    private static final String FILE_PATH = "students.txt";

    // Create file
    public static void createFile() {

        try {
            File file = new File(FILE_PATH);

            if (file.createNewFile()) {
                System.out.println("File created successfully!");
            } else {
                System.out.println("File already exists.");
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

                String[] data = line.split("\\|");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                String course = data[3];
                double marks = Double.parseDouble(data[4]);

                Student student = new Student(
                    id, name, email, course, marks
                );

                students.add(student);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }

        return students;
    }
}