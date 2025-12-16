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
        boolean added = false;
        if (searchByKey(item.getKey()) == null) {
            String key = item.getKey();
            int index = hashFunction(key);
            added = table[index].AddNode(item);
        }
        return added;
    }

    public T searchByKey(String key) {
        int index = hashFunction(key);
        SingleLinkedList<T> bucket = table[index];
        Node<T> current = bucket.head;
        while (current != null) {
            if (current.data.getKey().equals(key)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public boolean deleteByKey(String key) {
        int index = hashFunction(key);
        SingleLinkedList<T> bucket = table[index];
        Node<T> current = bucket.head;
        while (current != null) {
            if (current.data.getKey().equals(key)) {
                return bucket.DeleteNode(current.data);
            }
            current = current.next;
        }
        return false;
    }

    public void printHashTable() {
        for (int i = 0; i < size; i++) {
            SingleLinkedList<T> bucket = table[i];
            if (bucket.head != null) {
                Node<T> current = bucket.head;
                while (current != null) {
                    T temp = searchByKey(current.data.getKey());
                    System.out.println(temp);
                    current = current.next;
                }
            }
        }
    }

     public SingleLinkedList<T>[] getTable() {
        return table;
    }
}
