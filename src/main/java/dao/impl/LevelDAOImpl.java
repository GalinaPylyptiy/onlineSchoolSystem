package dao.impl;
import connectionPool.ConnectionPool;
import dao.LevelDAO;
import entity.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LevelDAOImpl implements LevelDAO {

    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_LEVEL_BY_LEVEL_NAME = "SELECT * FROM student_level WHERE level_name = ?";
    private static final String GET_LEVELS_LIST = "SELECT* FROM student_level";

    @Override
    public Level getLevelByLevelName(String levelName){
        Connection connection = connectionPool.getConnection();
       Level level = new Level();
       try(PreparedStatement statement = connection.prepareStatement(GET_LEVEL_BY_LEVEL_NAME)) {
           statement.setString(1, levelName);
           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()){
               level.setId(resultSet.getInt(ID));
               level.setName(resultSet.getString(LEVEL_NAME));
           }
       } catch (SQLException e) {
           LOGGER.error(e.fillInStackTrace(), e.getCause());
       }
       finally {
           connectionPool.releaseConnection(connection);
       }
        return level;
    }

    @Override
    public List<Level> getAllLevels() {
        Connection connection = connectionPool.getConnection();
        List<Level> levels = new ArrayList<>();
        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_LEVELS_LIST);
            while (resultSet.next()){
                Level level = new Level(resultSet.getInt(ID), resultSet.getString(LEVEL_NAME));
                levels.add(level);
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace(), e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return levels;
    }

}
