package dao.impl;

import connectionPool.ConnectionPool;
import dao.LocaleDAO;
import entity.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocaleDAOImpl implements LocaleDAO {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();
    private static final String GET_LOCALE_BY_SHORT_NAME="SELECT id, short_name, full_name FROM locale WHERE short_name=?";
    private static final String GET_ALL_LOCALES = "SELECT id, short_name, full_name FROM locale ORDER BY id";

    @Override
    public Locale getLocaleByShortName(String shortName) {
        Connection connection = connectionPool.getConnection();
        Locale locale = null;
        try(PreparedStatement statement = connection.prepareStatement(GET_LOCALE_BY_SHORT_NAME)) {
            statement.setString(1, shortName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(ID);
                String shName = resultSet.getString(SHORT_NAME);
                String name = resultSet.getString(FULL_NAME);
                locale = new Locale(id, shName, name);
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace(), e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return locale;
    }
    @Override
    public List<Locale> getAllLocales() {
        Connection connection = connectionPool.getConnection();
        List<Locale> localeList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_LOCALES)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(ID);
                String shortName = resultSet.getString(SHORT_NAME);
                String fullName = resultSet.getString(FULL_NAME);
                Locale locale = new Locale(id, shortName, fullName);
                localeList.add(locale);
            }
        } catch (SQLException e) {
            LOGGER.error(e.fillInStackTrace(), e.getCause());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return localeList;
    }
}
