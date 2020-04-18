import java.util.ArrayDeque;

public class Queue {
    public static void main(String[] args) {


        ArrayDeque<Integer> deque = new ArrayDeque<>();


        deque.offer(12);
        deque.offer(22);
        deque.offer(43);
        deque.offer(38);
        deque.offer(98);
        System.out.println(deque.peek());
        System.out.println(deque.poll());
        System.out.println(deque.poll());

        System.out.println(deque);


    }
}
