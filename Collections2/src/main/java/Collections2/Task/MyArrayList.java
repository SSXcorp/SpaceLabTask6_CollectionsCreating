package Collections2.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList implements MyList {
    private static final int INITIAL_CAPACITY = 10;
    public Integer[] array;
    private int size;

    public MyArrayList() {
        array = new Integer[INITIAL_CAPACITY];
        size = 0;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2); //Making copy of current array + increasing the size x2
        }
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Integer object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(Integer object) {
        ensureCapacity();
        array[size++] = object;
    }

    @Override
    public void add(int index, Integer object) {
        if (index < 0 || index > size) {
            add(object);
        }
        else {
            ensureCapacity();
            for (int i = size; i > index; i--) { // Move elements right from the end of array
                array[i] = array[i - 1];
            }

            array[index] = object;
            size++;
        }
    }

    @Override
    public boolean remove(Integer object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, array[i])) {
                // Сдвигаем элементы влево, начиная с найденного индекса
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[--size] = null; // Уменьшаем размер и обнуляем последний элемент
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
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return array[index];
    }

    @Override
    public int indexOf(Integer object) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(object, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer object) {
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
        String result = "";
        for (int i = 0; i < size; i++) {
            result += array[i] + ", ";
        }
        if (result.contains(",")){
            return "[ " + result.substring(0, result.length() - 2) + " ]";
        }
        return "[]";
    }

//    @Override
//    public Integer[] toArray() {
//        return Arrays.copyOf(array, size);
//    }


}
