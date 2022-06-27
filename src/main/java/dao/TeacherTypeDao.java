package dao;

import entity.TeacherType;

import java.util.List;

public interface TeacherTypeDao {

    void addType(TeacherType type);
    TeacherType getTypeById(int id);
    List<TeacherType> getAllTypes();
    void updateTypeById(TeacherType type, int id);
    void deleteTypeById(int id);
    TeacherType getTypeByTypeName(String typeName);
}
