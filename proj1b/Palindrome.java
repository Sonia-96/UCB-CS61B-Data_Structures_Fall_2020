public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dq = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dq.addLast(word.charAt(i));
        }
        return dq;
    }

    private static boolean isPalindrome(ArrayDeque<Character> dq) {
        if (dq.size == 0) {
            return true;
        } else if (dq.get(0) != dq.get(dq.size - 1)) {
            return false;
        } else {
            dq.removeFirst();
            dq.removeLast();
            return isPalindrome(dq);
        }

    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        ArrayDeque<Character> wordDeque = (ArrayDeque) wordToDeque(word.toLowerCase());
        return isPalindrome(wordDeque);
    }

    private static boolean isPalindrome(ArrayDeque<Character> dq, CharacterComparator cc) {
        if (dq.size == 0 || dq.size == 1) {
            return true;
        } else if (!cc.equalChars(dq.get(0), dq.get(dq.size - 1))) {
            return false;
        } else {
            dq.removeFirst();
            dq.removeLast();
            return isPalindrome(dq, cc);
        }

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        ArrayDeque<Character> wordDeque = (ArrayDeque) wordToDeque(word.toLowerCase());
        return isPalindrome(wordDeque, cc);
    }
}
