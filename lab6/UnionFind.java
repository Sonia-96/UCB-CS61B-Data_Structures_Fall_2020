import java.util.Arrays;

public class UnionFind {
    int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parent = new int[n];
        // set all the parents to be -1 to symbolize that they are disjoint
        Arrays.fill(parent, -1);
    }

    /* Throws an exception if v1 is not a valid vertex. */
    private void validate(int v1) {
        if (v1 < 0 || v1 >= parent.length) {
            throw new IllegalArgumentException("Not a valid vertex!");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        return -1 * parent[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean isConnected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Connecting a
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void connect(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 != root2) {
            int size1 = sizeOf(root1);
            int size2 = sizeOf(root2);
            if (size1 > size2) {
                parent[root1] += parent[root2];
                parent[root2] = root1;
            } else {
                parent[root2] += parent[root1];
                parent[root1] = root2;
            }
        }
    }

    /* Returns the root of the set v1 belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v1) {
        // recursion
        validate(v1);
        if (parent[v1] > 0) {
            parent[v1] = find(parent[v1]);
            return parent[v1];
        }
        return v1;

        /* iteration
        validate(v1);
        int root = v1;
        while (parent[root] > 0) {
            root = parent[root];
        }
        while (v1 != root) {
            int next = parent[v1];
            parent[v1] = root;
            v1 = next;
        }
        return root;
         */

    }
}
