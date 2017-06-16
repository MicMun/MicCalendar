package de.micmun.java.mickalender.model;

import de.micmun.java.mickalender.util.CalendarDate;

import java.util.List;

/**
 * Representation of one week.
 *
 * @author MicMun
 * @version 1.0, 16.06.17
 */
public class Week {
   private CalendarDate MONDAY;
   private CalendarDate TUESDAY;
   private CalendarDate WEDNESDAY;
   private CalendarDate THURSDAY;
   private CalendarDate FRIDAY;
   private CalendarDate SATURDAY;
   private CalendarDate SUNDAY;

   /**
    * New Week with List of seven days of this week.
    *
    * @param days List of seven days of this week.
    */
   public Week(List<CalendarDate> days) {
      MONDAY = days.get(0);
      TUESDAY = days.get(1);
      WEDNESDAY = days.get(2);
      THURSDAY = days.get(3);
      FRIDAY = days.get(4);
      SATURDAY = days.get(5);
      SUNDAY = days.get(6);
   }

   /**
    * New week with empty dates.
    */
   public Week() {
      MONDAY = null;
      TUESDAY = null;
      WEDNESDAY = null;
      THURSDAY = null;
      FRIDAY = null;
      SATURDAY = null;
      SUNDAY = null;
   }

   public CalendarDate getMONDAY() {
      return MONDAY;
   }

   public void setMONDAY(CalendarDate MONDAY) {
      this.MONDAY = MONDAY;
   }

   public CalendarDate getTUESDAY() {
      return TUESDAY;
   }

   public void setTUESDAY(CalendarDate TUESDAY) {
      this.TUESDAY = TUESDAY;
   }

   public CalendarDate getWEDNESDAY() {
      return WEDNESDAY;
   }

   public void setWEDNESDAY(CalendarDate WEDNESDAY) {
      this.WEDNESDAY = WEDNESDAY;
   }

   public CalendarDate getTHURSDAY() {
      return THURSDAY;
   }

   public void setTHURSDAY(CalendarDate THURSDAY) {
      this.THURSDAY = THURSDAY;
   }

   public CalendarDate getFRIDAY() {
      return FRIDAY;
   }

   public void setFRIDAY(CalendarDate FRIDAY) {
      this.FRIDAY = FRIDAY;
   }

   public CalendarDate getSATURDAY() {
      return SATURDAY;
   }

   public void setSATURDAY(CalendarDate SATURDAY) {
      this.SATURDAY = SATURDAY;
   }

   public CalendarDate getSUNDAY() {
      return SUNDAY;
   }

   public void setSUNDAY(CalendarDate SUNDAY) {
      this.SUNDAY = SUNDAY;
   }
}
