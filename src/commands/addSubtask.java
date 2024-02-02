package commands;

import UI.Components.TaskManagerGUI;

import entities.Task;
import javax.swing.*;
import java.awt.*;


public class addSubtask {
    public void execute(TaskManagerGUI taskManagerGUI, Task t){

        JFrame frame = new JFrame("Edit sub tasks:");
        JPanel subTaskOptions = new JPanel();
        //Area for adding a member
        JLabel addLabel = new JLabel("Add a sub task:");
        subTaskOptions.add(addLabel);
        JTextField addsubT = new JTextField(20);
        addsubT.addActionListener(e -> {
            t.setSubTasks(t.getSubTasksSize()+1, addsubT.getText());
            taskManagerGUI.updateTaskList();
        });
        subTaskOptions.add(addsubT);

        frame.add(subTaskOptions, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        /**
         * might need this!
        Task subTask = new Task();
        subTask.setTitle(taskManagerGUI.getSubTaskArea().getText());
        taskManagerGUI.addSubTaskToContainer(subTask);
         */
    }
}
