[TOC]

# Priority Queue

1. 优先队列

   priority queue（优先队列，缩写为PQ）是一种用于处理最小值（MinPQ）或最大值（MaxPQ）的**抽象数据结构（ADT）**。



2. PQ API

   下面是MinPQ Interface：

	 ```java
	 /** (Min) Priority Queue: Allowing tracking and removal of the
	  * smallest item in a priority queue. */
	 public interface MinPQ<Item> {
     /** Adds the item to the priority queue. */
        public void add(Item x);
   
        /** Returns the smallest item in the priority queue. */
        public Item getSmallest();
   
        /** Removes the smallest item from the priority queue. */
     public Item removeSmallest();
   
        /** Returns the size of the priority queue. */
     public int size();
    }
    ```



3. PQ的应用：
   - 部分排序（Top K问题）
     - 比起BST，PQ的优势在于：
       - 允许重复的数据
       - 节省内存：只需记录前K个元素，而无需对所有元素排序



# Heaps

1. heap 堆

   - heap是优先队列的一种实现方式。

   - heap是一种**完全二叉树**：<u>除了最后一层，所有层次的结点都填满了；最后一排的叶结点必须从左往右排列</u>。

   - heap又分为两种：
     - max-heap（最大堆）：父结点的值 >= 子结点的值。根节点是堆中最大的结点
     - min-heap（最小堆）：父结点的值 <= 子结点的值。根节点是堆中最小的结点

   下面以min-heap为例说明堆的操作。



2. Heap Operations

   - heap的储存方法：

     - 用**数组**按层次从左到右依次储存结点（这比BST的表示方法节省了2/3的内存）

     - 如果数组从下标1开始储存结点，则对结点`i`

       - 父结点下标：`i / 2`
       - 左孩子下标：`2 * i `
       - 右孩子下标：`2 * i + 1`

       | 结构                                                         | 数组                                                         |
     | ------------------------------------------------------------ | ------------------------------------------------------------ |
       | ![image-20210412172952288](C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210412172952288.png) | <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210412173013247.png" alt="image-20210412173013247" style="zoom:80%;" /> |
   
     - 如果数组从下标0开始储存结点，则对结点`i`

       - 父结点下标：`(i - 1) / 2`
       - 左孩子下标：`2 * i + 1 `
       
       - 右孩子下标：`2 * i + 2`
     
   - helper functions:
     - swim(x)：如果该结点的值小于父结点，则将两者位置交换（即将子结点往上移），直到满足最小堆的性质。
     - sink(x)：如果该结点的值大于子结点，则将两者位置交换（即将父结点往下移），直到满足最小堆的性质。
     
   - add(x)：将`x`插入到数组末尾，然后swim(x)
   
   - getSmallest()：返回根节点的值
   
   - removeSmallest()：将数组中的最后一个元素与根节点交换，数组size-1，然后sink(root)



3. Runtime Analysis
   - add: Θ(logN)
   - getSmallest：Θ(1)
   - removeSmallest：Θ(logN)



# 总结

目前学过的ADT及其implementations（效果差的不列）：

- List
  - LinkedList
  - resizing array
  - 注：List按元素的进出顺序又可分为：stack、queue、deque
- Set & Map
  - BST
  - 2-3 tree
  - LLRB
  - Hash Table
    - chaining
    - open addressing
- Priority Queue
  - Heap
- Disjoint-Set
  - Weighted Quick Union (WQU)
  - WQU by path compression