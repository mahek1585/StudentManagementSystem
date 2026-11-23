
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;
import util.DBConnection;

import java.util.ArrayList;
import java.util.List;

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
     
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                Student student = new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("course"),
                    resultSet.getDouble("marks")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }

        return students;
    }

    public Student searchStudentById(int id) {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {

                    Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("course"),
                        resultSet.getDouble("marks")
                    );

                    return student;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching student: " + e.getMessage());
        }

        return null;
    }

    public boolean updateStudent(Student student) {

        String sql = "UPDATE students SET name = ?, email = ?, course = ?, marks = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getCourse());
            preparedStatement.setDouble(4, student.getMarks());
            preparedStatement.setInt(5, student.getId());

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {

            System.out.println("Error updating student: " + e.getMessage());
            return false;
        }
    }


    public boolean deleteStudent(int id) {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;

        } catch (SQLException e) {

            System.out.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }

    


}
