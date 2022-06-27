package daoImpl;

import connectionPool.ConnectionPool;
import dao.TeacherTypeDao;
import entity.TeacherType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static constants.DBColumnNamesConstants.*;

public class TeacherTypeDAOImpl implements TeacherTypeDao {

    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private static final String GET_TYPE_BY_ID = "SELECT * FROM teacher_type WHERE id = ?";
    private static final String GET_TYPE_BY_TYPE_NAME = "SELECT * FROM teacher_type WHERE type_name = ?";
    private static final String ADD_TYPE = "INSERT INTO teacher_type (id, type_name) VALUES (?,?)";
    private static final String GET_TYPES_LIST = "SELECT* FROM teacher_type";
    private static final String UPDATE_TYPE = "UPDATE teacher_type SET id = ?, type_name = ? WHERE id = ?";
    private static final String DELETE_TYPE = "DELETE FROM teacher_type WHERE id  = ?";

    @Override
    public void addType(TeacherType type) {
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(ADD_TYPE)) {
            statement.setInt(1, type.getId());
            statement.setString(2, type.getTypeName());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public TeacherType getTypeById(int id) {
        Connection connection = connectionPool.getConnection();
        TeacherType type = new TeacherType();
        try (PreparedStatement statement = connection.prepareStatement(GET_TYPE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                type.setId(resultSet.getInt(ID));
                type.setTypeName(resultSet.getString(TYPE_NAME));
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return type;
    }
    @Override
    public TeacherType getTypeByTypeName(String typeName){
        Connection connection = connectionPool.getConnection();
        TeacherType type = new TeacherType();
      try(PreparedStatement statement = connection.prepareStatement(GET_TYPE_BY_TYPE_NAME) ) {
          statement.setString(1, typeName);
          ResultSet resultSet = statement.executeQuery();
          while (resultSet.next()){
             type.setId(resultSet.getInt(ID));
             type.setTypeName(resultSet.getString(TYPE_NAME));
          }
      } catch (SQLException e) {
          LOGGER.error(e.fillInStackTrace());
      }
      finally {
          connectionPool.releaseConnection(connection);
      }
      return type;
    }

     @Override
     public List<TeacherType> getAllTypes () {
         Connection connection = connectionPool.getConnection();
          List<TeacherType> types = new ArrayList<>();
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(GET_TYPES_LIST);
                while (resultSet.next()) {
                    TeacherType type = new TeacherType(resultSet.getInt(ID), resultSet.getString(TYPE_NAME));
                    types.add(type);
                }
            } catch (SQLException e) {
                LOGGER.error(e.fillInStackTrace());
            }
            finally {
                connectionPool.releaseConnection(connection);
            }
            return types;
        }
      @Override
      public void updateTypeById (TeacherType type, int id){
          Connection connection = connectionPool.getConnection();
            try(PreparedStatement statement = connection.prepareStatement(UPDATE_TYPE)) {
                statement.setInt(1, type.getId());
                statement.setString(2, type.getTypeName());
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
        public void deleteTypeById (int id){
            Connection connection = connectionPool.getConnection();
            try(PreparedStatement statement =connection.prepareStatement(DELETE_TYPE)){
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
