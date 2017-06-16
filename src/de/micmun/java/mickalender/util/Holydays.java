package de.micmun.java.mickalender.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Handling the holydays.
 *
 * @author MicMun
 * @version 1.1, 30.05.17
 */
class Holydays {
   private int year;

   private List<Holyday> holydays;

   /**
    * Holydays for the given year +/- 1.
    *
    * @param year given year.
    */
   Holydays(int year) {
      this.year = year;
      holydays = new ArrayList<>(60);
      calculate();
   }

   /**
    * Returns the holyday if the date is a holday, otherwise <code>null</code>.
    *
    * @param date date to check.
    * @return holyday if the date is a holday otherwise <code>null</code>.
    */
   Holyday getHolyday(LocalDate date) {
      Holyday holyday = null;

      for (Holyday h : holydays) {
         if (h.getDate().equals(date)) {
            holyday = h;
            break;
         }
      }
      return holyday;
   }

   /**
    * Calculates the holydays for three years (year-1, year, year+1).
    */
   private void calculate() {
      int startYear = year - 1;
      int endYear = year + 1;

      calcHolydays(startYear);
      calcHolydays(year);
      calcHolydays(endYear);
   }

   /**
    * Calculates the holydays for the given year.
    *
    * @param year year to calculate the holydays.
    */
   private void calcHolydays(int year) {
      LocalDate easter = easterSunday(year);
      LocalDate bubetday = getBuBetDay(easter);

      holydays.add(new Holyday("Neujahr",
              getHolyday(year, Month.JANUARY, 1)));
      holydays.add(new Holyday("Hl. Drei Könige",
              getHolyday(year, Month.JANUARY, 6)));
      holydays.add(new Holyday("Karfreitag",
              getHolyday(easter, -2)));
      holydays.add(new Holyday("Ostersonntag", easter));
      holydays.add(new Holyday("Ostermontag", getHolyday(easter, 1)));
      holydays.add(new Holyday("Tag der Arbeit",
              getHolyday(year, Month.MAY, 1)));
      holydays.add(new Holyday("Christi Himmelfahrt",
              getHolyday(easter, 39)));
      holydays.add(new Holyday("Pfingstsonntag",
              getHolyday(easter, 49)));
      holydays.add(new Holyday("Pfingstmontag",
              getHolyday(easter, 50)));
      holydays.add(new Holyday("Fronleichnam",
              getHolyday(easter, 60)));
      holydays.add(new Holyday("Mariä Himmelfahrt",
              getHolyday(year, Month.AUGUST, 15)));
      holydays.add(new Holyday("Tag der dt. Einheit",
              getHolyday(year, Month.OCTOBER, 3)));
      holydays.add(new Holyday("Reformationstag",
              getHolyday(year, Month.OCTOBER, 31)));
      holydays.add(new Holyday("Allerheiligen",
              getHolyday(year, Month.NOVEMBER, 1)));
      holydays.add(new Holyday("Buß- und Bettag", bubetday));
      holydays.add(new Holyday("1. Weihnachtsfeiertag",
              getHolyday(year, Month.DECEMBER, 25)));
      holydays.add(new Holyday("2. Weihnachtsfeiertag",
              getHolyday(year, Month.DECEMBER, 26)));
   }

   /**
    * Returns the LocalDate for year, month and day.
    *
    * @param year  year.
    * @param month month.
    * @param day   day.
    * @return LocalDate.
    */
   private LocalDate getHolyday(int year, Month month, int day) {
      return LocalDate.of(year, month, day);
   }

   /**
    * Returns the LocalDate for an offset to easter sunday.
    *
    * @param easter easter sunday.
    * @param offset day offset.
    * @return LocalDate.
    */
   private LocalDate getHolyday(LocalDate easter, int offset) {
      return easter.plusDays(offset);
   }

   /**
    * Calculate the easter sunday of the given year.
    *
    * @param year year for calculation.
    * @return easter sunday.
    */
   private LocalDate easterSunday(int year) {
      // Calculate the date of easter sunday.
      int K = year / 100;
      int M = 15 + ((3 * K + 3) / 4) - ((8 * K + 13) / 25);
      int S = 2 - ((3 * K + 3) / 4);
      int A = year % 19;
      int D = (19 * A + M) % 30;
      int R = D / 29 + (D / 28 - D / 29) * A / 11;
      int OG = 21 + D - R;
      int SZ = 7 - ((year + year / 4 + S) % 7);
      int OE = 7 - ((OG - SZ) % 7);
      int OS = OG + OE - 1; // add OS to 01.03. for easter sunday

      LocalDate easter = LocalDate.of(year, Month.MARCH, 1);

      return easter.plusDays(OS);
   }

   /**
    * Returns the date of Bu&szlig;- und Bettag.
    *
    * @param easterSunday LocalDate for easter sunday.
    * @return LocalDate with date of Bu&szlig;- und Bettag.
    */
   private LocalDate getBuBetDay(LocalDate easterSunday) {
      int easterYear = easterSunday.getYear();
      int dayOfOS = easterSunday.getDayOfMonth();
      int number = easterSunday.getMonth() == Month.MARCH ? 33 : 30;

      int dayNr = (-1) * ((number - dayOfOS) % 7);

      // local date
      LocalDate date = LocalDate.of(easterYear, Month.NOVEMBER, 22);

      return date.plusDays(dayNr);
   }

}
