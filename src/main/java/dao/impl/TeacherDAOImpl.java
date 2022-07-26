package dao.impl;
import connectionPool.ConnectionPool;
import dao.TeacherDAO;
import dao.TeacherSubjectListDAO;
import entity.Subject;
import entity.Teacher;
import entity.TeacherType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static constants.DBColumnNamesConstants.*;

public class TeacherDAOImpl implements TeacherDAO  {

    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private TeacherSubjectListDAO tsl= new TeacherSubjectDAOImpl();

    private static final String ADD_TEACHER = "INSERT INTO teacher (last_name,first_name, middle_name, " +
            "login, password,teacher_type_id, is_admin, is_active) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SELECT_ALL_TEACHERS = "SELECT teacher.id, last_name, first_name, middle_name, " +
            " teacher_type.type_name, teacher.is_admin FROM teacher"+
            " JOIN teacher_type ON teacher.teacher_type_id = teacher_type.id" +
            " WHERE teacher.is_active = 'true' ORDER BY teacher.id";
    private static final String GET_TEACHER_BY_ID = "SELECT teacher.id, last_name, first_name, middle_name," +
            " teacher_type.type_name, teacher.is_admin FROM teacher JOIN teacher_type ON teacher_type.id = teacher.teacher_type_id  " +
            " WHERE teacher.id = ? AND is_active = 'true'";
    private static final String GET_TEACHER_BY_LOGIN_AND_PASSWORD ="SELECT teacher.id, last_name, first_name, middle_name," +
            "teacher_type_id, teacher_type.type_name, teacher.is_admin FROM teacher " +
            "JOIN teacher_type ON teacher_type.id = teacher.teacher_type_id  " +
            "WHERE login = ? AND password = ? AND is_active = 'true'";
    private static final String DELETE_TEACHER_BY_ID = "UPDATE teacher SET is_active = 'false' WHERE id = ?";

    @Override
    public void addTeacher(Teacher teacher) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(ADD_TEACHER)) {
            statement.setString(1, teacher.getLastName());
            statement.setString(2, teacher.getFirstName());
            statement.setString(3, teacher.getMiddleName());
            statement.setString(4,teacher.getLogin());
            statement.setString(5, teacher.getPassword());
            statement.setInt(6, teacher.getType().getId());
            statement.setBoolean(7, teacher.getIsAdmin());
            statement.setBoolean(8, teacher.isActive());
            statement.execute();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }
    @Override
    public Teacher getTeacherById(Long id) {
        Connection connection = connectionPool.getConnection();
        Teacher teacher = new Teacher();
        TeacherType type = new TeacherType();
        try(PreparedStatement statement = connection.prepareStatement(GET_TEACHER_BY_ID)) {
            statement.setLong(1, id);
            ResultSet  resultSet = statement.executeQuery();
            while (resultSet.next()){
                setNewTeacher(resultSet, teacher, type);
            }
            List<Subject> subjects = tsl.getSubjectsOfaTeacher(teacher);
            teacher.setSubject(subjects);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return teacher;
    }
    @Override
    public Teacher getTeacherByLoginAndPassword(String login, String password){
        Connection connection = connectionPool.getConnection();
        Teacher teacher = new Teacher();
        TeacherType type = new TeacherType();
        try(PreparedStatement statement =connection.prepareStatement(GET_TEACHER_BY_LOGIN_AND_PASSWORD)) {
        statement.setString(1,login);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            teacher.setId(resultSet.getLong(ID));
            teacher.setLastName(resultSet.getString(LAST_NAME));
            teacher.setFirstName(resultSet.getString(FIRST_NAME));
            teacher.setMiddleName(resultSet.getString(MIDDLE_NAME));
            type.setId(resultSet.getInt(TEACHER_TYPE_ID));
            type.setTypeName(resultSet.getString(TYPE_NAME));
            teacher.setType(type);
            teacher.setAdmin(resultSet.getBoolean(IS_ADMIN));
        }
            List<Subject>subjects = tsl.getSubjectsOfaTeacher(teacher);
            teacher.setSubject(subjects);

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return teacher;
    }
     @Override
         public List<Teacher> getAllTeachers() {
         Connection connection = connectionPool.getConnection();
        TeacherSubjectListDAO tsl= new TeacherSubjectDAOImpl();
        List<Teacher>teachers = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
           ResultSet resultSet =  statement.executeQuery(SELECT_ALL_TEACHERS);
           while (resultSet.next()){
               Teacher teacher = new Teacher();
               TeacherType type = new TeacherType();
               setNewTeacher(resultSet, teacher,type);
               List<Subject>subjects = tsl.getSubjectsOfaTeacher(teacher);
               teacher.setSubject(subjects);
               teachers.add(teacher);
           }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return teachers;
    }

    @Override
    public void deleteTeacher (Long id){
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_TEACHER_BY_ID)) {
            statement.setLong(1,id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }
    private void setNewTeacher(ResultSet resultSet, Teacher teacher, TeacherType type) throws SQLException {
        teacher.setId(resultSet.getLong(ID));
        teacher.setLastName(resultSet.getString(LAST_NAME));
        teacher.setFirstName(resultSet.getString(FIRST_NAME));
        teacher.setMiddleName(resultSet.getString(MIDDLE_NAME));
        type.setTypeName(resultSet.getString(TYPE_NAME));
        teacher.setType(type);
        teacher.setAdmin(resultSet.getBoolean(IS_ADMIN));
    }
}
