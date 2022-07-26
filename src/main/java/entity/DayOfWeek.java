package entity;

import java.time.DateTimeException;

public enum DayOfWeek {

     MONDAY("Понедельник"), TUESDAY("Вторник"), WEDNESDAY("Среда"),
    THURSDAY("Четверг"), FRIDAY("Пятница"), SATURDAY("Суббота"),
    SUNDAY("Воскресенье");

   private String russianEquiv;

   DayOfWeek(String russianEquiv) {
        this.russianEquiv = russianEquiv;
    }

    public String getRussianEquiv() {
        return russianEquiv;
    }

    public void setRussianEquiv(String russianEquiv) {
        this.russianEquiv = russianEquiv;
    }

    public int getValue(){
       return this.ordinal()+1;
    }

    public static DayOfWeek of (int dayOfWeek) {
        if (dayOfWeek >= 1 && dayOfWeek <= 7) {
            return values()[dayOfWeek - 1];
        } else {
            throw new DateTimeException("Invalid value for DayOfWeek: " + dayOfWeek);
        }
    }

    public static String[] russianValues(){
      String[] russianValues = new String[7];
      for(int i = 0; i<russianValues.length; i++){
          russianValues[i]=DayOfWeek.of(i+1).getRussianEquiv();
      }
      return russianValues;
    }

    public static DayOfWeek getDayOfWeek(String russianEquiv){
           switch (russianEquiv){
               case "Понедельник":return DayOfWeek.MONDAY;
               case "Вторник":return DayOfWeek.TUESDAY;
               case "Среда":return DayOfWeek.WEDNESDAY;
               case "Четверг":return DayOfWeek.THURSDAY;
               case "Пятница":return DayOfWeek.FRIDAY;
               case "Суббота":return DayOfWeek.SATURDAY;
               case "Воскресенье":return DayOfWeek.SUNDAY;
           }
     throw  new DateTimeException("Invalid value for DayOfWeek: " + russianEquiv);
    }


}
