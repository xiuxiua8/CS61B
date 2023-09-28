import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void testGold() {
        StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
        int flag = 1; //this means that the Student's solution is right.
        for (int i = 0; i < 100; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                sad2.addLast(i);
                if (sad1.size() == 0) {
                    break;
                }
                if (sad2.removeLast() != sad1.removeFirst()) {
                    flag = 0;
                    break;
                }
            } else {
                sad1.addFirst(i);
                sad2.addFirst(i);
                if (sad1.size() == 0) {
                    break;
                }
                if (sad2.removeFirst() != sad1.removeFirst()) {
                    flag = 0;
                    break;
                }
            }

        }
        sad1.printDeque();
        //sad2.printDeque();
        assertEquals(1, flag);

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addFirst(i);
                sad2.addFirst(i);
            }
            else {
                sad1.addLast(i);
                sad2.addFirst(i);
            }
        }
        for (int i = 0; i < 10; i += 1) {
            //assertEquals(sad1.removeFirst(),sad2.removeFirst());
            Integer actual = sad1.removeFirst();
            Integer expected = sad2.removeFirst();
            assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual
                            + " not equal to " + expected + "!",
                    expected, actual);

        }
    }
}
