import Interface_form.List;
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

    private void addFirst(E value) {
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









}
