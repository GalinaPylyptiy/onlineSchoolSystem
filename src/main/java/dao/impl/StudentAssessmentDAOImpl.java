package dao.impl;
import connectionPool.ConnectionPool;
import dao.StudentAssessmentDAO;
import entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentAssessmentDAOImpl implements StudentAssessmentDAO {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String ADD_ASSESSMENT_RECORD = "INSERT INTO student_assessment (curriculum_id, student_id, grade, " +
            "assessment_date, is_active) VALUES(?,?,?,?,?)";
    private static final String GET_ASSESSMENT_OF_STUDENTS_BY_LEVEL_AND_SUBJECT = "SELECT student_assessment.id, student_assessment.student_id, student.last_name, " +
            "student.first_name,student_assessment.curriculum_id, curriculum.lesson_date,curriculum.subject_id, grade, curriculum.lesson_theme " +
            "FROM student_assessment " +
            "JOIN student ON student.id = student_assessment.student_id " +
            "JOIN curriculum ON curriculum.id = student_assessment.curriculum_id " +
            "WHERE curriculum.student_level_id =? AND curriculum.subject_id = ? AND student_assessment.is_active = 'true'";
    private static final String GET_GRADES_OF_STUDENT_FOR_TIME_PERIOD =  "SELECT grade,curriculum_id,curriculum.lesson_date," +
            "curriculum.lesson_theme FROM student_assessment " +
            "JOIN curriculum ON curriculum.id = student_assessment.curriculum_id " +
            "WHERE student_assessment.is_active = 'true' AND student_assessment.student_id=? AND curriculum.subject_id = ? AND curriculum.lesson_date BETWEEN ? AND ? ";
    private static final String GET_AVG_GRADE_FOR_TIME_PERIOD ="SELECT AVG (grade) AS average_grade FROM student_assessment " +
            "JOIN curriculum ON curriculum.id = student_assessment.curriculum_id " +
            "WHERE student_assessment.student_id=? AND curriculum.subject_id = ? AND curriculum.lesson_date BETWEEN ? AND ? ";
    private static final String DELETE_ASSESSMENT = "UPDATE student_assessment SET is_active = 'false' WHERE id = ?";


    @Override
    public void addAssessmentRecord(StudentAssessment assessment) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(ADD_ASSESSMENT_RECORD)) {
            statement.setLong(1, assessment.getCurriculumRecord().getId());
            statement.setLong(2,assessment.getStudent().getId());
            statement.setInt(3,assessment.getGrade());
            statement.setDate(4, Date.valueOf(assessment.getAssessmentDate()));
            statement.setBoolean(5,assessment.isActive());
            statement.execute();

        } catch (SQLException e) {
           LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }

    }

   @Override
    public List<StudentAssessment> getAssessmentByLevelAndSubject(CurriculumRecord curriculumRecord){
        Connection connection = connectionPool.getConnection();
        List<StudentAssessment> assessmentList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_ASSESSMENT_OF_STUDENTS_BY_LEVEL_AND_SUBJECT)) {
            statement.setInt(1, curriculumRecord.getLevel().getId());
            statement.setInt(2, curriculumRecord.getSubject().getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                StudentAssessment assessment = new StudentAssessment();
                Student student = new Student();
                CurriculumRecord record = new CurriculumRecord();
                Subject subject = new Subject();
                assessment.setId(resultSet.getLong(ID));
                student.setId(resultSet.getLong(STUDENT_ID));
                student.setLastName(resultSet.getString(LAST_NAME));
                student.setFirstName(resultSet.getString(FIRST_NAME));
                assessment.setStudent(student);
                record.setId(resultSet.getLong(CURRICULUM_ID));
                record.setLessonDate(resultSet.getDate(LESSON_DATE).toLocalDate());
                subject.setId(resultSet.getInt(SUBJECT_ID));
                record.setSubject(subject);
                assessment.setGrade(resultSet.getInt(GRADE));
                record.setLessonTheme(resultSet.getString(LESSON_THEME));
                assessment.setCurriculumRecord(record);
                assessmentList.add(assessment);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e.fillInStackTrace() );
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return assessmentList;
    }


    @Override
    public List<StudentAssessment> getGradesOfStudentForTimePeriod(Student student, Subject subject, LocalDate since, LocalDate until) {
        Connection connection = connectionPool.getConnection();
        List<StudentAssessment> assessmentList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_GRADES_OF_STUDENT_FOR_TIME_PERIOD)) {
            statement.setLong(1, student.getId());
            statement.setInt(2,subject.getId());
            statement.setDate(3,Date.valueOf(since));
            statement.setDate(4, Date.valueOf(until));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                StudentAssessment assessment = new StudentAssessment();
                CurriculumRecord curriculumRecord = new CurriculumRecord();
                assessment.setGrade(resultSet.getInt(GRADE));
                curriculumRecord.setId(resultSet.getLong(CURRICULUM_ID));
                curriculumRecord.setLessonDate(resultSet.getDate(LESSON_DATE).toLocalDate());
                curriculumRecord.setLessonTheme(resultSet.getString(LESSON_THEME));
                assessment.setCurriculumRecord(curriculumRecord);
                assessmentList.add(assessment);
            }
        } catch (SQLException e) {
           LOGGER.error(e.getMessage(), e.fillInStackTrace());

        } finally {
            connectionPool.releaseConnection(connection);
        }
        return assessmentList;
    }
    @Override
    public Double getAverageGradeForTimePeriod(Student student, Subject subject, LocalDate since, LocalDate until) {
        Double grade = null;
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(GET_AVG_GRADE_FOR_TIME_PERIOD)) {
            statement.setLong(1, student.getId());
            statement.setInt(2,subject.getId());
            statement.setDate(3,Date.valueOf(since));
            statement.setDate(4,Date.valueOf(until));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                grade = resultSet.getDouble(AVERAGE_GRADE);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        } finally {
            connectionPool.releaseConnection(connection);
        }
        return grade;
    }

    @Override
    public void deleteAssessmentRecord(Long id){
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_ASSESSMENT)){
           statement.setLong(1, id);
           statement.execute();
        } catch (SQLException e) {
           LOGGER.error(e.getMessage(),e.getMessage());
        } finally {
        connectionPool.releaseConnection(connection);
    }
    }
}
