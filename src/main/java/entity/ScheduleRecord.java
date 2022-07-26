package entity;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

public class ScheduleRecord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 628395202569041685L;
    private DayOfWeek dayOfWeek;
    private LocalTime time ;
    private Level level;
    private Subject subject;
    private Teacher teacher;

    public ScheduleRecord(){}

    public ScheduleRecord(DayOfWeek dayOfWeek, LocalTime time, Level level, Subject subject, Teacher teacher) {
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.level = level;
        this.subject = subject;
        this.teacher = teacher;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleRecord scheduleRecord = (ScheduleRecord) o;
        return  dayOfWeek.equals(scheduleRecord.dayOfWeek) &&
                time.equals(scheduleRecord.time) &&
                level.getId().equals(scheduleRecord.level.getId()) &&
                subject.getId().equals(scheduleRecord.subject.getId()) &&
                teacher.getId().equals(scheduleRecord.teacher.getId())&&
                isActive().equals(scheduleRecord.isActive());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), dayOfWeek, time, level, subject, teacher);
    }

    @Override
    public String toString() {
        return  getId() + ", day of the lesson:" + dayOfWeek.name() +
                ", time of the lesson:" + time +
                ", level:" + level.getName() +
                ", subject:" + subject.getName() +
                ", teacher:" + teacher.getLastName() + " " +teacher.getFirstName();
    }
}
