import Interface_form.List;

import java.util.NoSuchElementException;

// https://st-lab.tistory.com/167
public class SinglyLinkedList<E> implements List<E>
{
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> x = head;

        for (int i = 0; i < index; i++) {
            x = x.next;   // 헤드부터 index까지 next를 찾으면서 Index칸에 있는 노드를 리턴
        }
        return x;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<>(value);
        newNode.next = head;
        head = newNode;
        size++;


        /**
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
         * 마지막 노드다. 즉 tail = head 다.
         */
        if (newNode.next == null) {
            tail = head;
        }
    }


    @Override
    public boolean add(E value) {
        return false;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<>(value);
        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }


    @Override
    public void add(int index, E value) {
        // index 가 size보다 큰경우 -> 예외던지기
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // index와 size가 같은경우 -> addLast
        if (index == size) {
            addLast(value);
            return;
        }

        Node<E> prevNode = search(index - 1);

        Node<E> nextNode = search(index + 1);

        Node<E> newNode = new Node<>(value);

        prevNode = null;
        prevNode.next = newNode;
        newNod.next = nextNode;
        size++;
    }


    /*
    * 가장 앞의 요소(head)를 삭제 - remove()
    * 특정 index의 요소를 삭제 - remove(int index)
    * 특정 요소를 삭제 - remove(Object value)
    * */

    public E remove() {
        Node<E> headNode = head;  // 삭제된 노드를 반환하기위해
        if (headNode == null) {
            throw new NoSuchElementException();
        }

        E element = headNode.data;


        Node<E> nextNode = head.next;

        head.data = null;
        head.next = null;

        head = nextNode;
        size--;

        if (size == 0) {

            tail = null;
        }
        return element;
    }
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 1) {
            return remove();
        }

        // search를 계속 쓰면 시작 복잡도가 올라감.
        Node<E> prevNode = search(index - 1);
        Node<E> removeNode = prevNode.next;
        Node<E> nextNode = removeNode.next;


        E element = removeNode.data;

        prevNode.next = nextNode;

        if (prevNode.next == null) {
            tail = prevNode;

        }

        removeNode.data = null;
        removeNode.next = null;
        size--;

        return element;

    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        Node<E> x = head;

        for (; x != null; x = x.next) {
            if (x.data.equals(value)) {
                break;
            }
            prevNode = x;
        }

        // 일치하는 노드가 없다면 false
        if (x == null) {
            return false;
        }

        if (x.equals(head)) {
            remove();
            return true;
        } else {
            prevNode.next = x.next;

            if (prevNode.next == null) {
                tail = prevNode;
            }
            x.data = null;
            x.next = null;
            size--;
            return true;
        }
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
}
