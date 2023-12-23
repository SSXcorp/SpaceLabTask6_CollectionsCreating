package Collections2.Task.Generics;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList2<T> implements MyList<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked") // no notifications about typeCasting, problems that can occur with old legacy code
    public MyArrayList2() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(T object) {
        ensureCapacity();
        array[size++] = object;
    }

    @Override
    public void add(int index, T object) {
        if (index < 0 || index > size) {
            add(object);
        } else {
            ensureCapacity();
            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = object;
            size++;
        }
    }

    @Override
    public boolean remove(T object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, array[i])) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[--size] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return array[index];
    }

    @Override
    public int indexOf(T object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T object) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(object, array[i])) {
                return i;
            }
        }
        return -1;
    }

    public int length() {
        return size;
    }

    public String getAllElements() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(array[i]).append(", ");
        }
        if (result.length() > 0) {
            return "[ " + result.substring(0, result.length() - 2) + " ]";
        }
        return "[]";
    }

}

