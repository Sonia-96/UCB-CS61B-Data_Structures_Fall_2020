import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private ArrayList<Entry>[] hashTable;
    private int size = 0;

    private class Entry {
        K key;
        V val;
        Entry(K k, V v) {
            key = k;
            val = v;
        }
    }

    public MyHashMap() {
        hashTable = new ArrayList[initialSize];
        for (int i = 0; i < initialSize; i++) {
            hashTable[i] = new ArrayList<>();
        }
    };

    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        hashTable = new ArrayList[this.initialSize];
        for (int i = 0; i < this.initialSize; i++) {
            hashTable[i] = new ArrayList<>();
        }
    };

    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        hashTable = new ArrayList[this.initialSize];
        for (int i = 0; i < this.initialSize; i++) {
            hashTable[i] = new ArrayList<>();
        }
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), hashTable.length);
    }

    private double loadFactor() {
        return size * 1.0 / hashTable.length;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i] = new ArrayList<>();
        }
    };

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    };

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        for (Entry e : hashTable[hash(key)]) {
            if (e.key.equals(key)) {
                return e.val;
            }
        }
        return null;
    };

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    };

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (loadFactor() > loadFactor) {
            resize(hashTable.length * 2);
        }
        int i = hash(key);
        for (Entry e : hashTable[i]) {
            if (e.key.equals(key)) {
                e.val = value;
                return;
            }
        }
        hashTable[i].add(new Entry(key, value));
        size += 1;
    };

    private void resize(int capacity) {
        MyHashMap<K, V> temp = new MyHashMap<>(capacity);
        for (ArrayList<Entry> entries : hashTable) {
            for (Entry e : entries) {
                temp.put(e.key, e.val);
            }
        }
        this.hashTable = temp.hashTable;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (ArrayList<Entry> entries : hashTable) {
            for (Entry e : entries) {
                keySet.add(e.key);
            }
        }
        return keySet;
    };

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    };

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    };
}
