import Interface_form.Queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 64;  // 최소 용적크기

    private Object[] array; // 요소를 담을 배열
    private int size = 0;
    private int front;
    private int rear;

    public ArrayQueue() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    public ArrayQueue(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = 0;
    }

    // 동적할당을 위한 Resize
    public void resize(int newCapacity) {
        int arrayCapacity = array.length;
        Object[] newArray = new Object[newCapacity];

        for (int i = 1, j = front + 1; i < size; i++, j++) {
            newArray[i] = array[j];
        }
        this.array = newArray;
        front = 0;
        rear = size;
    }


    @Override
    public boolean offer(E e) {
        // 용적이 가득 찼을 경우
        if ((rear + 1) % array.length == front) {
            resize(array.length * 2);
        }

        rear = (rear + 1) % array.length;
        array[rear] = e;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        front = (front + 1) % array.length; // front를 먼저 옮기고(기존 프론트는 빈값이므로)

        @SuppressWarnings("unchecked")
        E item = (E) array[front];

        array[front] = null;
        size--;

        // resize
        if (array.length > DEFAULT_CAPACITY && array.length / 4 > size) {
            resize(Math.max(array.length / 2, DEFAULT_CAPACITY));
        }

        return item;
    }
    public E remove() {
        E item = poll();
        if (item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        @SuppressWarnings("unchecked")
        E item = (E) array[front+1];
        return item;
    }
    public E element() {
        E item = peek();
        if (item == null) {
            throw new NoSuchElementException();
        }
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(E e){
        for (int i = 0, j = (front + 1) % array.length; i < size; i++, j = j+1 % array.length) {
            if(e.equals(array[j])) return true;
        }
        return false;
    }
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        front = size = rear = 0;
    }
}
