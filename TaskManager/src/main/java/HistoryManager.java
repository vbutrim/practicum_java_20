import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author butrim
 */
public interface HistoryManager {

    void add(Task task);

    void remove(int id);

    List<Integer> getHistoryIds();


    /**
     * not so optimal, because in the average it works in O(1)
     */
    final class BasedOnList implements HistoryManager {
        private final List<Integer> historyTaskIds = new ArrayList<>();

        public BasedOnList() {
        }

        @Override
        public void add(Task task) {
            // check on space
            this.historyTaskIds.add(task.getId());
        }

        /**
         * Predicate<Integer> = Function<Integer, Boolean>
         */
        @Override
        public void remove(int id) {
            historyTaskIds.removeIf(element -> element == id);
        }

        @Override
        public List<Integer> getHistoryIds() {
            return Collections.unmodifiableList(historyTaskIds);
        }
    }

    /**
     * option 1: implements everything (both CustomLinkedList and HashMap) inside BasedOnCustomLinkedListHistoryManager
     * option 2: introduce two independent classes CustomLinkedList and HashMap and use them inside BasedOnCustomLinkedListHistoryManager
     */
    final class BasedOnCustomLinkedListHistoryManager implements HistoryManager {
        /**
         * option 1: head and tail links
         */
        private final LinkedList<Integer> list = new LinkedList<>();
        private final HashMap<Integer, Node<Integer>> map = new HashMap<>();

        public BasedOnCustomLinkedListHistoryManager() {
        }

        @Override
        public void add(Task task) {
            Node<Integer> node = list.linkLast(task.getId());
            map.put(task.getId(), node);
        }

        /**
         * O(1)
         * 1: get node from map
         * 2: remove node from list
         */
        @Override
        public void remove(int id) {
            // todo
        }

        @Override
        public List<Integer> getHistoryIds() {
            return list.getAll();
        }
    }

    final class LinkedList<T> {
        private final Node<T> head;
        private final Node<T> tail;

        /**
         * todo: don't use hack with mocked nodes in your code, do it with nulls
         */
        private LinkedList() {
            this.head = new Node<>(null);
            this.tail = new Node<>(null);
            head.next = tail;
            tail.prev = head;
        }

        /**
         * add element to the tail
         * option 1: node?
         * option 2: element (IB votes for it)
         */
        private Node<T> linkLast(T elem) {
            Node<T> newNode = new Node<>(elem);

            tail.prev.next = newNode;
            newNode.prev = tail.prev;

            return newNode;
        }

        private List<T> getAll() {
/*            Node currentElement = head.next;

            while (currentElement != tail) {
                currentElement = currentElement.next;
            }*/

            List<T> resulted = new java.util.LinkedList<>();

            for (Node<T> currentElement = head.next;
                 currentElement != tail;
                 currentElement = currentElement.next)
            {
                resulted.add(currentElement.elem);
            }

            return Collections.unmodifiableList(resulted);
        }
    }

    final class Node<T> {
        private Node<T> prev;
        private Node<T> next;

        private final T elem;

        Node(T elem) {
            this.prev = null;
            this.next = null;
            this.elem = elem;
        }
    }
}
