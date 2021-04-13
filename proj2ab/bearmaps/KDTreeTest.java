package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class KDTreeTest {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    @Test
    public void testNearestSimple() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        List<Point> points = List.of(p1, p2, p3);

        KDTree kdtree = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        assertEquals(nn.nearest(3.0, 4.0).getX(), kdtree.nearest(3.0, 4.0).getX(), 0.00000001);
        assertEquals(nn.nearest(3.0, 4.0).getY(), kdtree.nearest(3.0, 4.0).getY(), 0.00000001);
    }

    @Test
    public void testNearestSimple2() {
        Point p1 = new Point(2, 3); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);
        Point p7 = new Point(4, 2);
        List<Point> points = List.of(p1, p2, p3, p4, p5, p6, p7);

        KDTree kdtree = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        assertEquals(nn.nearest(3.0, 4.0).getX(), kdtree.nearest(3.0, 4.0).getX(), 0.00000001);
        assertEquals(nn.nearest(3.0, 4.0).getY(), kdtree.nearest(3.0, 4.0).getY(), 0.00000001);
    }

    @Test
    public void testNearestRandomly() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            Point p = new Point(StdRandom.uniform(-1000, 1000), StdRandom.uniform(-1000, 1000));
            points.add(p);
        }
        KDTree kdtree = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        for (int i = 0; i < 10000; i++) {
            Point goal = new Point(StdRandom.uniform(-1000, 1000), StdRandom.uniform(-1000, 1000));
            Point kdRes = kdtree.nearest(goal.getX(), goal.getY());
            Point nnRes = nn.nearest(goal.getX(), goal.getY());
            assertEquals(Point.distance(nnRes, goal), Point.distance(kdRes, goal), 0.00000001);
        }
    }

    @Test
    public void testTimings() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            Point p = new Point(StdRandom.uniform(-1000, 1000), StdRandom.uniform(-1000, 1000));
            points.add(p);
        }
        KDTree kdtree = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);

        Stopwatch nnSw = new Stopwatch();
        for (int i = 0; i < 500; i++) {
            Point goal = new Point(StdRandom.uniform(-1000, 1000), StdRandom.uniform(-1000, 1000));
            nn.nearest(goal.getX(), goal.getY());
        }
        double nnTime = nnSw.elapsedTime();
        System.out.println("The time of NaivePointSet: " + nnTime + "seconds");

        Stopwatch kdSw = new Stopwatch();
        for (int i = 0; i < 500; i++) {
            Point goal = new Point(StdRandom.uniform(-1000, 1000), StdRandom.uniform(-1000, 1000));
            kdtree.nearest(goal.getX(), goal.getY());
        }
        double kdTime = kdSw.elapsedTime();
        System.out.println("The time of kdTree: " + kdTime + "seconds");
    }
}
