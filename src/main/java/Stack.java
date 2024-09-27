//이러한 구조는 보통 어디서 쓰이냐면 '뒤로 가기', '실행 취소(undo)', 그리고 컴퓨터 구조에서 'stack memory


import Interface_form.StackInterface;

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


}

