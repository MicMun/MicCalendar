package de.micmun.java.miccalendar.util;

import java.time.LocalDate;

/**
 * Representation of a holyday with name and date.
 *
 * @author MicMun
 * @version 1.1, 30.05.17
 */
public class Holyday {
   private String name;
   private LocalDate date;

   /**
    * Holyday with name and date.
    *
    * @param name name of the holyday.
    * @param date date of the holyday as GregorianCalendar.
    */
   Holyday(String name, LocalDate date) {
      this.name = name;
      this.date = date;
   }

   /**
    * Returns the date of the holyday.
    *
    * @return the date of the holyday.
    */
   LocalDate getDate() {
      return date;
   }

   @Override
   public String toString() {
      //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.uuuu");
      //return date.format(dtf) + ": " + name;
      return name;
   }
}
