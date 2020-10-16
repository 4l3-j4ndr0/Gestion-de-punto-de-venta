/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author 4l3
 */
public class MyMinimalPieChartExample { 

// private static final String KEY1 = "Datum 1"; 
// public static final String KEY2 = "Datum 2"; 

 public static void main(String[] args) { 
  DefaultPieDataset dataset = new DefaultPieDataset(); 
//  dataset.setValue(KEY1, 99); 
//  dataset.setValue(KEY2, 77); 
for (int i = 0; i < OpcionesVen.get_all_productos_vendidos().size(); i++) {
                    dataset.setValue(OpcionesVen.all_productos().get(i).toString(), OpcionesVen.get_ventas(OpcionesVen.all_productos().get(i).toString()));
                }

     JFreeChart someChart = ChartFactory.createPieChart(
   "Header", dataset, true, true, false); 
  PiePlot plot = (PiePlot) someChart.getPlot(); 
//  plot.setSectionPaint(KEY1, Color.green); 
//  plot.setSectionPaint(KEY2, Color.red); 
//  plot.setExplodePercent(KEY1, 0.10); 
  plot.setSimpleLabels(true); 

  PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
   "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%")); 
  plot.setLabelGenerator(gen); 

  JFrame f = new JFrame("Test"); 
  f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  f.add(new ChartPanel(someChart) { 
   @Override 
   public Dimension getPreferredSize() { 
    return new Dimension(800, 600); 
   } 
  }); 
  f.pack(); 
  f.setLocationRelativeTo(null); 
  f.setVisible(true); 

 } 
} 
