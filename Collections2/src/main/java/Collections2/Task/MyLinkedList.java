package Collections2.Task;

import org.w3c.dom.Node;

import java.util.Objects;

public class MyLinkedList implements MyList, MyQueue{

    private Node head; // Head of our linked list to maintain it
    private Node tail; // End of our linked list to maintain it
    private int size; // LinkedList size

    private static class Node { // Class Node to store data and links to other objects
        Integer data;
        Node next;
        Node prev;

        Node(Integer data) {
            this.data = data;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean offer(Integer object) {
        add(object);
        return true;
    }

    @Override
    public Integer poll() { // If collection is empty - returns null | else - removes and returns 1 element of collection
        if (isEmpty()) {
            return null;
        }
        Integer data = head.data; //Reassign head (first) element data because we are getting it
        head = head.next;   // Reassign head as a next element because it first now
        if (head != null) { // If this head is not null, previous element for head - is null
            head.prev = null;
        }
        else {
            tail = null; // If we had only one element in a list
        }
        size--;

        return data;
    }

    @Override
    public Integer peek() {
        return isEmpty() ? null : head.data; // If we have data returns head.data (first element) | else - null
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
    public boolean contains(Integer object) {
        Node current = head;
        while (current != null) { // Going throw all list and comparing elements to see if we have such element in our collection
            if (Objects.equals(object, current.data)) {
                return true;
            }
            current = current.next; // Next element
        }
        return false;
    }


    @Override
    public void add(Integer object) {
        Node newNode = new Node(object); // Creating a node, putting object to initialize in node constructor
        if (tail == null) { // For
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // End of a list now is this new node
            newNode.prev = tail; // For this node previous is end of the list before adding element
            tail = newNode;
        }
        size++; // Size increased
    }

    @Override
    public void add(int index, Integer object) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        if (index == size) {
            add(object);
        }
        else {
            Node newNode = new Node(object); //New node that we wish to add
            Node current = getNode(index); // current node on this index

            newNode.prev = current.prev; // Previous element for newNode is previous for current element
            newNode.next = current; // But next element for newNode is current element (we moved all elements to the right side)

            if (current.prev != null) { // If current node previous element != null (if current is not first element)
                current.prev.next = newNode; // Reassign previous for current to newNode (for 1 next element will be newNode (2))
            }
            else {
                head = newNode; // Else - newNode will be first element
            }

            current.prev = newNode; // Previous for current el. will be this just added element
            size++;
        }
    }

    @Override
    public boolean remove(Integer object) { //Comparing all values with object value and remove it
        Node current = head;
        while (current != null) {
            if (Objects.equals(object, current.data)) { // If value of current object equals required
                if (current.prev != null) { // If current element is not first element
                    current.prev.next = current.next; // For prev element next will be current.next element
                } else {
                    head = current.next; // Else next element for current will be first
                }

                if (current.next != null) { // If current.next is not last element
                    current.next.prev = current.prev; // For next element - previous will be current.prev
                } else {
                    tail = current.prev; // current.prev - is last
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
    public Integer get(int index) {
        return getNode(index).data;
    }

    @Override
    public int indexOf(Integer object) { // Goes throw all elements, increases and returns index of required element
        Node current = head;
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
    public int lastIndexOf(Integer object) { // Goes throw all elements, increases and returns index of last required element
        Node current = tail;
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

    private Node getNode(int index) { // Goes throw collection and returns required element
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    public String getAllElements() {
        String result = "";
        Node current = head;

        while (current != null) {
            result+= current.data + ", ";
            current = current.next;
        }
        if (isEmpty()) return "[]";
        result = result.substring(0, result.length() - 2);
        return "[" + result + "]";
    }
}
