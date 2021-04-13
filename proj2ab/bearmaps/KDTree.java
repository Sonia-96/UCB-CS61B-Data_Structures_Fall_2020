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
        root = put(root, 0, p);
    }

    private Node put(Node n, int currDepth, Point p) {
        if (n == null) {
            return new Node(currDepth, p);
        }
        // 偶数层比较x坐标
        if (n.depth % 2 == 0) {
            if (p.getX() < n.getX()) {
                n.left = put(n.left, n.depth + 1, p);
            } else if (p.getX() > n.getX() || p.getY() != n.getY()) {
                n.right = put(n.right, n.depth + 1, p);
            }
        } else { // 奇数层比较y坐标
            if (p.getY() < n.getY()) {
                n.left = put(n.left, n.depth + 1, p);
            } else if (p.getY() > n.getY() || p.getX() != n.getX()) {
                n.right = put(n.right, n.depth + 1, p);
            }
        }
        return n;
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
        // good side first
        Node goodSide, badSide;
        double badDist;
        if (n.depth % 2 == 0) { // 偶数层比较x坐标
            if (goal.getX() < n.getX()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
            badDist = Math.pow(goal.getX() - n.getX(), 2);
        } else {
            if (goal.getY() < n.getY()) {
                goodSide = n.left;
                badSide = n.right;
            } else {
                goodSide = n.right;
                badSide = n.left;
            }
            badDist = Math.pow(goal.getY() - n.getY(), 2);
        }
        best = nearest(goodSide, goal, best);
        if (badDist < best.distance(goal)) {
            best = nearest(badSide, goal, best);
        }
        return best;
    }
}
