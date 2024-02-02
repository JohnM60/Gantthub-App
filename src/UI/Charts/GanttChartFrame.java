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

public class GanttChartFrame  extends JFrame {

    private static final long serialVersionUID = 1L;

    public GanttChartFrame(String title) {
        super(title);

        // Create dataset
        IntervalCategoryDataset dataset = loadProjectData();

        // Create chart
        JFreeChart chart = ChartFactory.createGanttChart(
                "Gantt Chart", // Chart title
                "Project Phases", // X-Axis Label
                "Timeline", // Y-Axis Label
                dataset);

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private IntervalCategoryDataset loadProjectData()
    {
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
            GanttChartFrame example = new GanttChartFrame("Gantt Chart Example");
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
            example.setVisible(true);
        });
    }
}
