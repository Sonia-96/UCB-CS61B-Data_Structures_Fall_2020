public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    private static boolean isPalindrome(ArrayDeque<Character> dq) {
        if (dq.size() == 0 || dq.size() == 1) {
            return true;
        } else {
            char first = dq.removeFirst();
            char last = dq.removeLast();
            if (first != last) {
                return false;
            } else {
                return isPalindrome(dq);
            }
        }
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        ArrayDeque<Character> wordDeque = (ArrayDeque) wordToDeque(word);
        return isPalindrome(wordDeque);
    }

    private static boolean isPalindrome(ArrayDeque<Character> dq, CharacterComparator cc) {
        if (dq.size() == 0 || dq.size() == 1) {
            return true;
        } else {
            char first = dq.removeFirst();
            char last = dq.removeLast();
            if (!cc.equalChars(first, last)) {
                return false;
            } else {
                return isPalindrome(dq, cc);
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        ArrayDeque<Character> wordDeque = (ArrayDeque) wordToDeque(word);
        return isPalindrome(wordDeque, cc);
    }
}
