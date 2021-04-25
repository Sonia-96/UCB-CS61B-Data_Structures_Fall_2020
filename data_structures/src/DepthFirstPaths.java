import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DepthFirstPaths {
    private boolean[] marked; // mark[v] is true if v connected to s
    private int[] edgeTo; // previous vertex on path from s to v
    private int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        Arrays.fill(marked, false);
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, this.s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    // is there a path from s to v?
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // path from s to v
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
