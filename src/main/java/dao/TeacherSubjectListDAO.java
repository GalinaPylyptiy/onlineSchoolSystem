package dao;

import entity.Locale;
import entity.Subject;
import entity.Teacher;

import java.util.List;

public interface TeacherSubjectListDAO {

    void addTeacherSubject(Teacher teacher, Subject subject);
    List<Subject> getSubjectsOfaTeacher(Teacher teacher);
    List<Subject> getLocaleSubjectsOfaTeacher(Teacher teacher, Locale locale);

}
