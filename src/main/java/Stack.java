//이러한 구조는 보통 어디서 쓰이냐면 '뒤로 가기', '실행 취소(undo)', 그리고 컴퓨터 구조에서 'stack memory


import Interface_form.StackInterface;

import java.util.Arrays;

public class Stack<E> implements StackInterface<E> {
    private static final int DEFAULT_CAPACITY = 10;  // 최소(기본) 용적크기
    private static final Object[] EMPTY_ARRAY = {};

    private Object[] array; // 요소를 담을 배열
    private int size; // 요소 갯수

    // 생성자1 (초기 공간 할당 안함)
    public Stack() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    // 생성자2 (초기 공간 할당)
    public Stack(int capacity){
        this.array = new Object[capacity];
        this.size = 0;
    }

    private void resize() {

        // 빈 배열일 경우 (capacity 가 0)
        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
            return;
        }

        int arrayCapacity = array.length; // 현재 용적 크기

        // 용적이 가득 찰 경우
        if (size == arrayCapacity) {
            int newSize = arrayCapacity * 2;

            // 배열복사
            array = Arrays.copyOf(array, newSize);
            // Arrays.copyOf() 는 첫 번째 파라미터로 '복사할 배열'을 넣어주고, 두 번째 파라미터는 용적의 크기를 넣어주면 된다.
            return;
        }

        // 용적의 절반 미반으로 요소가 차지하고 있을 경우
        if (size == arrayCapacity / 2) {
            int newCapacity = arrayCapacity / 2;

            // 배열 복사
            arrays = Arrays.copyOf(array, Math.max(DEFAULT_CAPACITY, newCapacity));
            return;
        }
    }


    @Override
    public E push(E item) {
        // 용적이 풀로 꽉차있다면 리사이즈 (동적할당)
        if (size == array.length) {
            resize();
        }
        array[size] = item; // 마지막 위치에 요소 추가
        size++; // 사이즈 1증가

        return item;
    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E search(Object value) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean empty() {
        return false;
    }
}

