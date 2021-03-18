import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testPalindrome() {
        assertTrue(palindrome.isPalindrome("noon"));
        assertFalse(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("abcdcba"));
        assertTrue(palindrome.isPalindrome(""));
    }

    @Test
    public void testOffByOne() {
        CharacterComparator offByOne = new OffByOne();
        assertTrue(palindrome.isPalindrome("ab", offByOne));
        assertFalse(palindrome.isPalindrome("ad", offByOne));
        assertTrue(palindrome.isPalindrome("flake", offByOne));
    }

    @Test
    public void testOffByN() {
        CharacterComparator offByN = new OffByN(5);
        assertFalse(palindrome.isPalindrome("ae", offByN));
        assertTrue(palindrome.isPalindrome("af", offByN));
        assertTrue(palindrome.isPalindrome("faa", offByN));
    }
}