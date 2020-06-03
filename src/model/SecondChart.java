package model;

import View.TableWidget;
import View.WindowForDrow;
import model.Point;

import java.util.ArrayList;

public class SecondChart {
    private WindowForDrow windowForDrow;
    private TableWidget tableWidget;
    private double lastPoint = 0.0;

    public double getLastPoint() {
        return lastPoint;
    }

    private double firstPoint = 0.0;
    private double step = 0.1;


    public ArrayList<Point> getPoints() {
        return points;
    }


    ArrayList<Point> points = new ArrayList<>();

    public SecondChart(WindowForDrow windowForDrow) {
        this.windowForDrow = windowForDrow;

    }

    public void update(TableWidget tableWidget){
        tableWidget.addWidget(points);
    }

    public double getFirstPoint() {
        return firstPoint;
    }

    public void setFirstPoint(double firstPoint) {
        this.lastPoint = firstPoint;
        this.firstPoint = firstPoint;
    }

    public void setPoint() {
        Point point = new Point(lastPoint);
        point.setPoints(points);
        point.forHard(firstPoint ,lastPoint);
       // point.simple();
        points.add(point);
        lastPoint += step;
    }


    public void upWindowForDrow() {
        windowForDrow.drowWindow(points , "second");
    }

    public WindowForDrow getWindowForDrow() {
        return windowForDrow;
    }

}
