package synthesizer;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        String log = "";
        arb.enqueue(1);
        arb.dequeue();
        arb.dequeue();
        for (int i = 0; i < 1000; i++) {
            if (arb.fillCount() == 0) {
                int addNumber = StdRandom.uniform(1000);
                int headOrBack = StdRandom.uniform(2);
                arb.enqueue(addNumber);
                log = log + "enqueue(" + addNumber + ")\n";
            } else {
                int x = StdRandom.uniform(2);
                int addNumber = StdRandom.uniform(1000);
                Integer testremoveNumber = 1;
                Integer stdremoveNumber = 1;
                switch (x) {
                    case 0:
                        log = log + "enqueue(" + addNumber + ")\n";
                        arb.enqueue(addNumber);
                        break;
                    case 1:
                        log = log + "dequeue("  + ")\n";
                        arb.dequeue();
                        break;
                    default:
                }
                assertEquals(log, stdremoveNumber, testremoveNumber);
            }
        }
    }


    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
