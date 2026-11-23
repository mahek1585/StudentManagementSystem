
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Student;
import util.DBConnection;

public class StudentDAO {

    public boolean addStudent(Student student) {

        String sql = "INSERT INTO students (id, name, email, course, marks) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getCourse());
            preparedStatement.setDouble(5, student.getMarks());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {

            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
     
    public void getAllStudents() {

        String sql = "SELECT * FROM students";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String course = resultSet.getString("course");
                double marks = resultSet.getDouble("marks");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Course: " + course);
                System.out.println("Marks: " + marks);
                System.out.println("-------------------------");
            }

            if (!found) {
                System.out.println("No students found!");
            }

        } catch (SQLException e) {

            System.out.println("Error fetching students: " + e.getMessage());
        }
    }

    public void searchStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (java.sql.ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    System.out.println("Student Found!");
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Email: " + resultSet.getString("email"));
                    System.out.println("Course: " + resultSet.getString("course"));
                    System.out.println("Marks: " + resultSet.getDouble("marks"));

                } else {
                    System.out.println("Student not found!");
                }
            }

        } catch (SQLException e) {

            System.out.println("Error searching student: " + e.getMessage());
        }
    }



}
