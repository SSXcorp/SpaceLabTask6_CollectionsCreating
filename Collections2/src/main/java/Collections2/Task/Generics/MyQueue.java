package Collections2.Task.Generics;

public interface MyQueue<T> {

    boolean offer(T object);

    T poll();

    T peek();

    int size();
}


