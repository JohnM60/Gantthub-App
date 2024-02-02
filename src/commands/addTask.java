package commands;
import UI.Components.*;
import entities.Task;

import java.time.DateTimeException;
import java.time.LocalDate;

public class addTask extends Command {

    public addTask() {
        super();
    }

    @Override
    public void execute(TaskManagerGUI taskManager, Task ignoreThisTask) {
        Task t = new Task();
        t.setTitle(taskManager.getTitleField().getText());
        t.setDescription(taskManager.getDescriptionArea().getText());
        t.setAssignees(taskManager.getMemberTextArea().getText());
        t.setSubTasks(t.getSubTasksSize(), taskManager.getSubTaskTextArea().getText());
        t.incrementtaskCount();
        try {
            t.setPriority(Integer.parseInt(taskManager.getPriorityField().getText()));
        } catch (NumberFormatException e) {
            t.setPriority(0);
        }
        t.setStartDate(LocalDate.now());
        t.setEndDate(LocalDate.now());
        t.setActualStartDate(LocalDate.now());
        t.setActualEndDate(LocalDate.now());
        taskManager.addTaskToContainer(t);
        taskManager.updateTextDisplay();
    }
}
