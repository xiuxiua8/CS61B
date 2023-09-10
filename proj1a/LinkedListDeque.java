/** DLLists based list.
 *  @author xiuxiu
 */

/* Invariants:
 addLast: The next item we want to add, will go into position size
 getLast: The item we want to return is in position size - 1
 size: The number of items in the list should be size.

 Deque (usually pronounced like “deck”) is an irregular acronym of double-ended queue.
 Double-ended queues are sequence containers with dynamic sizes that can be expanded or contracted on both ends
 (either its front or its back).
*/
public class LinkedListDeque<T> {


    /* Double-ended queues are sequence containers with dynamic sizes that can be expanded or contracted on both ends (either its front or its back).*/

    private class stuffNode {
        public stuffNode prev;
        public T item;
        public stuffNode next;
        public stuffNode(stuffNode Front, T Item,stuffNode Next){
            prev = Front;
            item = Item;
            next = Next;
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
    private stuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new stuffNode(null ,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(T x) {
        sentinel = new stuffNode(null ,null,null);
        sentinel.next = new stuffNode(sentinel,x,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Creates a deep copy of other.*/
    public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new stuffNode(null ,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (int i = 0; i < other.size();i++) {
            this.addLast((T) other.get(i+1));
        }
    }

    /** Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        size = size + 1;
        sentinel.next = new stuffNode(sentinel, item, sentinel.next);
        if (size == 1){
            sentinel.prev = sentinel.next;
        }
        sentinel.next.next.prev=sentinel.next;
    }

    /** Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        size = size + 1;
        sentinel.prev.next = new stuffNode(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
    }

    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        /* if (sentinel.next.item == null){
            return true;
        }
        return false;*/
        if (size == 0){
            return true;
        }
        return false;
    }

    /**  Returns the number of items in the deque.*/
    public int size(){
        return size;
    }
    /** Prints the items in the deque from first to last, separated by a space. Once all the items have been printed, print out a new line.*/
    public void printDeque() {
        stuffNode p = sentinel.next;
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

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
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
        stuffNode p = sentinel;
        if (index > size ){
            return null;
        }
        for (int i = 0; i < index ; i++){
            p = p.next;
        }
        //System.out.println(p.item);
        return p.item;
    }


    /** Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        if (index > size ){
            return null;
        }
        return sentinel.get(index);
    }


    public static void main(String[] args) {
        LinkedListDeque<Integer> LLD = new LinkedListDeque<>();
        if (LLD.isEmpty()){
            System.out.println("LLD is empty.");
        }
        //LLD.addFirst(1);
        LLD.addLast(2);
        if (LLD.isEmpty()){
            System.out.println("LLD is empty.");
        }
        System.out.println(LLD.size());
        LLD.addLast(3);
        LLD.addLast(4);
        LLD.addLast(5);
        LLD.printDeque();
        LinkedListDeque<Integer> LLD2 = new LinkedListDeque<>(LLD);
        System.out.println(LLD.get(4));
        System.out.println(LLD.getRecursive(4));
        LLD2.printDeque();
        LLD.removeFirst();
        LLD.removeLast();
        System.out.println(LLD.size());
    }
}