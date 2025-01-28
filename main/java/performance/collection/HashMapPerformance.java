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

public class HashMapPerformance {

    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        int numberOfElements = 100000;
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < numberOfElements; i++) {
            data.add(i);
        }
        
        Map<Integer, Integer> hashMap = new HashMap<>();
        
        dataset.addValue(measureAddPerformance(hashMap, data), "Add(put)", "HashMap");
        dataset.addValue(measureReadPerformance(hashMap, data), "Read(get)", "HashMap");
        dataset.addValue(measureSearchPerformance(hashMap, data), "Search(containsKey)", "HashMap");
        dataset.addValue(measureRemovePerformance(hashMap, data), "Remove(remove)", "HashMap");
        
        JFreeChart chart = ChartFactory.createBarChart(
                "HashMap Operations Performance", 
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
    
    private static long measureAddPerformance(Map<Integer, Integer> map, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            map.put(i, i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; 
    }

    private static long measureReadPerformance(Map<Integer, Integer> map, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            map.get(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; 
    }

    private static long measureRemovePerformance(Map<Integer, Integer> map, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            map.remove(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; 
    }

    private static long measureSearchPerformance(Map<Integer, Integer> map, List<Integer> data) {
        long startTime = System.nanoTime();
        for (int i : data) {
            map.containsKey(i);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1_000_000; 
    }
}
