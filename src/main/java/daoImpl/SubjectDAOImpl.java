package daoImpl;

import connectionPool.ConnectionPool;
import dao.SubjectDAO;
import entity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAOImpl implements SubjectDAO{

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    private static final String GET_SUBJECT_BY_ID ="SELECT * FROM subject WHERE id = ?";
    private static final String GET_SUBJECT_BY_NAME = "SELECT * FROM subject WHERE subject_name = ?";
    private static final String ADD_SUBJECT= "INSERT INTO subject (id, subject_name) VALUES (?,?)";
    private static final String GET_SUBJECTS_LIST = "SELECT* FROM subject";
    private static final String UPDATE_SUBJECT = "UPDATE subject SET id = ?, subject_name = ? WHERE id = ?";
    private static final String DELETE_SUBJECT= "DELETE FROM subject WHERE id  = ?";


    @Override
    public void addSubject(Subject subj) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(ADD_SUBJECT)) {
            statement.setInt(1, subj.getId());
            statement.setString(2, subj.getName());
            statement.execute();
        } catch (SQLException e) {
           LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public Subject getSubjectById(int id) {
        Connection connection = connectionPool.getConnection();
        Subject subject = new Subject();
        try(PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_BY_ID)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
              subject.setId(resultSet.getInt(ID));
              subject.setName(resultSet.getString(SUBJECT_NAME));
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subject ;
    }
 @Override
 public Subject getSubjectByName(String subjectName){
     Connection connection = connectionPool.getConnection();
        Subject subject = new Subject();
        try(PreparedStatement statement = connection.prepareStatement(GET_SUBJECT_BY_NAME)){
           statement.setString(1, subjectName);
           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()){
               subject.setId(resultSet.getInt(ID));
               subject.setName(resultSet.getString(SUBJECT_NAME));
           }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subject;
 }
    @Override
    public List<Subject> getAllSubjects() {
        Connection connection = connectionPool.getConnection();
        List<Subject> subjects = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_SUBJECTS_LIST);
            while (resultSet.next()){
                Subject subj = new Subject(resultSet.getInt(ID), resultSet.getString(SUBJECT_NAME));
                subjects.add(subj);
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return subjects;
    }
    @Override
    public void updateSubject(Subject subj, int id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_SUBJECT)) {
            statement.setInt(1, subj.getId());
            statement.setString(2, subj.getName());
            statement.setInt(3, id );
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }

    }

    @Override
    public void deleteSubjectById(int id) {
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement statement = connection.prepareStatement(DELETE_SUBJECT)){
            statement.setInt(1,id);
            statement.execute();
        }
        catch (SQLException e){
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }

    }
}
