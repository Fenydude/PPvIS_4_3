package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class Point {
    private double x;
    private double y;
    private double sum = 0.0;

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    private ArrayList<Point> points = new ArrayList<>();

    Point(double x) {
        BigDecimal bd = new BigDecimal(Double.toString(x));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        this.x = bd.doubleValue();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void forEasy() {
        this.y = (double) ((this.x + 5));
    }

    public void forHard(double firstPoint, double lastPoint) {
        for (Point point : points){
            sum += point.getY();
        }
        sum += Math.sin(Math.sin(points.size()+1 * (Math.pow(x,2) + lastPoint - firstPoint )))/factorial(points.size()+1);
        BigDecimal bd = new BigDecimal(Double.toString(sum));
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        this.y =bd.doubleValue();
    }

    public static double factorial(double n)
    {
        if (n == 0) return 1;
        return n * factorial(n-1);
    }

    public void simple(){
        this.y = (double) ((-this.x -1));
    }


}
