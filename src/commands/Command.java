package commands;

import UI.Components.TaskManagerGUI;
import entities.Task;

public abstract class Command {
    public Command() {
    }

    public abstract void execute(TaskManagerGUI taskManagerGUI, Task t);

    //https://refactoring.guru/design-patterns/command/java/example
    //consider also just extending java.lang.Runnable or javax.swing.Action
}