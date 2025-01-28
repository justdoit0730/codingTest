package performance.collection;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.*;

public class PriorityQueuePerformance {

    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int numberOfElements = 100000;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            data.add(i);
        }

        Queue<Integer> priorityQueue = new PriorityQueue<>();

        dataset.addValue(measureAddPerformance(priorityQueue, data), "Add(add)", "PriorityQueue");
        dataset.addValue(measureReadPerformance(priorityQueue), "Read(peek)", "PriorityQueue");
        dataset.addValue(measureSearchPerformance(priorityQueue, data), "Search(contains)", "PriorityQueue");
        dataset.addValue(measureRemovePerformance(priorityQueue), "Remove(poll)", "PriorityQueue");

        JFreeChart chart = ChartFactory.createBarChart(
                "PriorityQueue Operations Performance",
                "Operation",
                "Time (ms)",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, java.awt.Color.GREEN);
        renderer.setSeriesPaint(1, java.awt.Color.CYAN);
        renderer.setSeriesPaint(2, java.awt.Color.RED);
        renderer.setSeriesPaint(3, java.awt.Color.ORANGE);

        renderer.setItemMargin(0.1);
        renderer.setMaximumBarWidth(0.05);

        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 600));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static long measureAddPerformance(Queue<Integer> queue, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            queue.add(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureReadPerformance(Queue<Integer> queue) {
        long startTime = System.nanoTime();
        queue.peek();
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureSearchPerformance(Queue<Integer> queue, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            queue.contains(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureRemovePerformance(Queue<Integer> queue) {
        long startTime = System.nanoTime();
        while (!queue.isEmpty()) {
            queue.poll();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}
