import com.sun.source.tree.ReturnTree;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    /* In order to create JUnit tests for your JUnit tests,
    we had to resort to some clever hacks,
    and the easiest way involved this weird stuff.
    Sorry. Luckily in TestOffByN you don’t have to worry about it.
     */

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
    public void testisPalindrome() {
        assertEquals(true, palindrome.isPalindrome("racecar"));
        assertEquals(true, palindrome.isPalindrome("noon"));
        assertEquals(true, palindrome.isPalindrome(""));
        assertEquals(true, palindrome.isPalindrome("a"));
        assertEquals(false, palindrome.isPalindrome("cat"));
    }
    @Test
    public void testOffisPalindrome() {
        OffByOne testword = new OffByOne();
        assertEquals(true, testword.isPalindrome("flake"));
        assertEquals(true, testword.isPalindrome("flame"));
        assertEquals(true, testword.isPalindrome(""));
        assertEquals(true, testword.isPalindrome("a"));
        assertEquals(false, testword.isPalindrome("cat"));
        assertEquals(true, testword.isPalindrome("&flame%"));

    }

    @Test
    public void testOffNisPalindrome() {
        OffByN testword = new OffByN(1);
        assertEquals(true, testword.isPalindrome("flake"));
        assertEquals(true, testword.isPalindrome("flame"));
        assertEquals(true, testword.isPalindrome(""));
        assertEquals(true, testword.isPalindrome("a"));
        assertEquals(false, testword.isPalindrome("cat"));
        assertEquals(true, testword.isPalindrome("&flame%"));

    }





}
