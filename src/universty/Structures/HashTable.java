package universty.Structures;

import universty.interfaces.Hashable;

public class HashTable<T extends Hashable> {
    SingleLinkedList<T>[] table;
    int size;

    public HashTable() {
        size = 100;
        table = new SingleLinkedList[size];
        for (int i = 0; i < size; i++) { // لو هي قبل istantiate بتبقى null غير الكود ده
            table[i] = new SingleLinkedList<T>();
        }
    }

    private int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash += key.charAt(i);
        }
        return hash % size;
    }

    public boolean insert(T item) {
        String key = item.GetKey();
        int index = hashFunction(key);
        boolean added = table[index].AddNode(item);
        return added;
    }

    public Node<T> SearchByKey(String key) {
        int index = hashFunction(key);
        SingleLinkedList<T> bucket = table[index];
        Node<T> current = bucket.head;
        while (current != null) {
            if (current.data.GetKey().equals(key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean DeleteByKey(String key) {
        int index = hashFunction(key);
        SingleLinkedList<T> bucket = table[index];
        Node<T> current = bucket.head;
        while (current != null) {
            if (current.data.GetKey().equals(key)) {
                return bucket.DeleteNode(current.data);
            }
            current = current.next;
        }
        return false;
    }
}
