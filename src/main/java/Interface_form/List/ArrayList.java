package Interface_form.List;

import Interface_form.List;

import java.security.PublicKey;
import java.util.Arrays;

public class ArrayList<E> implements List<E>
{
    // 생성자
    private static final int DEFAULT_CAPACITY = 10;  // least capacity size
    private static final Object[] EMPTY_ARRAY = {}; // 빈 배열

    private int size; // 요소 개수

    Object[] array; // 요소를 담을 배열

    // 생성자 1 (초기공간 할당 안함)
    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자 2 (초기 공간 할당 O)
    public ArrayList(int capacity) {
        this.array = new Object[capacity];
        this.size = 0;
    }
    public void resize() {
        int array_capacity = array.length;

        // array 의 용적이 0인 경우
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        // 용적이 꽉 찼을 경우
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;

            // copy
            array = Arrays.copyOf(array, new_capacity);
            return;
        }

        // 용적이 절반 미만으로 요소가 차지하고 있을 경우
        if (size < (array_capacity / 2)) {
            int new_capacity = array_capacity /2;

            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
            return;
        }
    }


    @Override
    public boolean add(E value) {
        return false;
    }

    public void addLast(E value) {
        // 꽉차있는 상태라면 용적 재할당
        if (array.length == size) {
            resize();
        }
        array[size] = value; // 마지막 위치에 요소 추가
        size++; // 리스트의 사이즈 1증가
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) { // inDex가 마지막 위치라면 addLast로
            addLast(value);
        }
        else {
            if (size == array.length) { // 꽉차있다면 용적 재할당
                resize();
            }

            // index 기준 후자에 있는 모든 요소를 한칸씩 뒤로 밀어준다.
            for (int i = size; i > index; i--) {
                array[i] = array[i-1];
            }
            array[index] = value;
            size++;
        }
    }

    public void arrayFirst(E value) {
        add(0, value);
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public boolean remove(Object value) {
        return false;
    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public void set(int index, E value) {

    }

    @Override
    public boolean contains(Object value) {
        return false;
    }

    @Override
    public int indexOf(Object value) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {

    }

    private
}
