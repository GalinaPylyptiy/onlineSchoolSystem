package dao;

import entity.CurriculumRecord;
import entity.Level;
import entity.Subject;
import entity.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface CurriculumRecordDAO {
    void addCurriculumRecord(CurriculumRecord record);
    CurriculumRecord getRecordById(long id);
    List<CurriculumRecord> getAllRecordsByLevelSubjectAndTeacher(Level level, Subject subject, Teacher teacher);
    void updateCurriculumRecord(CurriculumRecord record,long id);
    void deleteCurriculumRecord(Long id);
    List<CurriculumRecord> getCurriculumRecordsByLevelAndSubject(Level level, Subject subject);
}
