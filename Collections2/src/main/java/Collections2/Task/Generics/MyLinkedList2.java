package Collections2.Task.Generics;

import java.util.Objects;

public class MyLinkedList2<T> implements MyQueue<T>, MyList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    public MyLinkedList2() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean offer(T object) {
        add(object);
        return true;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T data = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;

        return data;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : head.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T object) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(object, current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void add(T object) {
        Node<T> newNode = new Node<>(object);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T object) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == size) {
            add(object);
        } else {
            Node<T> newNode = new Node<>(object);
            Node<T> current = getNode(index);

            newNode.prev = current.prev;
            newNode.next = current;

            if (current.prev != null) {
                current.prev.next = newNode;
            } else {
                head = newNode;
            }

            current.prev = newNode;
            size++;
        }
    }

    @Override
    public boolean remove(T object) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(object, current.data)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }

                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public int indexOf(T object) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (Objects.equals(object, current.data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T object) {
        Node<T> current = tail;
        int index = size - 1;
        while (current != null) {
            if (Objects.equals(object, current.data)) {
                return index;
            }
            current = current.prev;
            index--;
        }
        return -1;
    }

    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    public String getAllElements() {
        StringBuilder result = new StringBuilder();
        Node<T> current = head;

        while (current != null) {
            result.append(current.data).append(", ");
            current = current.next;
        }
        if (isEmpty()) return "[]";
        result.setLength(result.length() - 2);
        return "[" + result + "]";
    }
}



