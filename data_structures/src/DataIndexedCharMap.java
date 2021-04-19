import java.util.ArrayList;
import java.util.List;

public class DataIndexedCharMap<T> {
    public T[] items;

    public DataIndexedCharMap(int R) {
        items = (T[]) new Object[R];
    }

    public T get(int i) {
        return items[i];
    }

    public List<Character> keys() {
        List<Character> keys = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                keys.add((char) i);
            }
        }
        return keys;
    }

    public void add(int i, T item) {
        items[i] = item;
    }

    public boolean containsKey(char key) {
        return items[key] != null;
    }
}
