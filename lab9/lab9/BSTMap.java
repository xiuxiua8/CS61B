package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Set<K> keyset = new HashSet<>();

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** return node */
    private Node go(Node node, K key) {
        if (key.compareTo(node.key) < 0) {
            return node.left;
        } else if (key.compareTo(node.key) > 0){
            return node.right;
        } else {
            return null;
        }
    }
    /** go to the right node, then return that node
     * */
    private Node gotothebottom (Node node, K key) {
        Node ndptr = node;
        while (go(ndptr, key) != null) {
            ndptr = gotothebottom(go(ndptr, key), key);
        }
        //System.out.println("1");
        return ndptr;
    }

    private Node gotoparentnode (Node node, Node cnode, K key) {
        Node ndptr = node;
        while (go(ndptr, key) != cnode) {
            if (go(ndptr, key) == null) {
                return null;
            }
            ndptr = gotoparentnode(go(ndptr, key), cnode, key);
        }
        //System.out.println("1");
        return ndptr;
    }

    private Node gotoleft (Node node) {
        Node ndptr = node.left;
        if (ndptr == null) {
            return null;
        } else {
            while (ndptr.right != null) {
                ndptr = ndptr.right;
            }
            return ndptr;
        }
    }

    private Node gotoright (Node node) {
        Node ndptr = node.right;
        if (ndptr == null) {
            return null;
        } else {
            while (ndptr.left != null) {
                ndptr = ndptr.left;
            }
            return ndptr;
        }
    }



    private Node find(Node T, K sk) {
        if (T == null) {
            return null;
        }
        if (sk.equals(T.key)) {
            return T;
        } else if (sk.compareTo(T.key) < 0) {
            return find(T.left, sk);
        } else {
            return find(T.right, sk);
        }
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p.key.equals(key)) {
            return p.value;
        } else {
            return null;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        Node node = gotothebottom(root, key);
        //Node node = find(root, key);
        return getHelper(key, node);

    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            p = new Node(key, value);
            return p;
        } else if (key.compareTo(p.key) == 0){
            //Node node = go(p, key);
            p.value = value;
            return p;
        } else {
            Node node = new Node(key, value);
            if (key.compareTo(p.key) < 0) {
                p.left = node;
                return p.left;
            }
            if (key.compareTo(p.key) > 0) {
                p.right = node;
                return p.right;
            }
            return null;
        }
    }


    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (!keySet().contains(key)) {
            keyset.add(key);
        }
        if (root == null) {
            root = putHelper(key, value, root);
            size += 1;
            return;
        }

        Node node = gotothebottom(root, key);
        //Node node = find(root, key);
        putHelper(key, value, node);
        //throw new UnsupportedOperationException();
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keyset;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        int LR = 0;
        if (root == null) {
            return null;
        }
        Node node = gotothebottom(root, key);
        if (!node.key.equals(key)) {
            return null;
        }
        V returnvalue = getHelper(key, node);
        Node Pnode = gotoparentnode(root, node, key);
        if (Pnode == null) {
            //delete root which is a hard thing
            Node lnode = gotoleft(node);
            if (lnode.left != null) {
                Node PLnode = gotoparentnode(root,lnode,lnode.key);
                PLnode.right = lnode.left;
            } else {
                Node PLnode = gotoparentnode(root,lnode,lnode.key);
                PLnode.right = null;
            }
            lnode.right = node.right;
            lnode.left = node.left;
            root = lnode;
            size -= 1;
            keyset.remove(key);
            return returnvalue;
        }



        if (Pnode.right == node) {
            LR = 1;
        }
        if (node.left != null && node.right != null){
            Node lnode = gotoleft(node);
            if (lnode.left != null) {
                Node PLnode = gotoparentnode(root,lnode,lnode.key);
                PLnode.right = lnode.left;
            } else {
                Node PLnode = gotoparentnode(root,lnode,lnode.key);
                if (PLnode != node) {
                    PLnode.right = null;
                }
            }
            lnode.right = node.right;
            if (node.left != lnode) {
                lnode.left = node.left;

            }
            if (LR == 0) { Pnode.left = lnode;
            } else { Pnode.right = lnode;}
            System.out.println("hello");

        } else if (node.left == null && node.right != null) {
            if (LR == 0) { Pnode.left = node.right;
            } else { Pnode.right = node.right;}
        } else if (node.left != null && node.right == null) {
            if (LR == 0) { Pnode.left = node.left;
            } else { Pnode.right = node.left;}
        } else {
            if (LR == 0) { Pnode.left = null;
            } else { Pnode.right = null;}
        }
        size -= 1;
        keyset.remove(key);
        return returnvalue;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key).equals(value)) {
            return remove(key);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        //throw new UnsupportedOperationException();
        return new BSTIterator();
    }


    private class BSTIterator implements Iterator<K> {
        private int currentIndex = 0;
        //private Node[] Queue = new Node[16];

        @Override
        public boolean hasNext() {
            //return root.left == null || root.right == null;
            return false;
        }

        @Override
        public K next() {
            return root.key;
        }

    }

//    public static void main(String[] args) {
//        BSTMap<Integer,String> BStree = new BSTMap<>();
//        BStree.put(1, "Hello");
//        BStree.put(2, "World");
//        BStree.put(3, "!");
//
//        for (Integer element : BStree) {
//            System.out.println(element);
//        }
//    }

    public static void main(String[] args) {
        BSTMap<String, Integer> bstmap = new BSTMap<>();
        bstmap.put("hello", 5);
        System.out.println(bstmap.get("hello"));
        bstmap.put("cat", 10);
        bstmap.put("fish", 22);
        bstmap.put("zebra", 90);
        bstmap.put("apple", 1);
        bstmap.put("batman", 2);
        bstmap.put("catman", 2);
        bstmap.put("datman", 2);
        bstmap.put("eatman", 2);
        bstmap.put("fatman", 2);
        bstmap.remove("cat");
        //System.out.println(bstmap.gotoleft(bstmap.root).key);
        System.out.println(bstmap.get("hello"));
        System.out.println(bstmap.get("hello1"));
    }
}
