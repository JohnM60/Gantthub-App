package commands;

import UI.Components.TaskManagerGUI;
import container.TaskContainer;
import entities.Task;
import javax.swing.*;
import java.awt.*;

public class editDependencies {
    public void execute(TaskManagerGUI taskManagerGUI, Task t) {
        JFrame frame = new JFrame("Edit Dependencies");
        JPanel dependencyOptions = new JPanel();
        //Area for adding a dependency
        JLabel addLabel = new JLabel("Add a Task ID that this task depends on:");
        dependencyOptions.add(addLabel);
        JTextField addDependency = new JTextField(20);
        addDependency.addActionListener(e -> {
            try {
                if (TaskContainer.getInstance().getTask(Integer.parseInt(addDependency.getText())) != null) {
                    t.setDependencies(Integer.parseInt(addDependency.getText()));
                }
                taskManagerGUI.updateTaskList();
            } catch (Exception n) {
            }
        });
        dependencyOptions.add(addDependency);
        //Area for removing a dependency
        JLabel removeLabel = new JLabel("Enter position of task to remove (0 for first):");
        dependencyOptions.add(removeLabel);
        JTextField removeMembers = new JTextField(20);
        removeMembers.addActionListener(e -> {
            try {
                t.removeDependency(Integer.parseInt(removeMembers.getText()));
                taskManagerGUI.updateTaskList();
            } catch (Exception n) {
            }
        });
        dependencyOptions.add(removeMembers);
        frame.add(dependencyOptions, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}
