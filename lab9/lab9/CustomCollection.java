package lab9;

import java.util.Iterator;

public class CustomCollection<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    public CustomCollection(int capacity) {
        elements = (T[]) new Object[capacity];
        size = 0;
    }

    public void add(T element) {
        elements[size++] = element;
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public T next() {
            return elements[currentIndex++];
        }
    }

    public static void main(String[] args) {
        CustomCollection<String> collection = new CustomCollection<>(5);
        collection.add("Hello");
        collection.add("World");
        collection.add("!");

        for (String element : collection) {
            System.out.println(element);
        }
    }
}
