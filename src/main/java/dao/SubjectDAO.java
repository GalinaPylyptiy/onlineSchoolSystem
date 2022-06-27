package dao;
import entity.Subject;
import java.util.List;

public interface SubjectDAO {
    void addSubject(Subject subj);
    Subject getSubjectById(int id);
    Subject getSubjectByName(String subjectName);
    List<Subject> getAllSubjects();
    void updateSubject(Subject subj, int id);
    void deleteSubjectById(int id);
}
