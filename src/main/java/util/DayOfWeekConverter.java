package util;


import java.time.DayOfWeek;


public class DayOfWeekConverter {

    private static final String DAY1 = "Понедельник";
    private static final String DAY2 ="Вторник";
    private static final String DAY3 = "Среда";
    private static final String DAY4 = "Четверг";
    private static final String DAY5 = "Пятница";
    private static final String DAY6 = "Суббота";
    private static final String DAY7 = "Воскресенье";
    private static final int daysQuantity = 7;

    public static String [] getRussianDaysOfWeek(){
        String [] days = new String[daysQuantity];
        days[0] = DAY1;
        days[1] = DAY2;
        days[2] = DAY3;
        days[3] = DAY4;
        days[4] = DAY5;
        days[5] = DAY6;
        days[6] = DAY7;
        return days;
    }
    public static DayOfWeek convertDayOfWeek(String russianDayOfWeek){
        DayOfWeek dayOfWeek = null;
        switch (russianDayOfWeek){
            case DAY1: dayOfWeek = DayOfWeek.MONDAY;
            break;
            case DAY2:dayOfWeek = DayOfWeek.TUESDAY;
            break;
            case DAY3:dayOfWeek = DayOfWeek.WEDNESDAY;
            break;
            case DAY4:dayOfWeek =  DayOfWeek.THURSDAY;
            break;
            case DAY5:dayOfWeek = DayOfWeek.FRIDAY;
            break;
            case DAY6:dayOfWeek = DayOfWeek.SATURDAY;
            break;
            case DAY7: dayOfWeek= DayOfWeek.SUNDAY;
            break;
        }
        return dayOfWeek;
    }
    public static String convertDayOfWeek(DayOfWeek dayOfWeek){
        String day = null;
        switch (dayOfWeek){
            case MONDAY: day =  DAY1;
            break;
            case TUESDAY:day = DAY2;
            break;
            case WEDNESDAY: day = DAY3;
            break;
            case THURSDAY:day = DAY4;
            break;
            case FRIDAY: day =  DAY5;
            break;
            case SATURDAY:day = DAY6;
            break;
            case SUNDAY: day =  DAY7;
            break;

        }
        return day;
    }
}
