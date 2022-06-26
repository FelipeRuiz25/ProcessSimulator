package so.simulator.views.panels;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import so.simulator.views.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PanelGraphic extends JPanel{

    private JLabel titleForGraphic;
    private JFreeChart pieChart;
    private ChartPanel chartPanel;

    //falta ponerle los datos desde el modelo
    public PanelGraphic(String title){
        this.titleForGraphic = new JLabel(title);
        this.pieChart = ChartFactory.createBarChart(title, Constants.VALUE_X_GRAPHIC, Constants.VALUE_Y_GRAPHIC, createDataset(), PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot = pieChart.getCategoryPlot();
        //poner valor inferior y superior
        plot.addRangeMarker(new IntervalMarker(1, 8), Layer.BACKGROUND);
        //plot.addRangeMarker(new IntervalMarker(3.4, 3.6), Layer.FOREGROUND);
        this.chartPanel = new ChartPanel(pieChart);
        fill();

        //valor inicial
        Marker start = new ValueMarker(1);
        start.setPaint(Color.green);
        start.setLabel( "Tiempo Minimo" );
        start.setLabelFont(Constants.FONT_LIST);
        start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(start);

        //valor promedio
        Marker average = new ValueMarker(4.5);
        average.setPaint(Color.CYAN);
        average.setLabel( "Tiempo Promedio" );
        average.setLabelFont(Constants.FONT_LIST);
        average.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        average.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(average);

        //valor final
        Marker end = new ValueMarker(8);
        end.setPaint(Color.red);
        end.setLabel( "Tiempo Maximo" );
        end.setLabelFont(Constants.FONT_LIST);
        end.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        end.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(end);

        pieChart.getPlot().setBackgroundPaint( new Color(177, 175, 175) );
        CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.red);
        renderer.setSeriesPaint(1, Color.cyan);
        renderer.setSeriesPaint(2, Color.BLUE);

    }

    private void fill(){
        this.chartPanel.setPreferredSize(new java.awt.Dimension(700, 500));
        this.titleForGraphic.setFont(Constants.FONT_LIST);
        add(chartPanel);
    }

    //a este metodo llegan los datos que se van a poner en la grafica estos son solo para visualizar que sirviera bien
    private CategoryDataset createDataset() {
// row keys...
        final String firefox = "Firefox";
        final String chrome = "Chrome";
        final String iexplorer = "InternetExplorer";

        // column keys...
        final String speed = "Speed";
        final String popular = "Popular";
        final String response = "Response";
        final String osindependent = "OS Independent";
        final String features = "Features";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, firefox, speed);
        dataset.addValue(4.0, firefox, popular);
        dataset.addValue(3.0, firefox, response);
        dataset.addValue(5.0, firefox, osindependent);
        dataset.addValue(5.0, firefox, features);

        dataset.addValue(5.0, chrome, speed);
        dataset.addValue(7.0, chrome, popular);
        dataset.addValue(6.0, chrome, response);
        dataset.addValue(8.0, chrome, osindependent);
        dataset.addValue(4.0, chrome, features);

        dataset.addValue(4.0, iexplorer, speed);
        dataset.addValue(3.0, iexplorer, popular);
        dataset.addValue(2.0, iexplorer, response);
        dataset.addValue(3.0, iexplorer, osindependent);
        dataset.addValue(6.0, iexplorer, features);

        return dataset;
    }


}
