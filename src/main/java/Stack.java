//이러한 구조는 보통 어디서 쓰이냐면 '뒤로 가기', '실행 취소(undo)', 그리고 컴퓨터 구조에서 'stack memory


import Interface_form.StackInterface;

import java.util.Arrays;
import java.util.EmptyStackException;

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

        // 만약 삭제할 요소가 없다면 Stack이 비었다는 의미이므로 예외 발생시키기
        if(size == 0) {
            throw new EmptyStackException();
        }

        @SuppressWarnings("unchecked")
        E obj = (E) array[size - 1];
        /*
        * @SuppressWarnings("unchecked")을 붙이지 않으면 type safe(타입 안정성)에 대해 경고를 보낸다.
        * 반환되는 것을 보면 E 타입으로 캐스팅을 하고 있고 그 대상이 되는 것은 Object[] 배열의 Object 데이터다.
        * 즉, Object -> E 타입으로 변환을 하는 것인데 이 과정에서 변환할 수 없는 타입을 가능성이 있다는 경고로 메소드 옆에 경고표시가 뜨는데,
        * 우리가 push하여 받아들이는 데이터 타입은 유일하게 E 타입만 존재한다.
        * 그렇기 때문에 형 안정성이 보장된다.
        * 한마디로 ClassCastException이 뜨지 않으니 이 경고들을 무시하겠다는 것이
        * @SuppressWarnings("unchecked") 이다.
        * 물론 절대 남발하면 안되고, 형 변환시 예외 가능성이 없을 확실한 경우에 최소한의 범위에서 써주는 것이 좋다.
        * 그렇지 않으면 중요한 경고 메세지를 놓칠 수도 있기 때문이다.
        * */

        array[size - 1] = null; // 요소 삭제
        size--;
        resize(); // 용적 재할당

        return obj;
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

