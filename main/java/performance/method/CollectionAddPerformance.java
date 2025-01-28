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

public class CollectionAddPerformance {

    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        int numberOfElements = 100000;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            data.add(i);
        }

        List<Integer> arrayList = new ArrayList<>();
        dataset.addValue(measureAddPerformance(arrayList, data), "Add", "ArrayList(add)");

        List<Integer> linkedList = new LinkedList<>();
        dataset.addValue(measureAddPerformance(linkedList, data), "Add", "LinkedList(add)");

        Map<Integer, Integer> hashMap = new HashMap<>();
        dataset.addValue(measureAddPerformance(hashMap, data), "Add", "HashMap(put)");

        Set<Integer> hashSet = new HashSet<>();
        dataset.addValue(measureAddPerformance(hashSet, data), "Add", "HashSet(add)");

        Map<Integer, Integer> treeMap = new TreeMap<>();
        dataset.addValue(measureAddPerformance(treeMap, data), "Add", "TreeMap(put)");

        Set<Integer> treeSet = new TreeSet<>();
        dataset.addValue(measureAddPerformance(treeSet, data), "Add", "TreeSet(add)");

        Queue<Integer> priorityQueue = new PriorityQueue<>();
        dataset.addValue(measureAddPerformance(priorityQueue, data), "Add", "PriorityQueue(add)");

        JFreeChart chart = ChartFactory.createBarChart(
                "Collection Add/Put Operation Performance",
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

        renderer.setSeriesPaint(0, java.awt.Color.GREEN);
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

    private static long measureAddPerformance(Collection<Integer> collection, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            collection.add(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureAddPerformance(Map<Integer, Integer> map, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            map.put(i, i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }

    private static long measureAddPerformance(Queue<Integer> queue, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            queue.add(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000;
    }
}
