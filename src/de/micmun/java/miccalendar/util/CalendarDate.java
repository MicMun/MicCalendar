package de.micmun.java.miccalendar.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Representation of one date with holyday if it is one.
 *
 * @author MicMun
 * @version 1.1, 16.06.17
 */
public class CalendarDate {
   private LocalDate date;
   private Holyday holyday;

   private final DateTimeFormatter DAY_FORMATTER = DateTimeFormatter
           .ofPattern("dd");

   /**
    * A new CalendarDate with LocalDate and Holyday.
    *
    * @param date    LocalDate of the day.
    * @param holyday Holyday or <code>null</code>, if it is not a holyday.
    */
   CalendarDate(LocalDate date, Holyday holyday) {
      this.date = date;
      this.holyday = holyday;
   }

   /**
    * A new CalendarDate of today.
    */
   public CalendarDate() {
      date = LocalDate.now();
      holyday = null;
   }

   /**
    * Returns the month of the date.
    *
    * @return month of the date.
    */
   public Month getMonth() {
      return date.getMonth();
   }

   /**
    * Returns the year of the date.
    *
    * @return year of the date.
    */
   public int getYear() {
      return date.getYear();
   }

   public Holyday getHolyday() {
      return holyday;
   }

   @Override
   public String toString() {
      return DAY_FORMATTER.format(date);
   }
}
