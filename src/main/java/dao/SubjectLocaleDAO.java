package dao;

import entity.Locale;
import entity.Subject;

import java.util.List;

public interface SubjectLocaleDAO {

    Subject getSubjectByLocaleName(String subjectName);
    List<Subject> getSubjectLocaleNameList(Locale locale);
    Subject getSubjectLocaleBySubjectAndLocale(Subject subject, Locale locale);

}
