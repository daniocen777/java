package web.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

@ManagedBean
@ViewScoped
public class ChartViewBar implements Serializable {

    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;

    @PostConstruct
    public void init() {
        createBarModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 120);
        boys.set("2005", 100);
        boys.set("2006", 44);
        boys.set("2007", 150);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 110);
        girls.set("2007", 135);
        girls.set("2008", 120);
        
        ChartSeries gay = new ChartSeries();
        gay.setLabel("Gay");
        gay.set("2004", 12);
        gay.set("2005", 30);
        gay.set("2006", 56);
        gay.set("2007", 90);
        gay.set("2008", 160);
        
        ChartSeries bisexual = new ChartSeries();
        bisexual.setLabel("Bisexual");
        bisexual.set("2004", 4);
        bisexual.set("2005", 15);
        bisexual.set("2006", 22);
        bisexual.set("2007", 56);
        bisexual.set("2008", 99);
        
        ChartSeries extraterrestre = new ChartSeries();
        extraterrestre.setLabel("Extraterrestre");
        extraterrestre.set("2004", 98);
        extraterrestre.set("2005", 34);
        extraterrestre.set("2006", 45);
        extraterrestre.set("2007", 100);
        extraterrestre.set("2008", 120);

        model.addSeries(boys);
        model.addSeries(girls);
        model.addSeries(gay);
        model.addSeries(bisexual);
        model.addSeries(extraterrestre);

        return model;
    }

    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Género");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Nacimientos");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();

        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("2004", 50);
        boys.set("2005", 96);
        boys.set("2006", 44);
        boys.set("2007", 55);
        boys.set("2008", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Girls");
        girls.set("2004", 52);
        girls.set("2005", 60);
        girls.set("2006", 82);
        girls.set("2007", 35);
        girls.set("2008", 120);

        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);

        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);

        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Births");
        xAxis.setMin(0);
        xAxis.setMax(200);

        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Gender");
    }

}
