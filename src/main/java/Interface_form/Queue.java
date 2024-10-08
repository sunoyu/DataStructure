package Interface_form;

/**
 *
 * 자바 Queue Interface입니다. <br>
 * Queue는 ArrayQueue, LinkedQueue,
 * Deque, PriorityQueue 에 의해 구현됩니다.
 *
 * @author roy
 * @param <E> the type of elements in this Queue
 *
 * @version 1.0
 *
 */


public interface Queue<E> {
    /*
     * 큐의 가장 마지막에 요소를 추가합니다.
     *
     * @param 큐에 추가할 요소
     * @return 큐에 요소가 정상적으로 추가됐을 경우 true반환
     * */
    boolean offer(E e);


    /*
    *
    * 큐의 가장 첫번째 요소를 삭제하고 그 요소를 반환합니다.
     * @return 삭제된 마지막 요소
     * */
    E poll();


    /*
    * 큐의 가장 첫번째 요소를 삭제하지 않고 반환합니다.
     * @return 큐의 첫번째 요소
     * */
    E peek();
}
