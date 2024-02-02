package entities;


import container.TaskContainer;

import java.time.LocalDate;
import java.util.*;

public class Task implements Comparable<Task> {


    //region private members
    private static int taskCount = 0; //this is going to be tricky when loading a file ugh
    private int taskID;
    private String description;
    private Map<Integer, Task> subTasks;
    private int priority;
    private List<Object> assignees;
    private List<Object> resources;

    private boolean completed;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private ArrayList<Integer> dependencies;
    //endregion

    //region Constructor
    public Task(String title, String description, int priority) {

        this.taskID = taskCount;

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.completed = false;
        subTasks = new Hashtable<>();
        assignees = new ArrayList<>();
        resources = new ArrayList<>();
        dependencies = new ArrayList<>();
    }

    public Task()
    {
        this("","", 1);
    }

    //endregion

    //region Getters

    public String getDescription() {
        return description;
    }

    public List<String> getSubTaskList() {
        Collection<Task> subValues= subTasks.values();
        List<String> subTs = new ArrayList<>();
        for (Task subT : subValues){
            subTs.add(subT.getTitle());
        }
        return subTs;
        }
    public Map<Integer, Task> getSubTaskMap() {
        return subTasks;
    }

    public int getPriority() {
        return priority;
    }

    public List<Object> getAssignees() {
        return assignees;
    }

    public int getTaskID() {
        return taskID;
    }

    public boolean status() { return completed; }

    public String getTitle() {
        return title;
    }

    public int getSubTasksSize(){return subTasks.size();}

    public LocalDate getStartDate() { return startDate; }

    public LocalDate getEndDate() { return endDate; }

    public LocalDate getActualStartDate() { return actualStartDate; }

    public LocalDate getActualEndDate() { return actualEndDate; }
    public ArrayList<Integer> getDependencies() {
        return dependencies;
    }
    //endregion


    //region Setters

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void addSubTask(Task t)
    {
        subTasks.put(t.taskID, t);
    }

    public void setTaskID(int id) {
        this.taskID = id;
    }

    public void setAssignees(String member) {
        assignees.add(member);
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setSubTasks(int index, String subTask) {
        this.subTasks.put(index, new Task (subTask, "", 1));
    }

    public void setStartDate(LocalDate d) {
        startDate = d;
    }

    public void setEndDate(LocalDate d) {
        endDate = d;
    }

    public void setActualStartDate(LocalDate d) {
        actualStartDate = d;
    }

    public void setActualEndDate(LocalDate d) { actualEndDate = d; }

    public void setDependencies(int id) {
        dependencies.add(id);
    }
    //endregion

    //methods to remove a subtask
    /**
     * Remove subtask of subID from this task
     * @param subID
     * @return the subtask removed if subtask is found, null otherwise
     */
    public Task removeSubTask(int subID)
    {
        Task t = subTasks.get(subID);
        if ( t != null) subTasks.remove(subID);
        return t;
    }

    public Task removeSubTask(Task t)
    {
        return removeSubTask(t.taskID);
    }

    public void removeMember(int memberToRemove) {
        getAssignees().remove(memberToRemove);
    }

    public void removeDependency(int dependencyToRemove) {
        dependencies.remove(dependencyToRemove);
    }
    //endregion

    // methods to edit variables

    public void incrementtaskCount(){
        taskCount++;
    }

    //endregion


    //region Testing
    public static void main(String[] args) {
        Task a = new Task();
        //test adding a description
        a.setDescription("Make Bread");
        if (!a.description.equals("Make Bread"))
            System.out.println("Error in setDescription: task was not assigned a description");

        //test adding an id
        a.setTaskID(2);
        if (a.taskID != 2)
            System.out.println("Error in setTaskID: task was not assigned an ID");

        //test adding a priority
        a.setPriority(3);
        if (a.priority != 3)
            System.out.println("Error in setPriority: task was not assigned a description");

        //test adding a member
        a.setAssignees("Anonymous member");
        if (!a.assignees.get(0).equals("Anonymous member"))
            System.out.println("Error in setAssignees: member Isn't in the task");

        a.setAssignees("Test");
        a.setAssignees("third member");
        if (!a.assignees.get(1).equals("Test")) System.out.println("Error in setAssignees: member Isn't in the task");
        if (!a.assignees.get(2).equals("third member")) System.out.println("Error in setAssignees: member Isn't in the task");



        //test adding a description
        a.setDescription("Make Bread");
        if (!a.getDescription().equals("Make Bread"))
            System.out.println("Error in setDescription: task was not assigned a description");

        //test adding a subtask
        Task b = new Task();
        b.setDescription("Gather ingredients");
        a.addSubTask(b);
        if (a.subTasks.isEmpty())
            System.out.println("Error in addSubTask: subtask was not added");

        //test removing a subtask with task name
        a.removeSubTask(b);
        if (!a.subTasks.isEmpty())
            System.out.println("Error in removeSubTask: task was not removed");

        System.out.println("***** Testing Complete *****");
    }

    //endregion

    @Override
    public int compareTo(Task o) {
        return this.getStartDate().compareTo(o.getStartDate());
    }
}