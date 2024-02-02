package commands;

import UI.Components.TaskManagerGUI;

public class removeTask {

    public void execute(TaskManagerGUI taskManagerGUI, int index) {
        if (taskManagerGUI.tasksSize()>0) {
            taskManagerGUI.removeTaskFromContainer(index);
            taskManagerGUI.updateTaskList();
        }
    }
}
