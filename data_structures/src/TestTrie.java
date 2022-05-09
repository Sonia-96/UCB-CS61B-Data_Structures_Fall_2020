import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestTrie {
    TrieSet trie;

    public TestTrie() {
        trie = new TrieSet();
        String[] strings = {"sam", "sad", "sap", "same", "a", "awls"};
        for (String s : strings) {
            trie.insert(s);
        }
    }

    @Test
    public void testCollect() {
        System.out.println(trie.collect());
    }

    @Test
    public void testPrefixMatching() {
        System.out.println(trie.keysWithPrefix("sa"));
        System.out.println(trie.keysWithPrefix("b"));
    }

    @Test
    public void testLongestPrefixOf() {
        assertEquals("sam", trie.longestPrefixOf("sample"));
        assertEquals("", trie.longestPrefixOf("books"));
    }
}
