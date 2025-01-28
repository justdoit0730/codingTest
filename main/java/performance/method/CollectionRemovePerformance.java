package performance.method;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.*;

public class CollectionRemovePerformance {

    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int numberOfElements = 100000;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            data.add(i);
        }

        List<Integer> arrayList = new ArrayList<>(data);
        dataset.addValue(measureRemovePerformance(arrayList), "Remove", "ArrayList(remove)");

        List<Integer> linkedList = new LinkedList<>(data);
        dataset.addValue(measureRemovePerformance(linkedList), "Remove", "LinkedList(remove)");

        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i : data) {
            hashMap.put(i, i);
        }
        dataset.addValue(measureRemovePerformance(hashMap), "Remove", "HashMap(remove)");

        Set<Integer> hashSet = new HashSet<>(data);
        dataset.addValue(measureRemovePerformance(hashSet), "Remove", "HashSet(remove)");

        Map<Integer, Integer> treeMap = new TreeMap<>();
        for (int i : data) {
            treeMap.put(i, i);
        }
        dataset.addValue(measureRemovePerformance(treeMap), "Remove", "TreeMap(remove)");

        Set<Integer> treeSet = new TreeSet<>(data);
        dataset.addValue(measureRemovePerformance(treeSet), "Remove", "TreeSet(remove)");

        Queue<Integer> priorityQueue = new PriorityQueue<>(data);
        dataset.addValue(measureRemovePerformance(priorityQueue), "Remove", "PriorityQueue(remove)");

        JFreeChart chart = ChartFactory.createBarChart(
                "Collection Remove Operation Performance",
                "Collection",
                "Time (ms)",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        renderer.setSeriesPaint(0, java.awt.Color.RED);
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

    private static long measureRemovePerformance(Collection<Integer> collection) {
        long startTime = System.nanoTime();
        for (int i = 0; i < collection.size(); i++) {
            collection.remove(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureRemovePerformance(Map<Integer, Integer> map) {
        long startTime = System.nanoTime();
        for (int i = 0; i < map.size(); i++) {
            map.remove(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureRemovePerformance(Queue<Integer> queue) {
        long startTime = System.nanoTime();
        for (int i = 0; i < queue.size(); i++) {
            queue.remove();
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}
