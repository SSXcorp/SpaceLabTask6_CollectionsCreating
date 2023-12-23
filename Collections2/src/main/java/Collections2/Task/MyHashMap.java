package Collections2.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyHashMap implements MyMap<String, Integer> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry[] table; // Hashmap background is entry
    private int size; //Size

    private static class Entry { //Entry inner class
        String key;
        Integer value;
        Entry next; //Next value

        Entry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() { //Creating with default capacity
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
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
    public void put(String key, Integer value) {
        ensureCapacity();

        int index = getIndex(key); // returns index to put
        Entry entry = table[index]; //to put value in a right "bucket"

        while (entry != null) { // Going throw list of entries
            if (Objects.equals(entry.key, key)) { //if we have entry with such key - we just define new value for it
                entry.value = value;
                return; // To break the loop
            }
            entry = entry.next;
        }

        Entry newEntry = new Entry(key, value); // Or if we don't have such key we are creating a new Entry
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
    }

    @Override
    public boolean remove(String key) { // remove element using individual key
        int index = getIndex(key);
        Entry entry = table[index];
        Entry prev = null;

        while (entry != null) {// goes throw list of entries to find the same key
            if (Objects.equals(entry.key, key)) {
                if (prev == null) {
                    table[index] = entry.next; //if it was first element - now element 2 is first
                } else {
                    prev.next = entry.next; // links next element for previous ot next element for this entry
                }
                size--;
                return true;
            }
            prev = entry; // to call next entry
            entry = entry.next; // to call next entry
        }

        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public Integer get(String key) {
        int index = getIndex(key); // to get access to the element using key
        Entry entry = table[index]; //gets the entries up to current index

        while (entry != null) {// goes throw entry to find key
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    @Override
    public String[] keyArray() {
        List<String> keys = new ArrayList<>();

        for (Entry entry : table) {
            while (entry != null) {
                if (entry.key != null) {
                    keys.add(entry.key);
                }
                entry = entry.next;
            }
        }

        return keys.toArray(new String[0]);
    }

    @Override
    public Integer[] valueArray() {
        List<Integer> values = new ArrayList<>();

        for (Entry entry : table) {
            while (entry != null) {
                if (entry.key != null) {
                    values.add(entry.value);
                }
                entry = entry.next;
            }
        }

        return values.toArray(new Integer[0]);
    }

    private void ensureCapacity() { //Method to calc capacity
        if (size >= LOAD_FACTOR * table.length) {
            resizeTable();
        }
    }

    private void resizeTable() { //to resize table
        int newCapacity = table.length * 2; // New capacity = old*2
        Entry[] newTable = new Entry[newCapacity]; // Creating new Entry with new capacity

        for (Entry entry : table) { // loop to put all old entry values to the new Entry with new size
            while (entry != null) {
                Entry next = entry.next; //to get next entry
                int index = getIndex(entry.key, newCapacity); //to get index "bucket" for current entry in new table
                entry.next = newTable[index]; // Link the next entry to the existing linked list in the new bucket
                newTable[index] = entry; // to store this entry in newTable on this index
                entry = next; // to put inside entry new value (for loop)
            }
        }

        table = newTable;
    }

    private int getIndex(String key) { // Returns index
        return getIndex(key, table.length);
    }

    private int getIndex(String key, int capacity) { // Calculates new index in accordance with a capacity if key != null
        return key == null ? 0 : Math.abs(key.hashCode() % capacity);
    }

    public List<String> getAllValuesKeys() {
        List<String> keysWithValues = new ArrayList<>();

        for (Entry entry : table) {
            while (entry != null) {
                if (entry.key != null) {
                    keysWithValues.add(entry.key + ": " + entry.value);
                }
                entry = entry.next;
            }
        }

        return keysWithValues;
    }
}

