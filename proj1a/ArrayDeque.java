public class ArrayDeque<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
    }

    // first element : nextFirst + 1
    // last element: nextLast - 1
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = rePosition(nextFirst - 1);
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = rePosition(nextLast + 1);
        size += 1;
    }

    public void resize(int capacity) {
        T[] new_items = (T[]) new Object[capacity];
        int start_pos = (new_items.length - size ) / 2;
        if (nextLast - nextFirst - 1 == size) { // reduce the size
            System.arraycopy(items, nextFirst + 1, new_items, start_pos, size);
        } else { // enlarge the size
            System.arraycopy(items, nextFirst + 1, new_items, start_pos, size - nextFirst - 1);
            start_pos += size - nextFirst - 1;
            System.arraycopy(items, 0, new_items, start_pos, nextFirst + 1);
        }
        items = new_items;
        nextFirst = (items.length - size) / 2 - 1;
        nextLast = nextFirst + size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int p = rePosition(nextFirst + 1);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p] + " ");
            p = rePosition(p + 1);
        }
        System.out.println();
    }

    public T removeFirst() {
        nextFirst = rePosition(nextFirst + 1);
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        checkUsageRatio();
        return toRemove;
    }

    public T removeLast() {
        nextLast = rePosition(nextLast - 1);
        T toRemove = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        checkUsageRatio();
        return toRemove;
    }

    // 0-based index
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int p = nextFirst;
        for (int i = 0; i <= index; i++) {
            p += 1;
        }
        return items[rePosition(p)];
    }

    // below are helper functions
    public int rePosition(int pos) {
        return (pos + items.length) % items.length;
    }

    public void checkUsageRatio() {
        double usage_ratio = (double) size / items.length;
        if (items.length >= 16 && usage_ratio < 0.25) {
            resize(items.length / 2);
        }
    }

//    public static void main(String[] args) {
//        ArrayDeque<String> A = new ArrayDeque<>();
//        A.addLast("a");
//        A.addLast("b");
//        A.addFirst("c");
//        A.addLast("d");
//        A.addLast("e");
//        A.addFirst("f");
//        A.addFirst("g");
//        A.addLast("h");
//        A.addFirst("i");
//        A.addFirst("j");
//        A.addLast("k");
//        A.addFirst("l");
//        A.addFirst("m");
//        A.addFirst("n");
//        A.addFirst("o");
//        A.addFirst("p");
//        A.addLast("q");
//        for(int i = 0; i < 10; i++) {
//            A.removeLast();
//        }
//        A.printDeque();
//        System.out.println(A.size());
//        System.out.println(A.get(3));
//    }
}
