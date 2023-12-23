package Collections2.Task.Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MyHashMap2<K, V> implements MyMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private Entry<K, V>[] table;
    private int size;

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap2() {
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
    public void put(K key, V value) {
        ensureCapacity();

        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
            entry = entry.next;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
    }

    @Override
    public boolean remove(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];
        Entry<K, V> prev = null;

        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return true;
            }
            prev = entry;
            entry = entry.next;
        }

        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> entry = table[index];

        while (entry != null) {
            if (Objects.equals(entry.key, key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    @Override
    public K[] keyArray() {
        List<K> keys = new ArrayList<>();

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                if (entry.key != null) {
                    keys.add(entry.key);
                }
                entry = entry.next;
            }
        }

        return keys.toArray((K[]) new Object[0]);
    }

    @Override
    public V[] valueArray() {
        List<V> values = new ArrayList<>();

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                if (entry.key != null) {
                    values.add(entry.value);
                }
                entry = entry.next;
            }
        }

        return values.toArray((V[]) new Object[0]);
    }

    private void ensureCapacity() {
        if (size >= LOAD_FACTOR * table.length) {
            resizeTable();
        }
    }

    private void resizeTable() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = new Entry[newCapacity];

        for (Entry<K, V> entry : table) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int index = getIndex(entry.key, newCapacity);
                entry.next = newTable[index];
                newTable[index] = entry;
                entry = next;
            }
        }

        table = newTable;
    }

    private int getIndex(K key) {
        return getIndex(key, table.length);
    }

    private int getIndex(K key, int capacity) {
        return key == null ? 0 : Math.abs(key.hashCode() % capacity);
    }

    public List<String> getAllValuesKeys() {
        List<String> keysWithValues = new ArrayList<>();

        for (Entry<K, V> entry : table) {
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

