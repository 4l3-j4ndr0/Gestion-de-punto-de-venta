/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventas;

/**
 *
 * @author 4l3
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Paint;
import javax.swing.JFrame;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

/**
 * * @see https://stackoverflow.com/a/29709153/230513
 */
public class BarChart {
    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        dataset.addValue(350, row, "A");
//        dataset.addValue(690, row, "B");
//        dataset.addValue(510, row, "C");
//        dataset.addValue(570, row, "D");
//        dataset.addValue(180, row, "E");
//        dataset.addValue(504, row, "F");
        for (int i = 0; i < OpcionesVen.all_productos().size(); i++) {
            dataset.addValue(OpcionesVen.get_ventas(OpcionesVen.all_productos().get(i).toString()), "", OpcionesVen.all_productos().get(i).toString());
        }
         return dataset;
    }
    
    private JFreeChart createChart(CategoryDataset dataset) {
        
        CategoryAxis categoryAxis = new CategoryAxis("");
        ValueAxis valueAxis = new NumberAxis("");
        valueAxis.setVisible(false);
        
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                switch (column) {
//                    case 0:
//                        return Color.red;
//                    case 1:
//                        return Color.yellow;
//                    case 2:
//                        return Color.blue;
//                    case 3:
//                        return Color.orange;
//                    case 4:
//                        return Color.gray;
//                    case 5:
//                        return Color.green.darker();
                    default:
                        return Color.BLUE;
                }
            }
        };
        renderer.setDrawBarOutline(false);
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.BOTTOM_CENTER));
//        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER));
        renderer.setBaseItemLabelsVisible(Boolean.TRUE);

//        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//        renderer.setDefaultItemLabelsVisible(true);
//        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
//                TextAnchor.TOP_CENTER);
//        renderer.setDefaultPositiveItemLabelPosition(position);

        renderer.setBarPainter(new StandardBarPainter());
        CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        chart.setBackgroundPaint(Color.white);
        return chart;
    }

    private void display() {
        JFrame f = new JFrame("BarChart");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new ChartPanel(createChart(createDataset())));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new BarChart().display();
        });
    }
}
