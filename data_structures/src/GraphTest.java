import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GraphTest {
    public Graph graph;

    public GraphTest() {
        graph = new Graph(9);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 8);
        graph.addEdge(6, 7);
    }

    @Test
    public void testDFS() {
        DepthFirstPaths P = new DepthFirstPaths(graph, 0);
        assertTrue(P.hasPathTo(3));
        System.out.println(P.pathTo(3));
    }

    @Test
    public void testBFS() {
        BreadthFirstPaths P = new BreadthFirstPaths(graph, 0);
        System.out.println(P);
        assertTrue(P.hasPathTo(3));
        System.out.println(P.pathTo(3));
    }
}
