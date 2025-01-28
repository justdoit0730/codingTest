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

public class LinkedListPerformance {

    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int numberOfElements = 100000;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            data.add(i);
        }

        List<Integer> linkedList = new LinkedList<>();

        dataset.addValue(measureAddPerformance(linkedList, data), "Add(add)", "LinkedList");
        dataset.addValue(measureReadPerformance(linkedList, data), "Read(get)", "LinkedList");
        dataset.addValue(measureSearchPerformance(linkedList, data), "Search(contains)", "LinkedList");
        dataset.addValue(measureRemovePerformance(linkedList, data), "Remove(remove)", "LinkedList");

        JFreeChart chart = ChartFactory.createBarChart(
                "LinkedList Operations Performance",
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
        renderer.setSeriesPaint(1, java.awt.Color.BLUE);
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

    private static long measureAddPerformance(List<Integer> list, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            list.add(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureReadPerformance(List<Integer> list, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            list.get(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureSearchPerformance(List<Integer> list, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            list.contains(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureRemovePerformance(List<Integer> list, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            list.remove((Integer) i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

}
