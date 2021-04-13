package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet{
    List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = new ArrayList<>();
        for (Point p : points) {
            this.points.add(p);
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        double minDist = Double.POSITIVE_INFINITY;
        Point nearest = null;
        for (Point p : points) {
            double dist = Point.distance(target, p);
            if(dist < minDist) {
                minDist = dist;
                nearest = p;
            };
        }
        return nearest;
    }
}
