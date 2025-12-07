package universty.Structures;

public class Node<T> {

    public T data;
    public Node<T> next;

    public Node(T x) {
        this.data = x;
        this.next = null;
    }
}
