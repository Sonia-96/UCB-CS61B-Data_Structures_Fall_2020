import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private List<Entry>[] buckets;
    int size;

    private class Entry {
        private K key;
        private V val;
        private Entry(K k, V v) {
            key = k;
            val = v;
        }
    }

    public MyHashMap() {
        size = 0;
        buckets = new ArrayList[initialSize];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public MyHashMap(int initialSize) {
        size = 0;
        this.initialSize = initialSize;
        buckets = new ArrayList[this.initialSize];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public MyHashMap(int initialSize, double loadFactor) {
        size = 0;
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        buckets = new ArrayList[this.initialSize];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        for (Entry e : buckets[hash(key)]) {
            if (key.equals(e.key)) {
                return e.val;
            }
        }
        return null;
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), buckets.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (loadFactor() >= loadFactor) {
            resize(buckets.length * 2);
        }
        int idx = hash(key);
        for (Entry e : buckets[idx]) {
            if (key.equals(e.key)) {
                e.val = value;
                return;
            }
        }
        buckets[idx].add(new Entry(key, value));
        size += 1;
    }

    private double loadFactor() {
        return size * 1.0 / buckets.length;
    }

    private void resize(int capacity) {
        MyHashMap<K, V> newMap = new MyHashMap<>(capacity);
        for (List<Entry> bucket : buckets) {
            for (Entry e : bucket) {
                newMap.put(e.key, e.val);
            }
        }
        this.buckets = newMap.buckets;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (List<Entry> bucket : buckets) {
            for (Entry e : bucket) {
                keySet.add(e.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
