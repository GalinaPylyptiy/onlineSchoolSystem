package dao.impl;

import connectionPool.ConnectionPool;
import dao.TeacherSubjectListDAO;
import entity.Locale;
import entity.Subject;
import entity.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static constants.DBColumnNamesConstants.*;


public class TeacherSubjectDAOImpl implements TeacherSubjectListDAO {

    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_TEACHER_AND_SUBJECT = "INSERT INTO teacher_subject_list(teacher_id, subject_id) VALUES (?,?)";
    private static final String GET_ALL_SUBJECTS_OF_A_TEACHER = "SELECT subject.id, subject.subject_name FROM teacher_subject_list " +
            " JOIN subject ON teacher_subject_list.subject_id = subject.id WHERE teacher_id = ? ";
    private static final String GET_LOCALE_SUBJECTS_OF_A_TEACHER = "SELECT subject.id AS subject_id , subject_locale.subject_locale_name FROM teacher_subject_list " +
            " JOIN subject ON teacher_subject_list.subject_id = subject.id " +
            " JOIN subject_locale ON subject_locale.subject_id = subject.id" +
            " WHERE teacher_id = ? AND subject_locale.locale_id=? ";

    @Override
    public void addTeacherSubject(Teacher teacher, Subject subject) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(ADD_TEACHER_AND_SUBJECT)) {
            statement.setLong(1, teacher.getId());
            statement.setInt(2, subject.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<Subject> getSubjectsOfaTeacher(Teacher teacher) {
        Connection connection = connectionPool.getConnection();
        List<Subject> subjectList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_SUBJECTS_OF_A_TEACHER)) {
            statement.setLong(1, teacher.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(ID));
                subject.setName(resultSet.getString(SUBJECT_NAME));
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subjectList;
    }

    @Override
    public List<Subject> getLocaleSubjectsOfaTeacher(Teacher teacher, Locale locale) {
        Connection connection = connectionPool.getConnection();
        List<Subject> localeSubjectList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_LOCALE_SUBJECTS_OF_A_TEACHER)) {
          statement.setLong(1, teacher.getId());
          statement.setInt(2,locale.getId());
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()){
              Subject localeSubject = new Subject();
              localeSubject.setId(resultSet.getInt(SUBJECT_ID));
              localeSubject.setName(resultSet.getString(SUBJECT_LOCALE_NAME));
              localeSubjectList.add(localeSubject);
          }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return localeSubjectList;
    }
}
