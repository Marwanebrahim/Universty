package universty.Structures;

public class SingleLinkedList<T> {
    public Node<T> head;
    public Node<T> tail;
    int noOfNodes;

    public SingleLinkedList() {
        head = null;
        tail = null;
        noOfNodes = 0;
    }

    public boolean AddNode(T t) {
        boolean added = false;
        if (t == null)
            return added;
        Node<T> nd = new Node<T>(t);
        if (nd != null) {
            if (head == null) { // empty list
                head = nd;
                tail = nd;
            } else { // there is list exist
                tail.next = nd;
                tail = nd;
            }
            added = true;
            noOfNodes++;
        }
        return added;
    }

    public boolean DeleteNode(T t) {
        boolean deleted = false;
        if (head == null)
            return deleted;

        if (head.data.equals(t)) {
            head = head.next;

            if (head == null) {
                tail = null;
            }
            noOfNodes--;
            deleted = true;
            return deleted;
        }

        Node<T> temp = head;
        while (temp.next != null && !temp.next.data.equals(t)) {
            temp = temp.next;
        }

        if (temp.next == null)
            return deleted; // مش موجود

        // حذف العنصر
        temp.next = temp.next.next;

        // لو آخر عنصر اتشال
        if (temp.next == null)
            tail = temp;
        deleted = true;
        noOfNodes--;
        return deleted;
    }

    public boolean DeleteLastNode() {
        boolean deleted = false;
        if (head == null)
            return deleted;

        Node<T> temp = head;
        if (head == tail) {
            head = null;
            tail = null;
            noOfNodes--;
            deleted = true;
            return deleted;
        } else {
            while (temp.next != tail) {
                temp = temp.next;
            }
        }
        temp.next = null;
        noOfNodes--;
        tail = temp;
        deleted = true;
        return deleted;
    }

    public Node<T> Search(T t) {
        Node<T> temp = head;

        while (temp != null) {
            if (temp.data.equals(t))
                return temp;

            temp = temp.next;
        }

        return null;
    }

    public void PrintList() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public boolean IsEmpty() {
        return head == null;
    }

    public int getNoOfNodes() {
        return noOfNodes;
    }

}
