package dao;

import entity.Locale;
import entity.TeacherType;

import java.util.List;

public interface TeacherTypeLocaleDAO {

    List<TeacherType> getTeacherTypeLocaleList(Locale locale);
    TeacherType getTeacherTypeByLocaleTypeName(String localeTypeName);


}
