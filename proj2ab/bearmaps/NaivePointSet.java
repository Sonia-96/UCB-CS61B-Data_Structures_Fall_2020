package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
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
