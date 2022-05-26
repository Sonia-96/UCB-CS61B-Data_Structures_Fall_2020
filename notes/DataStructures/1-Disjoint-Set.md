# Disjoint Set

1. 本章目的：
   - 学习并查集的代码实现
   - 学习数据结构的优化过程



2. 优化过程
   - ListOfSets: 同一个集合的元素放到同一`set`里，这些`set`又放到`List`里。如[{1, 2, 3}, {4, 5}, {6, 7, 8}]
     - 缺点：`connect(p, q)`和`isConnected(p, q)`都需要遍历列表，时间复杂度均为O(N)
   - QuickFind: 用数组`id[]`储存每个集合的id（即每棵树的根节点）
     - 优点：`isConnected(p, q)`的时间复杂度降到O(1)
     - 缺点：`connect(p, q)`时需要遍历数组，然后更新每个子结点所在集合的id，时间复杂度为Θ(N)
   - **QuickUnion**：数组`id[]`储存每个结点的父结点，`connect(p, q)`默认p做q的子树，且是两树根节点相连
     - 优点：`connect(p, q)`时间复杂度降到O(N)
     - 缺点：树不平衡（结点都分布在一侧）时，树的高度为O(N)，`isConnected(p, q)`很费时
   - **WeightedQuickUnion**：记录树的size（结点数量），合并时size小的树作为size大的树的子树
     - 优点：树的高度为O(logN)
     - 思考：Union by size 而不是union by height？原因：
       - 二者的worst case performance都是Θ(logN)
       - height的代码实现比size要麻烦
   - **PathCompression**：在查找某结点时，将路径上经过的结点的parent都修改为根节点
     - 优点：`connect`和`isconnected`的时间复杂度降到近乎常数

   | implementation            | construction | connect | isConnected | N元素+M操作 |
   | ------------------------- | ------------ | ------- | ----------- | ----------- |
   | List Of Sets              | Θ(N)         | O(N)    | O(N)        | O(MN)       |
   | Quick Find                | Θ(N)         | Θ(N)    | O(1)        | Θ(MN)       |
   | Quick Union               | Θ(N)         | O(N)    | O(N)        | O(MN)       |
   | Weighted Quick Union      | Θ(N)         | O(logN) | O(logN)     | O(N+MlogN)  |
   | WQU with Path Compression | Θ(N)         | O(α(N)) | O(α(N))     | O(N+Mα(N))  |

   注：α(N)=[log\*N](https://en.wikipedia.org/wiki/Iterated_logarithm)，该函数对任何realistic input都<5，因此可以认为该函数为常数。



3. 并查集代码实现

   ```java
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
           if (parent[v1] >= 0) {
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
   
   ```

   