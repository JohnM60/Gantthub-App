package commands;

import UI.Components.TaskManagerGUI;
import entities.Task;
import javax.swing.*;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;

public class editDate extends Command{
    public editDate() {
        super();
    }
    @Override
    public void execute(TaskManagerGUI taskManagerGUI, Task t) {
        JFrame frame = new JFrame("Edit Date");
        JTextField alertField = new JTextField();
        alertField.setText("");
        JPanel dateOptions = new JPanel();
        //Area for adding estimated start and end dates
        JLabel estStartDate = new JLabel("Enter dates in YYYY-MM-DD format, and press enter to confirm. " +
                " Estimated Start Date:");
        dateOptions.add(estStartDate);
        JTextField estStartDateField = new JTextField(LocalDate.now().toString(), 10);
        estStartDateField.addActionListener(e -> {
            try {
                t.setStartDate(LocalDate.parse(estStartDateField.getText()));
                checkDates(t, alertField);
            } catch (DateTimeException d) {
                alertField.setText("Invalid date format");
            }
            taskManagerGUI.updateTextDisplay();
        });
        dateOptions.add(estStartDateField);

        JLabel estEndDate = new JLabel("Estimated End Date:");
        dateOptions.add(estEndDate);
        JTextField estEndDateField = new JTextField(LocalDate.now().toString(), 10);
        estEndDateField.addActionListener(e -> {
            try {
                t.setEndDate(LocalDate.parse(estEndDateField.getText()));
                checkDates(t, alertField);
            } catch (DateTimeException d) {
                alertField.setText("Invalid date format");
            }
            taskManagerGUI.updateTextDisplay();
        });
        dateOptions.add(estEndDateField);
        //Area for adding actual start and end dates
        JLabel actStartDate = new JLabel("Actual start date:");
        dateOptions.add(actStartDate);
        JTextField actStartDateField = new JTextField(LocalDate.now().toString(), 10);
        actStartDateField.addActionListener(e -> {
            try {
                t.setActualStartDate(LocalDate.parse(actStartDateField.getText()));
                checkDates(t, alertField);
            } catch (DateTimeException d) {
                alertField.setText("Invalid date format");
            }
            taskManagerGUI.updateTextDisplay();
        });
        dateOptions.add(actStartDateField);

        JLabel actEndDate = new JLabel("Actual End Date:");
        dateOptions.add(actEndDate);
        JTextField actEndDateField = new JTextField(LocalDate.now().toString(), 10);
        actEndDateField.addActionListener(e -> {
            try {
                t.setActualEndDate(LocalDate.parse(actEndDateField.getText()));
                checkDates(t, alertField);
            } catch (DateTimeException d) {
                alertField.setText("Invalid date format");
            }
            taskManagerGUI.updateTextDisplay();
        });
        dateOptions.add(actEndDateField);
        frame.add(dateOptions, BorderLayout.SOUTH);
        frame.add(alertField);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Helper method for validating if end date is before start date
     * @param t the task with dates to be compared
     * @param alertField the text field to output text to
     */
    private void checkDates(Task t, JTextField alertField) {
        if (t.getActualEndDate().isBefore(t.getActualStartDate())) {
            alertField.setText("Error: actual end date is before actual start date");
            t.setActualEndDate(t.getActualStartDate());
        }
        else if (t.getEndDate().isBefore(t.getStartDate())) {
            alertField.setText("Error: estimated end date is before estimated start date");
            t.setEndDate(t.getStartDate());
        }
        else {
            alertField.setText("");
        }
    }
}
