package dao;

import entity.*;

import java.time.DayOfWeek;
import java.util.List;

public interface ScheduleRecordDao {
    void addNewRecord(ScheduleRecord record);
    List<ScheduleRecord> getAllRecords() ;
    List<ScheduleRecord> getScheduleOfaTeacher(Teacher teacher);
    List<ScheduleRecord> getScheduleOfaStudent(Level level);
    List<Level> getLevelsOfTeacher(Teacher teacher);
    void deleteRecord(Long id);
}
