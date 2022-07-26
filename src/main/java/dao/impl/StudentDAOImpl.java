package dao.impl;

import connectionPool.ConnectionPool;
import dao.StudentDAO;
import entity.Level;
import entity.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static constants.DBColumnNamesConstants.*;

public class StudentDAOImpl implements StudentDAO{

    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_STUDENT = "INSERT INTO student (last_name,first_name, middle_name, " +
            "login, password, level_id, is_active ) VALUES (?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_STUDENTS = "SELECT student.id, last_name, first_name, middle_name, student_level.level_name " +
            "FROM student JOIN student_level ON student.level_id = student_level.id  WHERE is_active = 'true' ORDER BY student.id ";
    private static final String GET_STUDENT_BY_ID = "SELECT student.id, last_name, first_name, middle_name, student_level.level_name " +
            "FROM student JOIN student_level ON student.level_id = student_level.id " +
            "WHERE student.id = ? AND is_active = 'true'";
    private static final String GET_LIST_OF_STUDENTS_BY_LEVEL = "SELECT student.id, last_name, first_name, middle_name, student_level.level_name " +
            "FROM student JOIN student_level ON student.level_id = student_level.id WHERE student_level.level_name = ? AND is_active = 'true' ";
    private static final String GET_STUDENT_BY_LOGIN_AND_PASSWORD ="SELECT student.id, last_name, first_name, middle_name, student_level.level_name " +
            "FROM student JOIN student_level ON student.level_id = student_level.id WHERE login = ? AND password = ? AND is_active = 'true'";
    private static final String DELETE_STUDENT_BY_ID = "UPDATE student SET is_active ='false' WHERE id = ?";


    @Override
    public void addStudent(Student student) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement =connection.prepareStatement(ADD_STUDENT)){
          statement.setString(1, student.getLastName());
          statement.setString(2,student.getFirstName());
          statement.setString(3,student.getMiddleName());
          statement.setString(4, student.getLogin());
          statement.setString(5, student.getPassword());
          statement.setInt(6, student.getLevel().getId());
          statement.setBoolean(7,student.isActive());
          statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }
    @Override
    public Student getStudentById(Long id) {
        Connection connection = connectionPool.getConnection();
        Student student = new Student();
        Level level = new Level();
        try(PreparedStatement statement =connection.prepareStatement(GET_STUDENT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                setNewStudent(result, student, level);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        Connection connection = connectionPool.getConnection();
        List <Student> students = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
           ResultSet resultSet =  statement.executeQuery(SELECT_ALL_STUDENTS);
            while (resultSet.next()){
                Student student = new Student();
                Level level = new Level();
                setNewStudent(resultSet, student, level);
                students.add(student);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return students;
    }

    @Override
    public List<Student> getListOfStudentsByLevelName(String levelName){
        Connection connection = connectionPool.getConnection();
        List<Student> students = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_LIST_OF_STUDENTS_BY_LEVEL)) {
         statement.setString(1, levelName);
         ResultSet resultSet = statement.executeQuery();
         while (resultSet.next()){
             Student student = new Student();
             Level level = new Level();
             setNewStudent(resultSet, student, level);
             students.add(student);
         }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return students;
    }

    @Override
    public Student getStudentByLoginAndPassword(String login, String password) {
        Connection connection = connectionPool.getConnection();
        Student student = new Student();
        Level level = new Level();
        try (PreparedStatement statement = connection.prepareStatement(GET_STUDENT_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                setNewStudent(resultSet, student, level);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return student;
    }

    @Override
    public void deleteStudent(Long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_BY_ID)){
          statement.setLong(1,id);
          statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    private void setNewStudent(ResultSet resultSet, Student student, Level level) throws SQLException {
        student.setId(resultSet.getLong(ID));
        student.setLastName(resultSet.getString(LAST_NAME));
        student.setFirstName(resultSet.getString(FIRST_NAME));
        student.setMiddleName(resultSet.getString(MIDDLE_NAME));
        level.setName(resultSet.getString(LEVEL_NAME));
        student.setLevel(level);
    }
}
