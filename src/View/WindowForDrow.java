package View;

import model.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtchart.*;
import org.eclipse.swt.graphics.*;

import java.awt.*;
import java.util.ArrayList;

public class WindowForDrow {
    private Chart chart;

    WindowForDrow(Shell shell) {
        chart = new Chart(shell, SWT.NONE);
        chart.setBounds(0, 0, 900, 700);
    }

    public Chart getChart() {
        return chart;
    }

    public void redraw() {
        this.getChart().redraw();
    }

    public void drowWindow(ArrayList<Point> point, String string) {
        ISeriesSet seriesSet = chart.getSeriesSet();
        ISeries series = seriesSet.createSeries(ISeries.SeriesType.LINE, string);

        ILineSeries lineSeries = (ILineSeries) chart.getSeriesSet().getSeries(string);
        Display display = this.chart.getDisplay();
        if (string == "first") {
            lineSeries.setLineColor(new Color(display, 255, 0, 0));
        }
        if(string == "second"){
            lineSeries.setLineColor(new Color(display, 0, 255, 0));
        }


        ArrayList<Double> ax = new ArrayList<>();
        ArrayList<Double> ay = new ArrayList<>();
        for (Point p : point) {
            ax.add(p.getX());
            ay.add(p.getY());
        }

        double[] y = new double[ay.size()];
        double[] x = new double[ax.size()];
        for (int i = 0; i < ay.size(); ++i) {
            y[i] = ay.get(i);
            x[i] = ax.get(i);
        }
        series.setYSeries(y);
        series.setXSeries(x);
        IAxisSet axisSet = chart.getAxisSet();
        axisSet.adjustRange();
        chart.redraw();
    }


    public void clear() {
        ISeriesSet seriesSet = this.getChart().getSeriesSet();
        ISeries[] a = seriesSet.getSeries();
        for (ISeries series : a) {
            this.getChart().getSeriesSet().deleteSeries(series.getId());
        }
        this.redraw();
    }

    public void zoomWindow() {
        IAxisSet axisSet = this.getChart().getAxisSet();
        axisSet.zoomIn();
    }

    public void rZoomWindow() {
        IAxisSet axisSet = this.getChart().getAxisSet();
        axisSet.zoomOut();
    }
}
