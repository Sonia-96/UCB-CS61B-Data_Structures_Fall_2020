import java.util.ArrayList;
import java.util.List;

public class TrieSet {
    private static final int R = 128;
    Node root;

    private static class Node {
        private boolean isKey;
        private final DataIndexedCharMap<Node> next;

        private Node(boolean b, int R) {
            isKey = b;
            next = new DataIndexedCharMap<>(R);
        }
    }

    public TrieSet() {
        root = new Node(false, R);
    }

    public void insert(String s) {
        Node n = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!n.next.containsKey(c)) {
                n.next.add(c, new Node(false, R));
            }
            n = n.next.get(c);
        }
        n.isKey = true;
    }

    public List<String> collect() {
        List<String> res = new ArrayList<>();
        for (char c : root.next.keys()) {
            colHelp(String.valueOf(c), res, root.next.get(c));
        }
        return res;
    }

    private void colHelp(String s, List<String> res, Node n) {
        if (n.isKey) {
            res.add(s);
        }
        for (char c : n.next.keys()) {
            colHelp(s + c, res, n.next.get(c));
        }
    }

    public List<String> keysWithPrefix(String s) {
        Node n = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!n.next.containsKey(c)) {
                return null;
            }
            n = n.next.get(c);
        }
        List<String> res = new ArrayList<>();
        for (char c : n.next.keys()) {
            colHelp(s + c, res, n.next.get(c));
        }
        return res;
    }

    public String longestPrefixOf(String s) {
        Node n = root;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!n.next.containsKey(c)) {
                break;
            }
            res.append(c);
            n = n.next.get(c);
        }
        return res.toString();
    }
}
