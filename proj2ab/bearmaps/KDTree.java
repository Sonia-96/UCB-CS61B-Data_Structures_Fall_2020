package bearmaps;

import java.util.List;

public class KDTree {
    private Node root;

    private class Node {
        int depth;
        Point point;
        Node left;
        Node right;

        private Node(int d, Point p) {
            depth = d;
            point = p;
        }

        private double distance(Point x) {
            return Point.distance(point, x);
        }

        private double getX() {
            return point.getX();
        }

        private double getY() {
            return point.getY();
        }
    }


    public KDTree(List<Point> points) {
        for (Point p : points) {
            put(p);
        }
    }

    private void put(Point p) {
        root = put(root, p, 0);
    }

    private Node put(Node n, Point p, int currDepth) {
        if (n == null) {
            return new Node(currDepth, p);
        }
        if (p.equals(n.point)) {
            return n;
        }
        double cmp = compare(n.point, p, currDepth);
        if (cmp < 0) {
            n.left = put(n.left, p, currDepth + 1);
        } else {
            n.right = put(n.right, p, currDepth + 1);
        }
        return n;
    }

    private double compare(Point a, Point b, int depth) {
        if (depth % 2 == 0) {
            return a.getX() - b.getX();
        }
        return a.getY() - b.getY();
    }

    public Point nearest(double x, double y) {
        return nearest(root, new Point(x, y), root).point;
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (n.distance(goal) < best.distance(goal)) {
            best = n;
        }
        Node goodSide, badSide;
        double cmp = compare(n.point, goal, n.depth);
        if (cmp < 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        // check if the bad side is worth looking
        double badDist;
        if (n.depth % 2 == 0) {
            badDist = Math.pow(goal.getX() - n.getX(), 2);
        } else {
            badDist = Math.pow(goal.getY() - n.getY(), 2);
        }
        best = nearest(goodSide, goal, best);
        if (badDist < best.distance(goal)) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }
}
