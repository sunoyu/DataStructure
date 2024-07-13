import Interface_form.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E>, Cloneable
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

    @SuppressWarnings("unchecked")  // 리턴 타입에 대한 Unchecked cast: 'java.lang.Object' to 'E' 경고가 사라짐
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // Object타입에서 E타입으로 캐스팅 후 반환
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            // 해당 위치의 요소를 교체
            array[index] = value;
        }
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }


    public int lastIndexOf(Object value) {
        for(int i = size - 1; i >= 0; i--) {
            if(array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public boolean contains(Object value) {
        // 0 이상이면 요소가 존재한다는 뜻
        if(indexOf(value) >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        E element = (E) array[index]; // 삭제될 요소를 반환하기 위해 임시로 깔아둠
        array[index] = null;

        // 삭제된 요소에 의해 뒤에있는 모든 요소를 앞으로 한칸씩
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }
        size--;
        resize();
        return element;
    }

    @Override
    public boolean remove(Object value) {
        // 삭제하고자 하는 요소의 인덱스 찾기
        int index = indexOf(value);

        // -1이라면 array에 요소가 없다는 의미이므로 false 반환
        if (index == -1) {
            return false;
        }

        // index 위치에 있는 요소를 삭제
        remove(index);
        return true;
    }



    @Override
    public int size() {
        return size; // 요소 갯수 반환
    }

    @Override
    public boolean isEmpty() {
        return size == 0; // 요소가 0개일 경우 비어있다는 의미
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }


    // 부록 메소드
    @Override
    public Object clone() throws CloneNotSupportedException {

        // 새로운 객체 생성
        ArrayList<?> cloneList = (ArrayList<?>)super.clone();

        // 새로운 객체의 배열도 생성해주어야 함 (객체는 얕은복사가 되기 때문)
        cloneList.array = new Object[size];

        // 배열의 값을 복사함
        System.arraycopy(array, 0, cloneList.array, 0, size);

        return cloneList;
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // copyOf(원본 배열, 복사할 길이, Class<? extends T[]> 타입)
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }
        // 원본배열, 원본배열 시작위치, 복사할 배열, 복사할배열 시작위치, 복사할 요소 수
        System.arraycopy(array, 0, a, 0, size);
        return a;
    }

}
