package Collections2.Task;

public interface MyMap<K, V> {
    int size();

    boolean isEmpty();

    void put(String key, Integer value);

    boolean remove(String key);

    void clear();

    Integer get(String key);

    String[] keyArray();

    Integer[] valueArray();
}
