package dao.impl;

import connectionPool.ConnectionPool;
import dao.TeacherTypeLocaleDAO;
import entity.Locale;
import entity.TeacherType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static constants.DBColumnNamesConstants.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherTypeLocaleDAOImpl implements TeacherTypeLocaleDAO {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String GET_TEACHER_TYPE_LOCALE_LIST= "SELECT teacher_type.id, locale_type_name FROM teacher_type_locale" +
            " JOIN teacher_type ON teacher_type.id = teacher_type_locale.teacher_type_id WHERE locale_id =?";
    private static final String GET_TEACHER_TYPE_BY_LOCALE_TYPE_NAME = "SELECT teacher_type.id, teacher_type.type_name FROM teacher_type_locale " +
            " JOIN teacher_type ON teacher_type.id = teacher_type_locale.teacher_type_id" +
            " WHERE teacher_type_locale.locale_type_name = ?";
     @Override
    public List<TeacherType> getTeacherTypeLocaleList(Locale locale) {
        Connection connection = connectionPool.getConnection();
        List<TeacherType> teacherTypeList = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(GET_TEACHER_TYPE_LOCALE_LIST)){
            statement.setInt(1, locale.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                TeacherType teacherTypeLocale = new TeacherType();
                teacherTypeLocale.setId(resultSet.getInt(ID));
                teacherTypeLocale.setTypeName(resultSet.getString(LOCALE_TYPE_NAME));
                teacherTypeList.add(teacherTypeLocale);
            }
        }catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return teacherTypeList;
    }
    @Override
    public TeacherType getTeacherTypeByLocaleTypeName(String localeTypeName){
        Connection connection = connectionPool.getConnection();
        TeacherType teacherType = new TeacherType();
        try(PreparedStatement statement = connection.prepareStatement(GET_TEACHER_TYPE_BY_LOCALE_TYPE_NAME)){
            statement.setString(1, localeTypeName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                teacherType.setId(resultSet.getInt(ID));
                teacherType.setTypeName(resultSet.getString(TYPE_NAME));
            }
        }catch (SQLException e) {
            LOGGER.error(e.getMessage(),e.fillInStackTrace());
        }
        finally {
            connectionPool.releaseConnection(connection);
        }
        return teacherType;
     }
}
