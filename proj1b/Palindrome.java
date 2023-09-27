public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque<Character> deque = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    /** return true if the given word is a palindrome, and false otherwise.*/
    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }

        Deque<Character> stack = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length() / 2; i += 1) {
            if (2 * i == word.length() - 1) {
                break;
            }
            stack.addLast(word.charAt(i));
            stack.addFirst(word.charAt(word.length() - i - 1));

            if (stack.removeFirst() != stack.removeLast()) {
                return false;
            }
        }
        return true;
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }

        Deque<Character> stack = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length() / 2; i += 1) {
            if (2 * i == word.length() - 1) {
                break;
            }
            stack.addLast(word.charAt(i));
            stack.addFirst(word.charAt(word.length() - i - 1));

            if (!(cc.equalChars(stack.removeFirst(), stack.removeLast()))) {
                return false;
            }
        }
        return true;
    }


}
