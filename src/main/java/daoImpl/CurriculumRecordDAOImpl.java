package daoImpl;

import connectionPool.ConnectionPool;
import dao.CurriculumRecordDAO;
import entity.CurriculumRecord;
import entity.Level;
import entity.Subject;
import entity.Teacher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static constants.DBColumnNamesConstants.*;

public class CurriculumRecordDAOImpl implements CurriculumRecordDAO {
    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String ADD_NEW_RECORD = "INSERT INTO curriculum(lesson_date, subject_id," +
            "student_level_id, teacher_id, lesson_theme, home_task, is_active) VALUES(?,?,?,?,?,?,?)";
    private static final String GET_CURRICULUM_RECORDS_BY_LEVEL_SUBJECT= "SELECT curriculum.id, lesson_date, lesson_theme, home_task FROM curriculum" +
            " WHERE student_level_id = ? AND subject_id = ? AND is_active = 'true'";
    private static final String GET_ALL_RECORDS_BY_LEVEL_SUBJECT_TEACHER = "SELECT curriculum.id, lesson_date, lesson_theme, home_task FROM curriculum" +
            " WHERE student_level_id = ? AND subject_id = ? AND teacher_id  = ? AND is_active = 'true' ORDER BY lesson_date";
    private static final String UPDATE_RECORD = "UPDATE curriculum SET lesson_date = ?,lesson_theme=?, home_task = ? WHERE curriculum.id = ?";
    private static final String GET_RECORD_BY_ID = "SELECT * FROM curriculum WHERE id = ? AND is_active = 'true'";
    private static final String DELETE_RECORD = "UPDATE curriculum SET is_active = 'false' WHERE curriculum.id = ?";



    @Override
    public void addCurriculumRecord(CurriculumRecord record) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(ADD_NEW_RECORD)) {
            statement.setDate(1,Date.valueOf(record.getLessonDate()));
            statement.setInt(2,record.getSubject().getId());
            statement.setInt(3,record.getLevel().getId());
            statement.setLong(4,record.getTeacher().getId());
            statement.setString(5,record.getLessonTheme());
            statement.setString(6, record.getHomeTask());
            statement.setBoolean(7, record.isActive());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace(), e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<CurriculumRecord> getCurriculumRecordsByLevelAndSubject(Level level, Subject subject) {
        Connection connection = connectionPool.getConnection();
        List<CurriculumRecord> recordList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_CURRICULUM_RECORDS_BY_LEVEL_SUBJECT)){
            statement.setInt(1,level.getId());
            statement.setInt(2,subject.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                CurriculumRecord record = new CurriculumRecord();
                record.setId(resultSet.getLong(ID));
                record.setLessonDate(resultSet.getDate(LESSON_DATE).toLocalDate());
                record.setLessonTheme(resultSet.getString(LESSON_THEME));
                record.setHomeTask(resultSet.getString(HOME_TASK));
                recordList.add(record);
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace(), e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return recordList;
    }

    @Override
    public CurriculumRecord getRecordById(long id) {
        Connection connection = connectionPool.getConnection();
        CurriculumRecord record = new CurriculumRecord();
        Subject subject = new Subject();
        Level level = new Level();
        Teacher teacher = new Teacher();
        try(PreparedStatement statement = connection.prepareStatement(GET_RECORD_BY_ID)) {
            statement.setLong(1,id );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                record.setId(resultSet.getLong(ID));
                record.setLessonDate(resultSet.getDate(LESSON_DATE).toLocalDate());
                subject.setId(resultSet.getInt(SUBJECT_ID));
                level.setId(resultSet.getInt(STUDENT_LEVEL_ID));
                teacher.setId(resultSet.getLong(TEACHER_ID));
                record.setLessonTheme(resultSet.getString(LESSON_THEME));
                record.setHomeTask(resultSet.getString(HOME_TASK));
                record.setSubject(subject);
                record.setLevel(level);
                record.setTeacher(teacher);
            }
        } catch (SQLException e) {
            LOGGER.error( e.fillInStackTrace(), e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return record;
    }

    @Override
    public List<CurriculumRecord> getAllRecordsByLevelSubjectAndTeacher(Level level, Subject subject, Teacher teacher) {
        List<CurriculumRecord> records = new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_RECORDS_BY_LEVEL_SUBJECT_TEACHER)) {
            statement.setInt(1, level.getId());
            statement.setInt(2, subject.getId());
            statement.setLong(3, teacher.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                CurriculumRecord record = new CurriculumRecord();
                record.setId(resultSet.getLong(ID));
                record.setLessonDate(resultSet.getDate(LESSON_DATE).toLocalDate());
                record.setLessonTheme(resultSet.getString(LESSON_THEME));
                record.setHomeTask(resultSet.getString(HOME_TASK));
                records.add(record);
            }
        } catch (SQLException e) {
            LOGGER.error( e.fillInStackTrace() , e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return records;
    }


    @Override
    public void updateCurriculumRecord(CurriculumRecord record, long id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_RECORD)) {
            statement.setDate(1,Date.valueOf(record.getLessonDate()));
            statement.setString(2,record.getLessonTheme());
            statement.setString(3, record.getHomeTask());
            statement.setLong(4, id);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error( e.fillInStackTrace() , e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteCurriculumRecord(Long id) {
        Connection connection = connectionPool.getConnection();
      try(PreparedStatement statement = connection.prepareStatement(DELETE_RECORD)) {
          statement.setLong(1,id);
          statement.execute();
      } catch (SQLException e) {
          LOGGER.error(e.fillInStackTrace() , e.getCause());
      }
      finally {
          connectionPool.releaseConnection(connection);
      }
    }
}
