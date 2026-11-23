
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

        private Student mapStudent(ResultSet resultSet) throws SQLException {

        return new Student(
            resultSet.getInt("id"),
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getString("course"),
            resultSet.getDouble("marks")
        );
    }

        public boolean addStudent(Student student) throws SQLException {

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
            }
    }

        
     
    public List<Student> getAllStudents() throws SQLException {

        List<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM students";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                students.add(mapStudent(resultSet));
            }
        }

        return students;
    }

    public Student searchStudentById(int id) throws SQLException {

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    return mapStudent(resultSet);
                }
            }
        }

        return null;
    }
    public boolean updateStudent(Student student) throws SQLException {

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
        }
    }


    public boolean deleteStudent(int id) throws SQLException {

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, id);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;
        }
    }

    


}
