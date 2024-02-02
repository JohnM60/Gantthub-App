package UI.Components;

import UI.Charts.GanttChartFrame;
import commands.*;
import container.ResourceContainer;
import container.TaskContainer;
import entities.Resource;
import entities.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;



public class TaskManagerGUI {
    //Instance Variables
    private JTextArea taskTextArea;
    private JPanel taskOptionsArea;
    private JFrame frame;
    private JTextField titleField;
    private JTextField priorityField;
    private JTextArea descriptionArea;
    private JTextArea memberTextArea;
    private JTextArea subTaskTextArea;
    private JProgressBar progress;

    private JButton openResourceFrameBtn;
    private int option;
    private JButton openGanttChart;

    // hold the amount of finished tasks in the taskList
    private int trueTasks;
    /**
     * Constructor for the task manager
     * Creates areas for showing tasks and adding them
     */
    public TaskManagerGUI() {
        taskOptionsArea = new JPanel();

        frame = new JFrame("Task Manager");
        frame.setLayout(new BorderLayout());
        frame.setSize(420, 420);


        taskTextArea = new JTextArea(30, 40);
        taskTextArea.setEditable(false);

        // scroll through tasks:
        JScrollPane scrollPane = new JScrollPane(taskTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // ask for title of the task
        JPanel inputPanel = new JPanel();
        titleField = new JTextField(18);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);

        priorityField = new JTextField(5);
        inputPanel.add(new JLabel("Priority:"));
        inputPanel.add(priorityField);

        // add description of a task (optional)
        descriptionArea = new JTextArea(5, 20);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionArea);

        // assign members to the task
        memberTextArea = new JTextArea(5, 20);
        inputPanel.add(new JLabel("Members:"));
        inputPanel.add(memberTextArea);;

        // add subtasks to the task
        subTaskTextArea = new JTextArea(5, 20);
        inputPanel.add(new JLabel("SubTasks:"));
        inputPanel.add(subTaskTextArea);

        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> executeCommand(0));
        inputPanel.add(addButton);

        // progress of the user:
        progress = new JProgressBar(0, 100);

        progress.setBounds(0, 0, 420, 50);
        progress.setStringPainted(true);
        inputPanel.add(progress);

        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.add(taskOptionsArea, BorderLayout.NORTH);
        taskOptions();
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Adds a menu to the right of the tasks for selecting a task and modifying it
     */
    private void taskOptions() {
        //Area for selecting a task
        taskOptionsArea.add(new JLabel("Select Task ID number(Press enter to confirm):"));
        JTextField optionField = new JTextField(5);
        optionField.addActionListener(e -> {
            try {
                option = Integer.parseInt(optionField.getText());
                if (option > TaskContainer.getInstance().size()) {
                    option = TaskContainer.getInstance().size() - 1;
                }
            } catch (NumberFormatException n) {
                option = 0;
            }
        });
        taskOptionsArea.add(optionField);
        //buttons for completing a task, editing members, and removing a task
        JCheckBox checkBox = new JCheckBox("Complete This Task");
        checkBox.addActionListener(e -> executeCommand(2));
        taskOptionsArea.add(checkBox);

        JButton editMemberButton = new JButton("Members");
        editMemberButton.addActionListener(e -> executeCommand(3));
        taskOptionsArea.add(editMemberButton);

        JButton dependencyButton = new JButton("Dependencies");
        dependencyButton.addActionListener(e -> executeCommand(5));
        taskOptionsArea.add(dependencyButton);

        JButton editDateButton = new JButton("Date");
        editDateButton.addActionListener(e -> executeCommand(9));
        taskOptionsArea.add(editDateButton);

        JButton removeButton = new JButton("Remove Task");
        removeButton.addActionListener(e -> executeCommand(1));
        taskOptionsArea.add(removeButton);

        // add the subtask to the task in the taskList
        JButton subTaskButton = new JButton("Add Subtask");
        subTaskButton.addActionListener(e -> executeCommand(4));
        taskOptionsArea.add(subTaskButton);

  
        openGanttChart = new JButton("Create Project Gantt Chart");
        openGanttChart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    GanttChartFrame gant = new GanttChartFrame("Gantt Chart");
                    gant.setSize(800, 400);
                    gant.setLocationRelativeTo(null);
                    gant.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
                    gant.setVisible(true);
                });
            }
        });
        taskOptionsArea.add(openGanttChart);

        openResourceFrameBtn = new JButton("Add Resource to Project");
        openResourceFrameBtn.addActionListener( e ->
        {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    AddResourcePanel.createAndShowGUI().addWindowListener(
                            new java.awt.event.WindowAdapter()
                            {
                                @Override
                                public void windowClosing(java.awt.event.WindowEvent windowEvent)
                                {
                                    updateTextDisplay(); //update display on frame close
                                }
                            }
                    );
                }
            });
        });
        
        taskOptionsArea.add(openResourceFrameBtn);
    }

    /**
     * prints a complete list of all tasks. Make sure to use this every time a task is updated
     */
    public void updateTaskList() {
        taskTextArea.setText("");
        int progress1 = (int) getProgress();

        for (Task t:TaskContainer.getInstance().getTasks()) {
            taskTextArea.append("Is task completed: " + t.status() + "\nTask: " + t.getTitle()
                    + "\nID: " + t.getTaskID() + "\nDescription: " + t.getDescription() + "\nMembers: " +
                    t.getAssignees() + "\nPriority: " + t.getPriority() + "\nSubtasks: " + t.getSubTaskList()
                    + "\nEstimated start and end dates: " + t.getStartDate() + " to " + t.getEndDate()
                    + "\nActual start and end dates: " + t.getActualStartDate() + " to " + t.getActualEndDate()
                    + "\nDependencies: " + t.getDependencies() + "\n\n");
        }

        progress.setValue(progress1);

    }


    private void updateResourcesTextDisplay()
    {
        taskTextArea.append("Project Resources\n");
        ArrayList<Resource> resources = ResourceContainer.getInstance().getAllProjectResource();

        for ( Resource r : resources )
        {
            taskTextArea.append( "Resource Title: " + r.getTitle() + "\n"
                                +"ID: "             + r.getId() + "\n"
                                +"Cost: "           + r.getCost() + "\n"
                                +"Run Cost: "       + r.getHourlyRuntimeCost() + "\n"
                                                    + r.getDescription() + "\n"
            );
        }

        taskTextArea.append("\n");

    }

    public void updateTextDisplay()
    {
        taskTextArea.setText("");
        updateResourcesTextDisplay();
        updateTaskList();
        frame.pack();
    }


    public void checkTask(Task t) {
        if ( checkDependencies(t))
        {
            t.setCompleted(!t.status());
        }
        if (t.status()){
            trueTasks--;
            t.setActualEndDate(LocalDate.now());
        }
        else{
            trueTasks++;
        }

        updateTextDisplay();
    }

    public double getProgress(){
        return ((double) (trueTasks)/(double)TaskContainer.getInstance().size())*100;
    }

    /**
     * Helper method for checkTask
     */
    private boolean checkDependencies(Task t) {
        for (int i:t.getDependencies()) {
            if(!TaskContainer.getInstance().getTask(i).status()) {
                return false;
            }
        }
        return true;
    }
    /**
     * Executes a certain command specified by command number
     * @param commandNumber index of command to execute
     */
    private void executeCommand(int commandNumber) {
        Command c = new Command() {
            @Override
            public void execute(TaskManagerGUI taskManagerGUI, Task t) {
                // add a task
                if(commandNumber == 0) {
                    addTask a = new addTask();
                    a.execute(taskManagerGUI, null);
                }
                // remove a task
                else if (commandNumber == 1) {
                    removeTask r = new removeTask();
                    r.execute(taskManagerGUI, option);
                }
                else if (commandNumber == 2) {
                    checkTask(t);
                }
                // edit member
                else if (commandNumber == 3) {
                    editMember e = new editMember();
                    e.execute(taskManagerGUI, t);
                }
                // add a subtask
                else if (commandNumber == 4){
                    addSubtask addS = new addSubtask();
                    addS.execute(taskManagerGUI, t);
                }

                else if (commandNumber == 5) {
                    editDependencies e = new editDependencies();
                    e.execute(taskManagerGUI, t);
                }

                else if (commandNumber == 9) {
                    editDate e = new editDate();
                    e.execute(taskManagerGUI, t);
                }
            }
        };
        try {
            c.execute(this, TaskContainer.getInstance().getTask(option));
        } catch (IndexOutOfBoundsException e) {
            c.execute(this, null);
        }
    }
    //Getter methods for the task fields
    public JTextField getTitleField() {
        return titleField;
    }

    public JTextField getPriorityField() {
        return priorityField;
    }

    public JTextArea getDescriptionArea() {
        return descriptionArea;
    }

    public JTextArea getMemberTextArea() {
        return memberTextArea;
    }

    public JTextArea getSubTaskTextArea(){return subTaskTextArea;}

    public void addTaskToContainer(Task t){
        TaskContainer.getInstance().addTask(t);
    }

    public int tasksSize(){
        return TaskContainer.getInstance().size();
    }

    public void removeTaskFromContainer(int subID){
        TaskContainer.getInstance().removeTask(subID);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TaskManagerGUI());
    }
}
