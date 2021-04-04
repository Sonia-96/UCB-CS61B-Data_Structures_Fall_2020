import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

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

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls containsKey() with a null key");
        }
        return get(key) != null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node n = get(root, key);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    private Node get(Node x, K key) {
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
            return x;
        }
    }

    @Override
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

    // in-order traversal
    private void printInOrder() {
        Iterator<K> itr = iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
        System.out.println();
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (K key : this) {
            set.add(key);
        }
        return set;
    }

    // find the smallest key
    private K getMin() {
        return getMin(root).key;
    }

    private Node getMin(Node x) {
        if (x.left == null) {
            return x;
        }
        return getMin(x.left);
    }

    // removes the smallest key
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

    // remove the node and return its value
    @Override
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
                x.right = removeMin(t.right); // 删除右子树中最小的结点
                x.left = t.left;
            } else {
                return x.left;
            }
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public V remove(K key, V value) {
        V toRemove = get(key);
        if (!toRemove.equals(value)) {
            return null;
        } else {
            root = remove(root, key);
            return toRemove;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node x) {
            // push root and all of its left children
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node curr = stack.pop();
            Node x = curr.right;
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
            return curr.key;
        }
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bst = new BSTMap<>();
        for (int i = 1; i < 20; i++) {
            bst.put("a" + i, i);
        }
        for (String s : bst) {
            System.out.print(s + " ");
        }
        System.out.println();
        bst.printInOrder();
    }
}
