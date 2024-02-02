package UI.Charts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import Reports.GanttChartDatasetFactory;
import container.TaskContainer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.swing.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeries;

/**
 * Class for testing Gantt Chart with mock-up data
 */

public class GanttChartDemo  extends JFrame {

    private static final long serialVersionUID = 1L;

    public GanttChartDemo(String title) {
        super(title);

        // Create dataset
        IntervalCategoryDataset dataset = createSampleProject();

        // Create chart
        JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt Chart Example", // Chart title
                "Software Development Phases", // X-Axis Label
                "Timeline", // Y-Axis Label
                dataset);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private IntervalCategoryDataset createSampleProject()
    {
        //Create tasks

        entities.Task task = new entities.Task();
        task.setTitle("Requirement");
        task.setStartDate(LocalDate.of(2017,7,3));
        task.setEndDate(LocalDate.of(2017,7,7));
        task.setActualStartDate(task.getStartDate());
        task.setActualEndDate(task.getEndDate());
        TaskContainer.getInstance().addTask(task);

        task = new entities.Task();
        task.setTitle("Design");
        task.setStartDate(LocalDate.of(2017,7,10));
        task.setEndDate(LocalDate.of(2017,7,14));
        task.setActualStartDate(task.getStartDate());
        task.setActualEndDate(task.getEndDate());
        TaskContainer.getInstance().addTask(task);

        task = new entities.Task();
        task.setTitle("Coding");
        task.setStartDate(LocalDate.of(2017,7,17));
        task.setEndDate(LocalDate.of(2017,7,21));
        task.setActualStartDate(task.getStartDate());
        task.setActualEndDate(task.getEndDate());
        TaskContainer.getInstance().addTask(task);

        task = new entities.Task();
        task.setTitle("Testing");
        task.setStartDate(LocalDate.of(2017,7,24));
        task.setEndDate(LocalDate.of(2017,7,28));
        task.setActualStartDate(task.getStartDate());
        task.setActualEndDate(task.getEndDate());

        entities.Task subtask = new entities.Task();
        subtask.setTitle("Testing-Subtask");
        subtask.setStartDate(LocalDate.of(2017,7,25));
        subtask.setEndDate(LocalDate.of(2017,7,28));
        subtask.setActualStartDate(LocalDate.of(2017,8,1));
        subtask.setActualEndDate(LocalDate.of(2017,8,5));
        task.addSubTask(subtask);

        entities.Task subsubtask = new entities.Task();
        subsubtask.setTitle("Testing-SubSubtask");
        subsubtask.setStartDate(LocalDate.of(2017,7,26));
        subsubtask.setEndDate(LocalDate.of(2017,7,28));
        subsubtask.setActualStartDate(LocalDate.of(2017,8,2));
        subsubtask.setActualEndDate(LocalDate.of(2017,8,6));
        subtask.addSubTask(subsubtask);

        entities.Task s = new entities.Task();
        s.setTitle("Testing-subsubsub");
        s.setStartDate(LocalDate.of(2017,7,26));
        s.setEndDate(LocalDate.of(2017,7,27));
        s.setActualStartDate(LocalDate.of(2017,8,2));
        s.setActualEndDate(LocalDate.of(2017,8,3));
        subsubtask.addSubTask(s);

        TaskContainer.getInstance().addTask(task);

        task = new entities.Task();
        task.setTitle("Deployment");
        task.setStartDate(LocalDate.of(2017,7,31));
        task.setEndDate(LocalDate.of(2017,8,4));
        task.setActualStartDate(task.getStartDate());
        task.setActualEndDate(task.getEndDate());
        TaskContainer.getInstance().addTask(task);


        //Create Chart
        ArrayList<entities.Task> sortedByDate = TaskContainer.getInstance().getTasks();
        Collections.sort(sortedByDate);

        TaskSeries estimateSeries = GanttChartDatasetFactory.CreateEstimateSeries("Expected", sortedByDate);
        TaskSeries actualSeries   = GanttChartDatasetFactory.CreateActualSeries("Actual", sortedByDate);

        IntervalCategoryDataset data = GanttChartDatasetFactory.CreateDataSet(
                new ArrayList<>(
                Arrays.asList(
                        estimateSeries,
                        actualSeries )
                ));

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GanttChartDemo example = new GanttChartDemo("Gantt Chart Example");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
