package model;

import View.TableWidget;
import View.WindowForDrow;

import java.util.ArrayList;

public class FirstChart {
    private WindowForDrow windowForDrow;
    private TableWidget tableWidget;
    private double lastPoint = 0.0;
    private double step = 10;

    ArrayList<Point> points = new ArrayList<>();

    public ArrayList<Point> getPoints() {
        return points;
    }




    public FirstChart(WindowForDrow windowForDrow) {
        this.windowForDrow = windowForDrow;

    }

    public void update(TableWidget tableWidget){
        tableWidget.addWidget(points);
    }

    public double getFirstPoint() {
        return lastPoint;
    }

    public void setFirstPoint(double firstPoint) {
        this.lastPoint = firstPoint;
    }

    public void setPoint() {
        Point point = new Point(lastPoint);
        point.forEasy();
        points.add(point);
        lastPoint += step;
    }


    public void upWindowForDrow() {
        windowForDrow.drowWindow(points, "first");
    }

    public WindowForDrow getWindowForDrow() {
        return windowForDrow;
    }

}
