package de.micmun.java.mickalender.util;

import de.micmun.java.mickalender.model.Week;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * MonthCalendar for handling the day logic.
 *
 * @author MicMun
 * @version 1.2, 16.06.17
 */
public class MonthCalendar {
   private List<LocalDate> calendarList;

   private Month month;
   private int year;

   private Holydays holydays;

   /**
    * MonthCalendar for given year, month = current month -1 and
    * monthEnd = current month + 1;
    *
    * @param year current year.
    */
   public MonthCalendar(int year, Month month) {
      this.year = year;
      this.month = month;

      calendarList = new ArrayList<>(31);
      holydays = new Holydays(year);
      calculate();
   }

   /**
    * Returns a observable list for weeks.
    *
    * @return a observable list for weeks.
    */
   public ObservableList<Week> getCalendarList() {
      ObservableList<Week> weeks = FXCollections.observableArrayList();

      int index = 0;
      List<CalendarDate> calendarDates = new ArrayList<>(7);
      LocalDate lastDate = calendarList.get(0);

      for (LocalDate d : calendarList) {
         lastDate = d;
         DayOfWeek weekday = d.getDayOfWeek();

         if (index == 0 && calendarDates.size() == 0) {
            for (int i = 1; i < weekday.getValue(); ++i) {
               calendarDates.add(null);
            }
         }
         Holyday holyday = holydays.getHolyday(d);
         calendarDates.add(new CalendarDate(d, holyday));

         if (weekday == DayOfWeek.SUNDAY) {
            Week week = new Week(calendarDates);
            weeks.add(week);
            index++;
            calendarDates = new ArrayList<>(7);
         }
      }

      DayOfWeek weekday = lastDate.getDayOfWeek();
      if (weekday != DayOfWeek.SUNDAY) {
         for (int i = weekday.getValue(); i < DayOfWeek.SUNDAY.getValue(); ++i) {
            calendarDates.add(null);
         }
         Week week = new Week(calendarDates);
         weeks.add(week);
      }

      return weeks;
   }

   /**
    * Calculate the calendar.
    */
   private void calculate() {
      LocalDate start = LocalDate.of(year, month, 1);
      LocalDate end = LocalDate.of(year, month, start.lengthOfMonth());

      LocalDate current = start;
      LocalDate save;

      while (current.isBefore(end)) {
         calendarList.add(current);
         save = current.plusDays(1);
         current = save;
      }
      calendarList.add(current);
   }
}
