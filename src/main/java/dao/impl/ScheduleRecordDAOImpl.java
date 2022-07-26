package dao.impl;

import connectionPool.ConnectionPool;
import dao.ScheduleRecordDao;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

  public class ScheduleRecordDAOImpl implements ScheduleRecordDao {

      private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
      private ConnectionPool connectionPool = ConnectionPool.getInstance();
      private static final String ADD_RECORD = "INSERT INTO schedule (student_level_id, subject_id, teacher_id, time_of_the_lesson, day_of_week, is_active)  " +
           "VALUES (?, ?, ?, ?, ?,?) ";
   private static final String GET_LEVELS_OF_TEACHER = "SELECT DISTINCT student_level.id ,student_level.level_name FROM schedule " +
           " JOIN student_level ON student_level.id = schedule.student_level_id WHERE teacher_id = ? ";
   private static final String GET_ALL_RECORDS ="SELECT schedule.id, student_level_id, student_level.level_name AS level,subject_id, subject.subject_name AS subject, " +
           " teacher_id, teacher.last_name AS teacher_last_name, teacher.first_name AS teacher_first_name, day_of_week,schedule.time_of_the_lesson " +
           " FROM schedule JOIN student_level ON student_level.id = schedule.student_level_id" +
           " JOIN subject ON subject.id = schedule.subject_id" +
           " JOIN teacher ON teacher.id = schedule.teacher_id WHERE schedule.is_active = 'true' ORDER BY schedule.id ";
   private static final String GET_SCHEDULE_OF_A_TEACHER = "SELECT schedule.id,time_of_the_lesson," +
           " student_level.level_name,subject.subject_name,day_of_week FROM schedule " +
           " JOIN student_level ON schedule.student_level_id=student_level.id " +
           " JOIN subject ON schedule.subject_id=subject.id WHERE teacher_id = ? AND schedule.is_active = 'true' ORDER BY day_of_week ";
   private static final String GET_SCHEDULE_OF_A_STUDENT = "SELECT schedule.id,time_of_the_lesson," +
           " subject.subject_name,day_of_week FROM schedule " +
           " JOIN subject ON schedule.subject_id=subject.id WHERE student_level_id = ? AND schedule.is_active = 'true' ORDER BY day_of_week  ";
   private static final String DELETE_RECORD_BY_ID = "UPDATE schedule SET is_active = 'false' WHERE id = ?";

     @Override
     public void addNewRecord(ScheduleRecord record) {
     Connection connection = connectionPool.getConnection();
       try (PreparedStatement statement = connection.prepareStatement(ADD_RECORD)) {
          addAndUpdateSchedule(record, statement);
            statement.execute();
         } catch (SQLException e) {
             LOGGER.error(e.getMessage(), e.fillInStackTrace());
       }
          finally {
        connectionPool.releaseConnection(connection);
        }
     }

   @Override
   public List<ScheduleRecord> getAllRecords() {
    Connection connection = connectionPool.getConnection();
    List<ScheduleRecord> records = new ArrayList<>();
       try (Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_RECORDS)) {
            while (resultSet.next()){
            ScheduleRecord record = new ScheduleRecord();
             Level level = new Level();
             Subject subject = new Subject();
             Teacher teacher = new Teacher();
             setScheduleRecords(resultSet, record, level, subject, teacher);
              records.add(record);
           }
     } catch (SQLException e) {
        LOGGER.error(e.getMessage(), e.fillInStackTrace());
         }
        finally {
        connectionPool.releaseConnection(connection);
    }
    return records;
  }

      @Override
      public List<ScheduleRecord> getScheduleOfaTeacher(Teacher teacher) {
          Connection connection = connectionPool.getConnection();
       List<ScheduleRecord> scheduleRecordList =new ArrayList<>();
       try(PreparedStatement statement = connection.prepareStatement(GET_SCHEDULE_OF_A_TEACHER)) {
           statement.setLong(1,teacher.getId());
           ResultSet rs = statement.executeQuery();
           while (rs.next()){
               ScheduleRecord record = new ScheduleRecord();
               Level level = new Level();
               Subject subject = new Subject();
               record.setId(rs.getLong(ID));
               record.setTime(rs.getTime(LESSON_TIME).toLocalTime());
               level.setName(rs.getString(LEVEL_NAME));
               record.setLevel(level);
               subject.setName(rs.getString(SUBJECT_NAME));
               record.setSubject(subject);
               DayOfWeek dayOfWeek = DayOfWeek.of(rs.getInt(DAY_OF_WEEK));
               record.setDayOfWeek(dayOfWeek);
               scheduleRecordList.add(record);
           }
       } catch (SQLException e) {
           LOGGER.error(e.getMessage(),e.fillInStackTrace());
       }
       finally {
           connectionPool.releaseConnection(connection);
       }
          return scheduleRecordList;
      }

      @Override
      public List<ScheduleRecord> getScheduleOfaStudent(Level level) {
          Connection connection = connectionPool.getConnection();
          List<ScheduleRecord> scheduleRecordList =new ArrayList<>();
          try(PreparedStatement statement = connection.prepareStatement(GET_SCHEDULE_OF_A_STUDENT)) {
              statement.setLong(1,level.getId());
              ResultSet rs = statement.executeQuery();
              while (rs.next()){
                  ScheduleRecord record = new ScheduleRecord();
                  Subject subject = new Subject();
                  record.setId(rs.getLong(ID));
                  record.setTime(rs.getTime(LESSON_TIME).toLocalTime());
                  subject.setName(rs.getString(SUBJECT_NAME));
                  record.setSubject(subject);
                  DayOfWeek dayOfWeek = DayOfWeek.of(rs.getInt(DAY_OF_WEEK));
                  record.setDayOfWeek(dayOfWeek);
                  scheduleRecordList.add(record);
              }
          } catch (SQLException e) {
              LOGGER.error(e.getMessage(),e.fillInStackTrace());
          }
          finally {
              connectionPool.releaseConnection(connection);
          }
          return scheduleRecordList;
      }

      @Override
      public List<Level> getLevelsOfTeacher(Teacher teacher) {
       List<Level> levels = new ArrayList<>();
          Connection connection = connectionPool.getConnection();
       try(PreparedStatement statement = connection.prepareStatement(GET_LEVELS_OF_TEACHER)) {
           statement.setLong(1,teacher.getId());
           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()){
               Level level = new Level();
               level.setId(resultSet.getInt(ID));
               level.setName(resultSet.getString(LEVEL_NAME));
               levels.add(level);
           }
       } catch (SQLException e) {
           LOGGER.error(e.getMessage(),e.fillInStackTrace());
       }
       finally {
           connectionPool.releaseConnection(connection);
       }
          return levels;
      }

   @Override
      public void deleteRecord(Long id) {
       Connection connection = connectionPool.getConnection();
    try(PreparedStatement statement = connection.prepareStatement(DELETE_RECORD_BY_ID)) {
     statement.setLong(1, id);
     statement.execute();
    } catch (SQLException e) {
        LOGGER.error(e.getMessage(),e.getMessage());
    }
    finally {
        connectionPool.releaseConnection(connection);
    }
   }

     private void setScheduleRecords(ResultSet resultSet, ScheduleRecord record, Level level, Subject subject, Teacher teacher) throws SQLException {
    record.setId(resultSet.getLong(ID));
    level.setId(resultSet.getInt(STUDENT_LEVEL_ID));
    level.setName(resultSet.getString(LEVEL));
    record.setLevel(level);
    subject.setId(resultSet.getInt(SUBJECT_ID));
    subject.setName(resultSet.getString(SUBJECT));
    record.setSubject(subject);
    teacher.setId(resultSet.getLong(TEACHER_ID));
    teacher.setLastName(resultSet.getString(TEACHER_LAST_NAME));
    teacher.setFirstName(resultSet.getString(TEACHER_FIRST_NAME));
    record.setTeacher(teacher);
    DayOfWeek day = DayOfWeek.of(resultSet.getInt(DAY_OF_WEEK));
    record.setDayOfWeek(day);
    record.setTime(resultSet.getTime(LESSON_TIME).toLocalTime());
   }
   private void addAndUpdateSchedule(ScheduleRecord record, PreparedStatement statement ) throws SQLException {
    statement.setInt(1, record.getLevel().getId());
    statement.setInt(2, record.getSubject().getId());
    statement.setLong(3, record.getTeacher().getId());
    statement.setTime(4, Time.valueOf(record.getTime()));
    statement.setInt(5, record.getDayOfWeek().getValue());
    statement.setBoolean(6,record.isActive());
   }
  }
