[TOC]

# Abstract Data Types (ADT)

1. ADT - Abstract Data Types

   An ADT is defined only by its operations, not implementations.

   严格来说，interface不算是ADT，因为interface里可以有default methods。



2. 常见ADT
   - Stacks:
     - `push(int x)`
     - `int pop()`
     
   - Lists:
   
     - `add(int i)`：
     - `int get(int i)`：查找位置i处的元素
   
   - Sets:
     
     - `add(int i)`
     - `boolean contains(int i)`
     
   - Maps：就是Python中的dictionary
     - `put(K key, V Value)`：往map中插入一对key-value（键-值）
     
     - `v get(K key)`：查找键为key的元素的值
   
   

  这一章中我们要学习`Set` ADT的实现方法。



# Search Trees       

## Binary Search Tree (BST)

1. BST

   BST是二叉树的一个特例。对BST中的任一结点，其**左**子树中每个结点的值都比该结点**小**，**右**子树中每个结点的值都比该结点**大**。

   ```java
   public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
       private Node root;
   
       private class Node {
           private K key;
           private V value;
           private Node left, right;
           private int size;
   
           Node(K key, V value, int size) {
               this.key = key;
               this.value = value;
               this.size = size;
           }
       }
       ....
   }
   ```

   

2. BST API

   BST可以认为是`Map`或`Set`的一种实施方式，其主要方法有：

   - `get()`：查找结点

     ```java
     	private V get(Node x, K key) {
             if (key == null) {
                 throw new IllegalArgumentException("calls get() with a null key");
             }
             if (x == null) {
                 return null;
             }
             int cmp = key.compareTo(x.key);
             if (cmp < 0) {
                 return get(x.left, key);
             } else if (cmp > 0) {
                 return get(x.right, key);
             } else {
                 return x.value;
             }
         }
     ```

     

   - `put()`：插入结点

     ```java
     	public void put(K key, V value) {
             root = put(root, key, value);
         }
     
         private Node put(Node x, K key, V value) {
             if (x == null) {
                 return new Node(key, value, 1);
             }
             int cmp = key.compareTo(x.key);
             if (cmp < 0) {
                 x.left = put(x.left, key, value);
             } else if (cmp > 0) {
                 x.right = put(x.right, key, value);
             } else {
                 // overwriting the old value with the new value if the key already exists
                 x.value = value;
             }
             x.size = 1 + size(x.left) + size(x.right);
             return x;
         }
     ```

     

   - `remove()`：删除结点
     
     分三种情况：
     
     - no children：直接删除
     - 1 child：删除该结点，然后把子树放到它原来的位置
     - 2 children：找到左子树中最大的结点（predecessor），或右子树中最小的结点（successor），放到该结点的位置。在这里我们采用后者。
     
     ```java
     	public V remove(K key) {
             if (key == null) {
                 throw new IllegalArgumentException("calls remove() with a null key");
             }
             V toRemove = get(key);
             root = remove(root, key);
             return toRemove;
         }
     
         private Node remove(Node x, K key) {
             if (x == null) {
                 return null;
             }
             int cmp = key.compareTo(x.key);
             if (cmp < 0) {
                 x.left = remove(x.left, key);
             } else if (cmp > 0) {
                 x.right = remove(x.right, key);
             } else {
                 if (x.right != null) {
                     Node t = x;
                     x = getMin(t.right); // 找到右子树中最小的结点
                     x.right = removeMin(t.right); // 删除右子树中最小结点并返回该子树的根节点
                     x.left = t.left;
                 } else {
                     return x.left;
                 }
             }
             x.size = size(x.left) + size(x.right) + 1;
             return x;
         }
     
     	private K getMin() {
             return getMin(root).key;
         }
     
         private Node getMin(Node x) {
             if (x.left == null) {
                 return x;
             }
             return getMin(x.left);
         }
     
         private void removeMin() {
             root = removeMin(root);
         }
     
         private Node removeMin(Node x) {
             if (x.left == null) {
                 return x.right;
             }
             x.left = removeMin(x.left);
             x.size = size(x.left) + size(x.right) + 1;
             return x;
         }
     ```
   
   
   
   代码参考：https://algs4.cs.princeton.edu/32bst/BST.java.html
   
   


3. BST的其它可实现方法
	
	- `select(int i)`：查找BST中第i小的元素
	- `rank(K x)`：查找元素x在BST中的排序（按从小到大排序，编号从0开始）
	- `subSet(K from, K to)`：返回键在from~to之间的所有元素
	- `nearest(K x)`：查找与x差值的绝对值最小的元素
	
	


4. 时间复杂度分析

   BST各操作的时间复杂度与树的高度有关。
   
   树的高度：
   
   - worst case：Θ(N) 【树极不平衡】
   - best case: Θ(logN)



## B-Tree

1. B树

   B树是一种**多路平衡查找树**，它的每一个结点最多包含L个元素，其中L被称为B树的**阶**。

   B-Tree visualization: https://www.cs.usfca.edu/~galles/visualization/BTree.html



2. B树特点：

   - 根节点至少有2个子结点
   - 一个中间结点如果有k个元素，那么它一定有k+1个子结点；中间结点中的k个元素按从小到大排列，它们正好是k+1个子结点的值域划分
   - 所有叶结点高度相同



3. 2-3树/ 2-3-4树

   - 2-3树：每个结点可以有2~3个子结点，即L=2
   - 2-3-4树（又名2-4树）：每个结点可以有2~4个子结点，即L=3
     - 2-node：该结点有1个元素，2个子结点。如下图（左）中的e结点
     - 3-node：该节点有2个元素，3个子结点。如下图（左）中的mq结点
     - 4-node：该结点有3个元素，4个子结点。如下图（左）中的suw结点

   ![image-20210404103746640](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210404103746640.png)



4. 时间复杂度分析：

   假设每个结点最多能有L个元素（在实际应用中，L取值一般上千）。

   - 高度：
     - worst case：Θ(log<sub>2</sub>N) 。B树大大提升了BST在worst case下的表现
     - best case: Θ(log<sub>L+1</sub>N) ，由于L是常数，可简化为Θ(LlogN) 



## Red-Black Tree

1. 平衡二叉查找树

   - 满足BST性质
   - 对任一结点，其左右子树的高度差<=1
   - 树的高度在最糟糕的情况下为Θ(log2N)

   显然，前面学过的BST是**不平衡**的二叉查找树，在这一章我们要学习如何实现平衡二叉查找树。



2. 维持树平衡的方法——Rotation

   - `rotateLeft(G)`：假设x为G的右孩子，使G成为x的左孩子，x原本的左孩子变为G的右孩子

     ```java
     private Node rotateLeft(Node G) {
         // assert (G != null) && isRed(G.right);
         Node x = G.right;
         G.right = x.left;
         x.left = G;
         return x;
     }
     ```

   - `rotateRight(G)`：假设x为G的左孩子，旋转使G成为x的右孩子，x原本的右孩子变为G的左孩子

     ```java
     private Node rotateRight(Node G) {
         // assert (G != null) && isRed(G.left)
         Node x = G.left;
         G.left = x.right;
         x.right = G;
         return x;
     }
     ```

     

3. 红黑树

   前面我们学习了BST和B树：

   - BST：在代码上较好实现，但是可能遇到不平衡的问题，这个问题可以通过rotation解决
   - B树：是一种平衡二叉树，它的平衡是在construction中实现的（即无需rotation），但是代码实现很麻烦

   接下来我们要构造一种很聪明的数据结构，它在结构上与2-3树近似，但是代码实现上又采用BST的实现方法，这就是Left Leaning Red-Black tree，属于红黑树的一种。

   

   2-3树 ==> **Left Leaning Red-Black tree（LLRB）**

   - 2-node：无需改变

   - 3-node：将该结点拆开，小元素作为大元素的左孩子，两者之间用<span style="color:red">红线</span>连接

     ![Encoding a 3-node in a red-black BST](https://algs4.cs.princeton.edu/33balanced/images/redblack-encoding.png)

   LLRB性质：

   - 一个结点至多有一条<span style="color:red">红线</span>

   - <span style="color:red">红线</span>连接的一定是左孩子，不可能是右孩子

   - 每一条从根节点到叶结点的路径中，黑线数目都相同（因为B树中所有叶结点高度相同）

   - 假设2-3树的高度为H，则对应的LLRB的高度至多为2H+1（每一个结点都是3-node），所以LLRB的高度为Θ(logN)

     

4. 如何维持LLRB的性质？

   - 插入新结点：用<span style="color:red">红线</span>连接，且必须放到叶结点上

   - 如果<span style="color:red">红线</span>连接了右孩子：rotateLeft()

     

     <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210406153830743.png" alt="image-20210406153830743" style="zoom:75%;" />

     ```java
    Node rotateLeft(Node h) {
         Node x = h.right;
         h.right = x.left;
         x.left = h;
         x.color = h.color;
         h.color = RED;
         x.size = h.size;
         h.size = 1 + size(h.left) + size(h.right);
     }
     ```
   
     

   - 连续两条连接左孩子的<span style="color:red">红线</span>：rotateRight()

     

     ![image-20210406154500091](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210406154500091.png)

     

     ```java
    Node rotateRight(Node h) {
         Node x = h.left;
         h.left = x.right;
         x.right = h;
         x.color = h.color;
         h.color = RED;
         x.size = h.size;
         h.size = 1 + size(h.left) + size(h.right);
     }
     ```
   
     

   - 同一结点的两个孩子都用<span style="color:red">红线</span>连接：flip()，树的结构不变，翻转父结点和子结点的颜色

     ![image-20210406154947300](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210406154947300.png)

     ```java
      Node flipColors(Node h) {
         h.color = RED;
         h.left.color = BLACK;
         h.right.color = BLACK;
     }
     ```
   
   

4. LLBR时间复杂度分析

   - 高度：Θ(logN)
   - add()：
     - 插入新结点：Θ(logN)
     - rotation & color flip： Θ(logN)
   
   

6. 补充说明
   - Java中的[TreeMap](https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/999dbd4192d0f819cb5224f26e9e7fa75ca6f289/src/java.base/share/classes/java/util/TreeMap.java)是一种红黑树，它与2-4树一一对应，运行效率比LLBR更高。
   - 除了红黑树外，平衡二叉查找树还有AVL树，splay树，treaps等上百种。
   - 除了二叉查找树以外，别的链式结构也可以实现`Set`和`Map`，如skip list和hash table。



## 处理多维数据

前面学习的搜索树都只能用于处理一维数据，下面我们将学习如何处理二维及以上的数据。



1. 2D数据——**QuadTree**

   QuadTree的每个结点都有4个子结点，将空间分割为NE（northeast）、NW、SE、SW四个部分。

   | 2D 数据                                                      | QuadTree                                                     |
   | ------------------------------------------------------------ | ------------------------------------------------------------ |
   | ![image-20210409105602561](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409105602561.png) | ![image-20210409105617033](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409105617033.png) |

   

2. 3D数据——**OctTree**

   - OctTree的每个结点有8个子结点
   - OctTree非常常用

   <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409105938438.png" alt="image-20210409105938438" style="zoom:67%;" />



3. 更高维度的数据——**k-D Tree**

   k-D Tree：

   - 假设数据有k维，维度编号为0，1，...，k-1
   - KD-Tree的同一层结点只分割一个维度
   - 对深度为h的结点，它将第（h % k）维度分割为2部分

   

   例如，对2维数据：

   - 根节点将空间分为左右两部分（x轴）
   - depth=1的结点将空间分为上下两部分（y轴）
   - depth=2的结点将空间分为左右两部分（x轴）
   - ……

   | 2D数据                                                       | k-D Tree                                                     |
   | ------------------------------------------------------------ | ------------------------------------------------------------ |
   | ![image-20210409111811518](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409111811518.png) | ![image-20210409111948947](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409111948947.png) |
   
   
   
   **Nearest Pseudocode：**
   
   ```java
   nearest(Node n, Point goal, Node best) {
       if (n == null) return best;
       if (n.dist(goal) < best.dist(goal)) best = n;
       if (goal < n) {
           goodSide = goal.left; // 或goodSide = goal.up;
           badSide = goal.right; // 或badSide = goal.down;
       } else {
           goodSide = goal.right;
           badSide = goal.left;
       }
       best = nearest(goodSide, goal, best);
       if badSide could have something useful {
           best = nearest(badSide, goal, best);
       }
       return best;
   }
   ```



# Hashing

前面我们学习了用搜索树来实现`Set` 或`Map`ADT，但它们的缺点是要求数据必须comparable，并且代码实现较为麻烦。

这一章我们将学习哈希，这是`Set` 和`Map`ADT最常见的实现方式。



1. Hashing
   - hash function：将元素映射为一个整数，并作为数据储存时的下标
     - 元素通常是一个**key-value pair**，key（键）被映射为哈希值，然后key-value一起被储存到该位置
   - hash code：元素经哈希函数映射后得到的值
   - hash table：储存元素哈希值的数据结构
     - n：数据总数
     - m：哈希表长度
     - **load factor 装载因子**：n / m



2. **Collision 哈希冲突**

   当key1 != key2 且hashCode(key1) == hashCode(key2)时，即发生了哈希冲突。
   
   


3. 如何计算字符串的哈希值？

   各字符“进位”相加再取余（模通常取哈希表的长度）。例如：

   <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409203848117.png" alt="image-20210409203848117" style="zoom:40%;" />

   对于英文，char可以用ASCII码转换为int；但如果涉及中文、韩文等更复杂的文字，ASCII码值就不够用了，要用到Unicode，这时候底数会很大，将造成数值**overflow**。例如：

   <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409204115765.png" alt="image-20210409204115765" style="zoom:40%;" />

   Java中int的最大值为2^31 = 2,147,483,648。
   数值溢出必然导致**哈希冲突**。
   
   

4. 如何解决哈希冲突——**Chaining 链表法**

   哈希表有N个buckets，每个bucket对应一条链表，把哈希值相同的元素放到同一链表中。

   <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210409235535819.png" alt="image-20210409235535819" style="zoom:60%;" />

   

   假设哈希表一共有N个buckets，M个元素，则链表法的时间复杂度为：

   - 插入元素：Θ(1)
   - 查找元素：O(N/M)（假设元素分布均匀）
   
   为使O(N/M) = Θ(1)，则必须使O(M) = Θ(N)，实现办法是**动态扩容**：
   
   - 当N/M（装载因子）>=1.5（设置一个阈值即可）时，扩大哈希表的长度为原来的2倍，并重新计算表中原有对象的哈希值，此时模变为新表长度
   - 此操作的均摊时间为Θ(1)。
   
   
   
   注：解决哈希冲突还有另一种方法是“**开放寻址法（Open Addressing）**”，这里略过。



5. Good hash function
   - **equal objects have the same hash code**
   - 计算得到的哈希值尽量随机分布，这样元素才能分布均匀，`get(K key)`所需要的时间就更少
   - 底数通常选择一个较小的质数
     - why small？
       - 计算更快
       - 避免数据溢出。在实操中，数值溢出比collision更严重
     - ==why prime？==https://stackoverflow.com/questions/3613102/why-use-a-prime-number-in-hashcode
       - 非偶数
       - 更不易造成哈希冲突
     - Java的`hashCode()`函数的底数为31



6. Hash Tables in Java
   - `java.util.HashMap`和`java.util.HashSet`是哈希表的两种实现方式
     
     - Java中每个对象都有一个`.hashCode()`方法，这是`Object`类的默认方法，里面储存有该对象的哈希值。所以`HashMap`或`HashSet`并不计算对象的哈希值，而是用`.hashCode()`直接从对象中提取其哈希值。
   - `Math.floorMod()`对负数取模结果为正值。→适用于计算哈希值
   - python中的dictionary其实就是哈希表
   - 建议：
     - 不要用哈希表储存key能改变的对象，否则易导致对象丢失！（对象改变→hashCode改变→在哈希表中找不到该对象）
     
     - 重写`equals()`时，记得重写`hashCode()`，确保相等的对象有相同的哈希值。例如，下面的类存在的问题是：相等的对象不一定有相同的哈希值
     
       ```java
       class Course {
           int courseCode;
           int yearOffered;
           String[] staff;
           ...
           @Override
           public int hashCode() {
               return yearOffered + courseCode;
           }
           
           @Override
           public boolean equals(Object o) {
               Course c = (Course) o;
               return c.courseCode == courseCode;
           }
       }
       ```
       
       
       
       



