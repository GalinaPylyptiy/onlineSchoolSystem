package dao;
import entity.*;

import java.time.LocalDate;
import java.util.List;

public interface StudentAssessmentDAO {

    void addAssessmentRecord(StudentAssessment assessment);
    List<StudentAssessment> getAssessmentByLevelAndSubject(CurriculumRecord curriculumRecord);
    List<StudentAssessment> getGradesOfStudentForTimePeriod(Student student, Subject subject,LocalDate since, LocalDate until);
    Double getAverageGradeForTimePeriod(Student student, Subject subject,LocalDate since, LocalDate until);
    void deleteAssessmentRecord(Long id);


}
