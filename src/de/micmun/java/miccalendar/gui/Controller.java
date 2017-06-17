package de.micmun.java.miccalendar.gui;

import de.micmun.java.miccalendar.model.Week;
import de.micmun.java.miccalendar.util.CalendarDate;
import de.micmun.java.miccalendar.util.MonthCalendar;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

/**
 * Controller for handling user input.
 *
 * @author MicMun
 * @version 1.1, 16.06.17
 */
public class Controller {
   @FXML
   private ComboBox<Month> cbMonat;

   @FXML
   private ComboBox<Integer> cbJahr;

   @FXML
   private HBox hbTop;
   @FXML
   private Label lMonat;
   @FXML
   private Label lJahr;

   @FXML
   private TableView<Week> tableView;

   private final TextStyle MONTH_STYLE = TextStyle.FULL;
   private final Locale LOCALE = Locale.getDefault();
   private final Month[] MONTHS = Month.values();

   private CalendarTabView calendarTabView;

   private int currentYear;
   private Month currentMonth;

   @FXML
   public void initialize() {
      // Initialize the values
      cbMonat.getItems().addAll(MONTHS);
      cbMonat.setConverter(new StringConverter<Month>() {
         @Override
         public String toString(Month object) {
            return object.getDisplayName(MONTH_STYLE, LOCALE);
         }

         @Override
         public Month fromString(String string) {
            throw new UnsupportedOperationException("Not supported yet");
         }
      });

      CalendarDate cd = new CalendarDate();

      List<Integer> years = getYears(cd.getYear());
      cbJahr.getItems().addAll(years);

      // month and year
      hbTop.getStyleClass().add("tophbox");
      currentMonth = cd.getMonth();
      currentYear = cd.getYear();
      lMonat.setText(currentMonth.getDisplayName(MONTH_STYLE, LOCALE));
      lMonat.getStyleClass().add("toplabel");
      lJahr.setText(String.valueOf(currentYear));
      lJahr.getStyleClass().add("toplabel");

      // weekdays
      calendarTabView = new CalendarTabView(tableView);

      // Listener
      cbMonat.getSelectionModel().selectedItemProperty().addListener(
              (observable, oldValue, newValue) -> {
                 if (oldValue == null || !oldValue.equals(newValue)) {
                    lMonat.setText(newValue.getDisplayName(MONTH_STYLE, LOCALE));
                    currentMonth = newValue;
                    initCalendar(currentYear, currentMonth);
                 }
              });
      cbJahr.getSelectionModel().selectedItemProperty().addListener(
              (observable, oldValue, newValue) -> {
                 if (oldValue == null || !Objects.equals(oldValue, newValue)) {
                    lJahr.setText(String.valueOf(newValue));
                    currentYear = newValue;
                    initCalendar(currentYear, currentMonth);
                 }
              });

      // select default
      cbMonat.getSelectionModel().select(currentMonth);
      cbJahr.getSelectionModel().select(Integer.valueOf(currentYear));
   }

   /**
    * Initialize the calendar in the gridPane.
    *
    * @param year  selected year.
    * @param month selected month.
    */
   private void initCalendar(int year, Month month) {
      // Calculate Month and Year
      MonthCalendar monthCalendar = new MonthCalendar(year, month);
      //Show days of month
      calendarTabView.showTableView(monthCalendar.getCalendarList());
   }

   /**
    * Returns a list of years (3 years back and 5 years to future).
    *
    * @param year current year.
    * @return list of years.
    */
   private List<Integer> getYears(int year) {
      int startYear = year - 3;
      int endYear = year + 5;

      ArrayList<Integer> years = new ArrayList<>(endYear - startYear + 1);

      for (int i = startYear; i <= endYear; ++i) {
         years.add(i);
      }

      return years;
   }
}
