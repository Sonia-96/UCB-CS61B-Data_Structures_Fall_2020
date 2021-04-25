import java.util.*;

public class BreadthFirstPaths {
    private boolean[] marked; // mark[v] is true if v connected to s
    private int[] edgeTo; // previous vertex on path from s to v
    private int s;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        Arrays.fill(marked, false);
        edgeTo = new int[G.V()];
        this.s = s;
        bfs(G, this.s);
    }

    public void bfs(Graph G, int s) {
        Queue<Integer> fringe = new LinkedList<>();
        fringe.add(s);
        marked[s] = true;
        while (!fringe.isEmpty()) {
            int v = fringe.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    fringe.add(w);
                }
            }
        }
    }

    // is there a path from s to v?
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // shortest path from s to v
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(s);
        Collections.reverse(path);
        return path;
    }
}