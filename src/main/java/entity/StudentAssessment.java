package entity;

import java.time.LocalDate;
import java.util.Objects;

public class StudentAssessment extends BaseEntity {
    private static final long serialVersionUID = 7297457688706372616L;

    private CurriculumRecord record;
    private Student student;
    private Integer grade;
    private LocalDate assessmentDate;

    public StudentAssessment(){}
    public StudentAssessment(CurriculumRecord record, Student student, int grade, LocalDate assessmentDate) {
        this.record = record;
        this.student = student;
        this.grade = grade;
        this.assessmentDate = assessmentDate;
    }

    public CurriculumRecord getCurriculumRecord() {
        return record;
    }

    public void setCurriculumRecord(CurriculumRecord record) {
        this.record = record;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public LocalDate getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentAssessment that = (StudentAssessment) o;
        return  Objects.equals(id, that.id)&&
                Objects.equals(record, that.record) &&
                Objects.equals(student, that.student) &&
                Objects.equals(grade, that.grade) &&
                Objects.equals(assessmentDate, that.assessmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(record, student, grade, assessmentDate);
    }

    @Override
    public String toString() {
        return "StudentEvaluation{" +
                "lesson date =" + record.getLessonDate() +
                 "subject =" + record.getSubject().getName() +
                  "theme =" + record.getLessonTheme() +
                ", student=" + student.getLastName() +" " + student.getFirstName()+
                ", assessment=" + grade +
                 ", assessmentDate=" + assessmentDate +
                '}';
    }
}
