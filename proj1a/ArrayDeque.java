/** Array based list.
 *  @author xiuxiu
 */

//         0 1  2 3 4 5 6 7
// items: [6 9 -1 2 0 0 0 0 ...]
// size: 5

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.
*/
public class ArrayDeque<T> {
    private T[] items;
    private int size;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }


    /** Creates a deep copy of other. */
    /* public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.items.length];
        for (int i = 0; i < other.items.length; i++) {
            items[i] = (T) other.items[i];
        }
        size = other.size;
    }*/


    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        if (size == items.length) {
            //this.resize(size + 1);
            this.resize(size + 1);
        }
        for (int i = size; i > 0; i--) {
            items[i] = items[i-1];
        }
        items[0] = item;
        size = size + 1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(T item) {
        if (size == items.length) {
            //this.resize(size + 1);
            this.resize(size + 1);
        }
        items[size] = item;
        size = size + 1;
    }


    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        if (size == 0) {
            System.out.println("The deque is empty.\n");
        }
        for (int i = 0; i < size; i++) {
            System.out.print(items[i] + " ");
        }
        System.out.print("\n");
    }

    /** Gets the ith item in the list (0 is the front).
     * If no such item exists, returns null. Must not alter the deque! */
    public T get(int i) {
        return  items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T front = get(0);


        for (int i = 0; i < size - 1; i++) {
            items[i] = items[i + 1];
        }

        items[size - 1] = null; //this may not right.

        size = size - 1;
        if (size <= items.length * 0.25 && size > 16) {
            resize((int) (items.length * 0.25));
        }
        return front;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T back = get(size - 1);
        items[size - 1] = null;
        size = size - 1;
        if (size <= items.length * 0.25 && size > 16) {
            resize((int) (items.length * 0.25));
        }
        return back;
    }

    /*public static void main(String[] args) {
        ArrayDeque<Integer> aDeque = new ArrayDeque<>();
        if (aDeque.isEmpty()) {
            System.out.println("aDeque is empty.");
        }
        aDeque.addFirst(1);
        aDeque.addLast(2);
        if (aDeque.isEmpty()) {
            System.out.println("aDeque is empty.");
        }
        System.out.println(aDeque.size());
        aDeque.addLast(3);
        aDeque.addLast(4);
        aDeque.addLast(5);
        aDeque.printDeque();
        for (int i = 0; i < 17; i++){
            aDeque.addFirst(i);
        }
        //ArrayDeque<Integer> aDeque2 = new ArrayDeque<>(aDeque);
        System.out.println(aDeque.get(4));
        //aDeque2.printDeque();
        //System.out.println(aDeque.items.length);
        aDeque.removeFirst();
        aDeque.removeLast();
        System.out.println(aDeque.size());
        aDeque.printDeque();
    }*/

}

