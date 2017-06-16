package de.micmun.java.mickalender.gui;

import de.micmun.java.mickalender.model.Week;
import de.micmun.java.mickalender.util.CalendarDate;
import de.micmun.java.mickalender.util.Holyday;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Present the data in the tableview.
 *
 * @author MicMun
 * @version 1.0, 16.06.17
 */
class CalendarTabView {
   private final TextStyle WEEKDAY_STYLE = TextStyle.FULL;
   private final Locale LOCALE = Locale.getDefault();

   private TableView<Week> tableView;

   /**
    * New CalendarTabView with a tableview.
    *
    * @param tableView {@link TableView} for weeks.
    */
   CalendarTabView(TableView<Week> tableView) {
      this.tableView = tableView;
      tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
      tableView.getSelectionModel().setCellSelectionEnabled(false);
   }

   /**
    * Shows the data of a month in weeks.
    *
    * @param weeks list of weeks.
    */
   void showTableView(ObservableList<Week> weeks) {
      initWeekdays();
      tableView.setItems(weeks);
   }

   /**
    * Initialize the weekday labels for the tableview.
    */
   private void initWeekdays() {
      DayOfWeek[] daysOfWeek = DayOfWeek.values();
      TableColumn<Week, CalendarDate>[] weekdays =
              new TableColumn[daysOfWeek.length];

      tableView.getColumns().clear();

      for (int i = 0; i < daysOfWeek.length; ++i) {
         DayOfWeek weekday = daysOfWeek[i];
         String colName = weekday.getDisplayName(WEEKDAY_STYLE, LOCALE);
         weekdays[i] = new TableColumn<>(colName);
         weekdays[i].setCellValueFactory(new PropertyValueFactory<>(
                 weekday.name()));
         if (weekday == DayOfWeek.SATURDAY || weekday == DayOfWeek.SUNDAY) {
            weekdays[i].getStyleClass().add("weekend");
         }

         weekdays[i].setCellFactory(
                 new Callback<TableColumn<Week, CalendarDate>,
                         TableCell<Week, CalendarDate>>() {
                    @Override
                    public TableCell<Week, CalendarDate> call(
                            TableColumn<Week, CalendarDate> param) {

                       TableCell<Week, CalendarDate> cell =
                               new TableCell<Week, CalendarDate>() {
                                  @Override
                                  public void updateItem(CalendarDate item,
                                                         boolean empty) {
                                     super.updateItem(item, empty);
                                     setText(empty || item == null ? "" : item
                                             .toString());
                                     setGraphic(null);

                                     if (item != null &&
                                             item.getHolyday() != null) {
                                        getStyleClass().add("holyday");

                                        Holyday h = item.getHolyday();
                                        Tooltip tooltip = new Tooltip(h
                                                .toString());
                                        setTooltip(tooltip);
                                     }
                                  }
                               };

                       return cell;
                    }
                 });
         tableView.getColumns().add(weekdays[i]);
      }
   }
}
