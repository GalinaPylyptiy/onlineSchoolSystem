package dao;
import entity.Student;
import java.util.List;

public interface StudentDAO {

    void addStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    List<Student> getListOfStudentsByLevelName(String levelName);
    void deleteStudent(Long id);
    Student getStudentByLoginAndPassword(String login, String password);

}
