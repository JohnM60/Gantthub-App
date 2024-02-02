package commands;

import UI.Components.TaskManagerGUI;
import entities.Task;
import javax.swing.*;
import java.awt.*;

public class editMember {
    public void execute(TaskManagerGUI taskManagerGUI, Task t) {
        JFrame frame = new JFrame("Edit Members");
        JPanel memberOptions = new JPanel();
        //Area for adding a member
        JLabel addLabel = new JLabel("Add a Member(Press enter to confirm):");
        memberOptions.add(addLabel);
        JTextField addMembers = new JTextField(20);
        addMembers.addActionListener(e -> {
            t.setAssignees(addMembers.getText());
            taskManagerGUI.updateTaskList();
        });
        memberOptions.add(addMembers);
        //Area for removing a member
        JLabel removeLabel = new JLabel("Enter position of member to remove (0 for first member):");
        memberOptions.add(removeLabel);
        JTextField removeMembers = new JTextField(20);
        removeMembers.addActionListener(e -> {
            try {
                t.removeMember(Integer.parseInt(removeMembers.getText()));
                taskManagerGUI.updateTaskList();
            } catch (Exception n) {
            }
        });
        memberOptions.add(removeMembers);
        frame.add(memberOptions, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }
}

