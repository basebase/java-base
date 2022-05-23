package generics;

/**
 * @Author xiaomoyu
 * @Date: 2022/5/23 11:19:46
 * @Description:
 */
public class LinkedStack<T> {

    private static class Node<N> {
        N node;
        Node<N> next;

        public Node() {
            this.node = null;
            this.next = null;
        }

        public Node(N node, Node<N> next) {
            this.node = node;
            this.next = next;
        }

        boolean end() {
            return node == null && next == null;
        }
    }

    private Node<T> top = new Node<>();

    public void push(T t) {
        top = new Node<>(t, top);
    }

    public T pop() {
        T result = top.node;
        if (!top.end()) {
            top = top.next;
        }
        return result;
    }

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("Hi");
        System.out.println(stack.pop());
    }
}
