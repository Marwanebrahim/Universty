package universty.Structures;

public class Node<T> {

    T data;
    Node<T> next;

    public Node(T x) {
        this.data = x;
        this.next = null;
    }
}
