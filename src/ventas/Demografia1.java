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
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import javafx.scene.CacheHint;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class Demografia1 {

    public static void main(String[] args) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int tiempo = 0; tiempo < OpcionesVen.all_productos().size(); tiempo++) {
            dataset.addValue(OpcionesVen.get_ventas(OpcionesVen.all_productos().get(tiempo).toString()), "", OpcionesVen.all_productos().get(tiempo).toString());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Calculo estadistico",
                "productos",
                "ventas",
                dataset,
                PlotOrientation.HORIZONTAL,
                false,
                true,
                false
        );
        chart.setBorderVisible(true);
        chart.setBackgroundPaint(new java.awt.Color(204,204,255));
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        
        plot.setOutlinePaint(Color.BLUE);
plot.setOutlineStroke(new BasicStroke(2.0f));
plot.setBackgroundPaint(Color.WHITE);
plot.setRangeGridlinesVisible(true);
plot.setRangeGridlinePaint(Color.BLACK);
 
plot.setDomainGridlinesVisible(true);
plot.setDomainGridlinePaint(Color.BLACK);

        LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) plot.getRenderer();
        lineandshaperenderer.setBaseShapesVisible(true);
// line seris colors in line chart
        lineandshaperenderer.setSeriesPaint(0, Color.BLUE);
//        lineandshaperenderer.setSeriesPaint(1, Color.BLUE);
//        lineandshaperenderer.setSeriesPaint(2, Color.MAGENTA);
//        lineandshaperenderer.setSeriesPaint(3, Color.YELLOW);
//        lineandshaperenderer.setSeriesPaint(4, Color.PINK);

        lineandshaperenderer.setItemLabelsVisible(false);
        lineandshaperenderer.setBaseStroke(new BasicStroke(3.5f));

// series font style
        lineandshaperenderer.setSeriesItemLabelFont(0, new Font("TimesRoman", Font.BOLD, 11));
//        lineandshaperenderer.setSeriesItemLabelFont(1, new Font("TimesRoman", Font.BOLD, 11));
//        lineandshaperenderer.setSeriesItemLabelFont(2, new Font("TimesRoman", Font.BOLD, 12));
//        lineandshaperenderer.setSeriesItemLabelFont(3, new Font("TimesRoman", Font.BOLD, 11));
//        lineandshaperenderer.setSeriesItemLabelFont(4, new Font("TimesRoman", Font.BOLD, 11));
// values colors in line chart
        lineandshaperenderer.setSeriesItemLabelPaint(0, Color.BLACK);
//        lineandshaperenderer.setSeriesItemLabelPaint(1, Color.WHITE);
//        lineandshaperenderer.setSeriesItemLabelPaint(2, Color.BLACK);
//        lineandshaperenderer.setSeriesItemLabelPaint(3, Color.YELLOW);
//        lineandshaperenderer.setSeriesItemLabelPaint(4, Color.RED);

        DecimalFormat decimalformat1 = new DecimalFormat("##");
        lineandshaperenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
        lineandshaperenderer.setSeriesPositiveItemLabelPosition(1, new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));

//        ByteArrayOutputStream chart_out = new ByteArrayOutputStream();
//        ChartUtilities.writeChartAsJPEG(chart_out, CacheHint.QUALITY, chart, width, height);
//        int pictureureIdx = workbook.addPicture(chart_out.toByteArray(), Workbook.PICTURE_TYPE_PNG);
//        chart_out.close();
//        Drawing drawing = sheet.createDrawingPatriarch();
//        CreationHelper helper = workbook.getCreationHelper();
//        ClientAnchor anchor = helper.createClientAnchor();
//        anchor.setCol1(15);
//        anchor.setRow1(27);
//        Picture my_picture = drawing.createPicture(anchor, pictureureIdx);
//        my_picture.resize();
        //Mostramos la grafica en pantalla
        ChartFrame fr = new ChartFrame("Calculo Demografico I", chart);
        fr.pack();
        fr.setVisible(true);

    }

}
