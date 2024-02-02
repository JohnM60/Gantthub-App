package container;

import entities.Task;
import java.util.*;

/**
 * A collection of all container. Implement singleton pattern
 */
public class TaskContainer {

    private static TaskContainer instance;

    public static TaskContainer getInstance()
    {
        if ( null == instance )
        {
            instance = new TaskContainer();
        }

        return instance;
    }

    Map<Integer, Task> tasks;
    // saves the IDs of the removed tasks
    ArrayList<Integer> removedIDs = new ArrayList<>();


    private TaskContainer() {
        tasks = new Hashtable<>();
    }

    public Task addTask(Task t){
        tasks.put(t.getTaskID(), t);
        if (removedIDs.size() != 0){
            t.setTaskID(removedIDs.get(0));
            removedIDs.remove(0);
        }
        return t;
    }

    public int size() { return tasks.size(); }

    public Task removeTask(int subID)
    {
        Task t = tasks.get(subID);
        if ( t != null)
        {
            tasks.remove(subID);
        }
        if (subID < tasks.size()) removedIDs.add(subID);
        return t;
    }

    public Task getTask(int index){
        return tasks.get(index);
    }

    public Task removeTask(Task t)
    {
        return removeTask(t.getTaskID());
    }

    /**
     * @return an ArrayList containing all tasks
     */
    public ArrayList<Task> getTasks() {
        //return list representation
        ArrayList<Task> taskList = new ArrayList<>();

        for ( Map.Entry<Integer, Task> entry : tasks.entrySet() )
        {
            taskList.add(entry.getValue());
        }

        return taskList;
    }

    /**
     *
     * @return set of tasks ids saved to this project
     */
    public Set<Integer> getTaskIds()
    {
        return tasks.keySet();
    }
}
