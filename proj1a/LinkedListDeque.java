/** DLLists based list.
 *  @author xiuxiu
 */

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.

 Deque (usually pronounced like “deck”) is an irregular acronym of double-ended queue.
 Double-ended queues are sequence containers with dynamic sizes
 that can be expanded or contracted on both ends
 (either its front or its back).
*/
public class LinkedListDeque<T> {


    /* Double-ended queues are sequence containers with dynamic sizes
    that can be expanded or contracted on both ends (either its front or its back).*/

    private class StuffNode {
        private StuffNode prev;
        private T item;
        private StuffNode next;
        public StuffNode(StuffNode f, T i, StuffNode n) {
            prev = f;
            item = i;
            next = n;
        }
        /** Returns the ith item of this IntList. */
        public T get(int i) {
            if (i == 0) {
                return item;
            }
            return next.get(i - 1);
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /*public LinkedListDeque(T x) {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = new StuffNode(sentinel,x,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }*/

    /** Creates a deep copy of other.*/
    /*public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (int i = 0; i < other.size(); i++) {
            this.addLast((T) other.get(i + 1));
        }
    }*/

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        size = size + 1;
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        if (size == 1) {
            sentinel.prev = sentinel.next;
        }
        sentinel.next.next.prev = sentinel.next;
    }

    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        size = size + 1;
        sentinel.prev.next = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
    }

    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        /* if (sentinel.next.item == null){
            return true;
        }
        return false;*/
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**  Returns the number of items in the deque.*/
    public int size() {
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        StuffNode p = sentinel.next;
        if (size == 0) {
            System.out.println("The deque is empty.");
        }
        for (int i = 0; i < size ; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println("\n");
    }

    /**Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst() {
        if (size == 0){
            return null;
        }
        T front = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return front;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    public T removeLast() {
        if (size == 0){
            return null;
        }
        T back = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return back;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index) {
        StuffNode p = sentinel;
        if (index > size ) {
            return null;
        }
        for (int i = 0; i <= index ; i++){
            p = p.next;
        }
        //System.out.println(p.item);
        return p.item;
    }


    /** Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return sentinel.next.get(index);
    }


    /*public static void main(String[] args) {
        LinkedListDeque<Integer> llD = new LinkedListDeque<>();
        if (llD.isEmpty()) {
            System.out.println("llD is empty.");
        }
        llD.addFirst(1);
        llD.addLast(2);
        if (llD.isEmpty()) {
            System.out.println("llD is empty.");
        }
        System.out.println(llD.size());
        llD.addLast(3);
        llD.addLast(4);
        llD.addLast(5);
        llD.printDeque();
        //LinkedListDeque<Integer> llD2 = new LinkedListDeque<>(llD);
        System.out.println(llD.get(4));
        System.out.println(llD.getRecursive(4));
        //llD2.printDeque();
        llD.removeFirst();
        llD.removeLast();
        System.out.println(llD.size());
    }
    */
}

