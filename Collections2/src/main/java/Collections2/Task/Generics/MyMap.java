package Collections2.Task.Generics;

public interface MyMap<K, V> {
    int size();

    boolean isEmpty();

    void put(K key, V value);

    boolean remove(K key);

    void clear();

    V get(K key);

    K[] keyArray();

    V[] valueArray();
}
