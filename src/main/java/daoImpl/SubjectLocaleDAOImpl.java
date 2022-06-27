package daoImpl;
import connectionPool.ConnectionPool;
import dao.SubjectLocaleDAO;
import entity.Locale;
import entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectLocaleDAOImpl implements SubjectLocaleDAO {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_SUBJECT_LOCALE_NAME_LIST = "SELECT subject.id, subject_locale.subject_locale_name FROM subject_locale" +
            " JOIN subject ON subject.id = subject_locale.subject_id WHERE locale_id=?";
    private static final String GET_SUBJECT_BY_LOCALE_NAME ="SELECT subject.id, subject.subject_name FROM subject_locale " +
            "JOIN subject ON subject_locale.subject_id=subject.id WHERE subject_locale_name=?";
    private static final String GET_SUBJECT_LOCALE_BY_ID_AND_LOCALE = "SELECT subject_id, subject_locale.subject_locale_name FROM subject_locale" +
            " WHERE subject_id=? AND locale_id=? ";

 @Override
    public List<Subject> getSubjectLocaleNameList(Locale locale){
        List<Subject> subjectList=new ArrayList<>();
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_LOCALE_NAME_LIST)) {
            statement.setInt(1,locale.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Subject subject = new Subject();
                subject.setId(resultSet.getInt(ID));
                subject.setName(resultSet.getString(SUBJECT_LOCALE_NAME));
                subjectList.add(subject);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subjectList;
    }
    @Override
    public Subject getSubjectByLocaleName(String subjectName){
        Connection connection = connectionPool.getConnection();
        Subject subject = new Subject();
        try (PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_BY_LOCALE_NAME)) {
            statement.setString(1,subjectName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                subject.setId(resultSet.getInt(ID));
                subject.setName(resultSet.getString(SUBJECT_NAME));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subject;
    }
    @Override
    public Subject getSubjectLocaleBySubjectAndLocale(Subject subject, Locale locale){
        Connection connection = connectionPool.getConnection();
        Subject subjectLocale = new Subject();
        try (PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_LOCALE_BY_ID_AND_LOCALE)) {
            statement.setInt(1,subject.getId());
            statement.setInt(2,locale.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                subjectLocale.setId(resultSet.getInt(SUBJECT_ID));
                subjectLocale.setName(resultSet.getString(SUBJECT_LOCALE_NAME));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subjectLocale;
    }
}
