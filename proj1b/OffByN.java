public class OffByN implements CharacterComparator {
    private int number;
    public OffByN(int N) {
        number = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == number || y - x == number) {
            return true;
        }
        return false;
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

            if (!(equalChars(stack.removeFirst(), stack.removeLast()))) {
                return false;
            }
        }
        return true;
    }


}
