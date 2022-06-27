package dao;

import entity.Locale;

import java.util.List;

public interface LocaleDAO {

    Locale getLocaleByShortName(String shortName);
    List<Locale> getAllLocales();

}
