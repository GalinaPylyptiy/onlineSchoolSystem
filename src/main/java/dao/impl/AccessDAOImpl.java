package dao.impl;

import connectionPool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AccessDAOImpl {

    private  final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private static final String GET_TEACHERS_LOGIN_AND_PASSWORD = "SELECT teacher.login ,teacher.password FROM teacher";
    private static final String GET_STUDENTS_LOGIN_AND_PASSWORD = "SELECT student.login ,student.password FROM student";
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Map<String, String> getTeachersLoginAndPasswords() {
        return getLoginPasswordMap(GET_TEACHERS_LOGIN_AND_PASSWORD);
    }
    public Map<String, String> getStudentsLoginAndPasswords() {
        return getLoginPasswordMap(GET_STUDENTS_LOGIN_AND_PASSWORD);
    }

    private Map<String, String> getLoginPasswordMap(String request) {
        Connection connection = connectionPool.getConnection();
        Map<String, String> registration = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(request);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                registration.put(resultSet.getString(LOGIN), resultSet.getString(PASSWORD));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return registration;
    }
}

