package dao;
import entity.Level;
import entity.Subject;
import entity.Teacher;

import java.sql.SQLException;
import java.util.List;
public interface TeacherDAO {

    void addTeacher(Teacher teacher);
    Teacher getTeacherById(Long id);
    List<Teacher> getAllTeachers();
    Teacher getTeacherByLoginAndPassword(String login, String password);
    void deleteTeacher (Long id);

}
