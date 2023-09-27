/** DLLists based list.
*  @author zilong
*/

/* Invariants:
addLast: The next item we want to add, will go into position size
getLast: The item we want to return is in position size - 1
size: The number of items in the list should be size.
*/

public class LinkedListDeque<Item> implements Deque<Item> {

    /* Double-ended queues are sequence containers with dynamic sizes
    that can be expanded or contracted on both ends (either its front or its back).*/

    private class StuffNode {
        private StuffNode prev;
        private Item item;
        private StuffNode next;
        public StuffNode(StuffNode f, Item i, StuffNode n) {
            prev = f;
            item = i;
            next = n;
        }
        /** Returns the ith item of this IntList. */
        public Item get(int i) {
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

    public LinkedListDeque(Item x) {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = new StuffNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Creates a deep copy of other.*/
    /*public LinkedListDeque(LinkedListDeque other) {
        size = 0;
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (int i = 0; i < other.size(); i++) {
            this.addLast((Item) other.get(i + 1));
        }
    }
    @Override
    /** Adds an item of type Item to the front of the deque.*/
    public void addFirst(Item item) {
        size = size + 1;
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        if (size == 1) {
            sentinel.prev = sentinel.next;
        }
        sentinel.next.next.prev = sentinel.next;
    }
    @Override
    /** Adds an item of type Item to the back of the deque.*/
    public void addLast(Item item) {
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
    @Override
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
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println("\n");
    }

    /**Removes and returns the item at the front of the deque.
    * If no such item exists, returns null.*/
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Item front = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return front;
    }
    @Override
    /** Removes and returns the item at the back of the deque.
    * If no such item exists, returns null.*/
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Item back = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return back;
    }
    @Override
    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    * If no such item exists, returns null. Must not alter the deque!*/
    public Item get(int index) {
        StuffNode p = sentinel;
        if (index > size) {
            return null;
        }
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        //System.out.println(p.item);
        return p.item;
    }



    /** Same as get, but uses recursion.*/
    public Item getRecursive(int index) {
        if (index > size) {
            return null;
        }
        return sentinel.next.get(index);
    }

    @Override
    /** Inserts item into given position.*/
    public void insert(Item item, int position) {
        if (sentinel.next == null || position == 0) {
            addFirst(item);
            return;
        }

        StuffNode currentNode = sentinel.next.next;
        while (position > 1 && currentNode.next != null) {
            position -= 1;
            currentNode = currentNode.next;
        }

        StuffNode newNode = new StuffNode(currentNode, item, currentNode.next);
        currentNode.next = newNode;
    }

}


