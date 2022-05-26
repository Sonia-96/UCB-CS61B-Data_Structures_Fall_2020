1. Trie
   
   Trie树是一种多叉树，储存字符串的公共前缀。
   
   - 名称来源：Re**trie**val Tree的缩写
   
   - 特点：
     - 根节点为空
     - 一个结点表示一个字符
     - 同一结点的所有子结点都具有相同的前缀
     - 从根节点到一个模式串的尾结点（需用变量标记）的一条路径表示一个模式串
     
   - 例如，用Trie储存字符串“sam”, “sad”, “sap”, “same”, “a”,  “awls”：
   
     <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210419113330688.png" alt="image-20210419113330688" style="zoom:50%;" />



2. implementation

   - 结点表示：

       ```java
   public class TrieSet {
           private static final int R = 128;
           Node root;
       
           private static class Node {
               private boolean isKey; // 标记该结点是否为一个字符串的尾结点
               private DataIndexedCharMap<Node> next;
       
               private Node(boolean b, int R) {
                   isKey = b;
                   next = new DataIndexedCharMap<>(R);
               }
           }
       }
       ```

   - 记录子结点（child tracking）的三种方法：
     - `DataIndexedCharMap`：用长度为128的数组储存子结点，数组下标即为子结点的字符的ASCII码值
       - 时间复杂度：Θ(1)
       - 空间复杂度：Θ(128)
     - BST：
     - Hash Table
       - BST和Hash Table比`DataIndexedCharMap`在时间效率上要低一点，但是更节省内存



3. Prefix Matching

   Trie树的最大优势是可以进行**前缀匹配**，比如：

   - `keysWithPrefix()`：找出所有前缀相同的字符串
   - `longestPrefixOf()`：找出某字符串的最长前缀



4. Autocomplete

   Trie树的一大应用就是autocomplete，即预测用户的剩余输入。例如，在Google搜索框中输入“hello”，下方会自动出现十个以“hello”开头的字符串。

   实现方法：
   
   - 每个字符串配套一个`value`，表示其重要程度
   - 前缀匹配时只展示重要程度前10的字符串
     - 尾结点要储存所在字符串的`value`，另外每个节点都要储存所在路径的最大value
     - 用PQ储存`value`前10的字符串
   
   <img src="C:\Users\Sonia\AppData\Roaming\Typora\typora-user-images\image-20210419112529718.png" alt="image-20210419112529718" style="zoom: 50%;" />



5. 总结

   `Set`和`Map`不同的实施方法，对key的要求是不一样的

   - 平衡BST：comparable
   - 哈希表：hashable
   - Trie：strings