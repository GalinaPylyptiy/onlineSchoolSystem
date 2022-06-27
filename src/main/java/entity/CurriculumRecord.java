package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class CurriculumRecord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4251292109310696895L;
    private LocalDate lessonDate;
    private Subject subject;
    private Level level;
    private Teacher teacher;
    private String lessonTheme;
    private String homeTask;

    public CurriculumRecord(){}

    public CurriculumRecord(LocalDate localDate, Subject subject, Level level, Teacher teacher, String lessonTheme, String homeTask) {
        this.lessonDate = localDate;
        this.subject = subject;
        this.level = level;
        this.teacher = teacher;
        this.lessonTheme = lessonTheme;
        this.homeTask = homeTask;
    }

    public LocalDate getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(LocalDate localDate) {
        this.lessonDate = localDate;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getLessonTheme() {
        return lessonTheme;
    }

    public void setLessonTheme(String lessonTheme) {
        this.lessonTheme = lessonTheme;
    }

    public String getHomeTask() {
        return homeTask;
    }

    public void setHomeTask(String homeTask) {
        this.homeTask = homeTask;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurriculumRecord record = (CurriculumRecord) o;
        return  Objects.equals(id, record.id) &&
                Objects.equals(lessonDate, record.lessonDate) &&
                Objects.equals(subject, record.subject) &&
                Objects.equals(level, record.level) &&
                Objects.equals(teacher, record.teacher) &&
                Objects.equals(lessonTheme, record.lessonTheme) &&
                Objects.equals(homeTask, record.homeTask);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,lessonDate, subject, level, teacher, lessonTheme, homeTask);
    }

    @Override
    public String toString() {
        return
                id + ". "+
                " subjectId " + subject.getId()+
                " levelId" + level.getId()+
                " lessonDate= " + lessonDate +
                " teacherId " + teacher.getId()  +
                ",lessonTheme=" + lessonTheme + '\'' +
                ", homeTask='" + homeTask ;
    }
}
