public interface Deque<Item> {

    /**  Adds an item of type Item to the front of the deque.*/
    void addFirst(Item item);

    /** Inserts X into the back of the list.*/
    void addLast(Item x);


    /** Returns true if deque is empty, false otherwise.*/
    boolean isEmpty();

    /** Returns the number of items in the list.*/
    int size();


    /** Prints the items in the deque from first to last, separated by a space.*/
    void printDeque();


    /** Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    Item removeFirst();


    /** Deletes item from back of the list and returns deleted item.*/
    Item removeLast();

    /** Gets the ith item in the list (0 is the front). */
    Item get(int i);


    /**
     * Inserts item into given position.
     * Code from discussion #3
     */
    void insert(Item x, int position);

    /** Prints the list. Works for ANY kind of list. */
    default void print() {
        for (int i = 0; i < size(); i = i + 1) {
            System.out.print(get(i) + " ");
        }
    }


}
