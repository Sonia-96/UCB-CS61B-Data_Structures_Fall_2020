import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<Node> heap; // 1-based index
    private HashMap<T, Integer> itemMapIndex; // key is item, value is index

    private class Node {
        T item;
        double priority;

        public Node(T i, double p) {
            item = i;
            priority = p;
        }
    }

    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        heap.add(null);
        itemMapIndex = new HashMap<>();
    }

    private void swim(int k) {
        int parent = parent(k);
        if (parent > 0 && heap.get(k).priority < heap.get(parent).priority) {
            swap(k, parent);
            swim(parent);
        }
    }

    private void sink(int k) {
        int left = left(k), right = right(k), minIndex = k;
        if (left <= size() && heap.get(minIndex).priority > heap.get(left).priority) {
            minIndex = left;
        }
        if (right <= size() && heap.get(minIndex).priority > heap.get(right).priority) {
            minIndex = right;
        }
        if (minIndex != k) {
            swap(k, minIndex);
            sink(minIndex);
        }
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        heap.add(new Node(item, priority));
        itemMapIndex.put(item, size() + 1);
        swim(size());

    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return itemMapIndex.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return heap.get(1).item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        T toReturn = heap.get(1).item;
        swap(1, size());
        sink(1);
        itemMapIndex.remove(toReturn);
        return toReturn;
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return itemMapIndex.size();
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = itemMapIndex.get(item);
        double oldPriority = heap.get(index).priority;
        heap.get(index).priority = priority;
        if (priority > oldPriority) {
            sink(index);
        } else {
            swim(index);
        }
    }

    private int parent(int k) {
        return k / 2;
    }

    private int left(int k) {
        return k * 2;
    }

    private int right(int k) {
        return k * 2 + 1;
    }

    private void swap(int i, int j) {
        Node temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        itemMapIndex.replace(heap.get(i).item, i);
        itemMapIndex.replace(heap.get(j).item, j);
    }
}
