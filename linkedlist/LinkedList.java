public class LinkedList<T> {
    // inner class
    private class Node<T> {
        public T data;
        public Node next;
        public Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    // private fields
    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(T n) {
        add(n, size());
    }

    public void add(T n, int index) {
        if (index == 0) {
            if (size() == 0) {
                head = new Node(n);
                tail = head;
            } else {
                Node temp = new Node(n);
                temp.next = head;
                head = temp;
            }
        } else if (index == size()) {
            Node node = new Node(n);
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        } else {
            Node curr = head;
            int i = 0;
            while (i++ < index) {
                curr = curr.next;
            }
            Node node = new Node(n);
            node.next = curr;
            node.prev = curr.prev;
            curr.prev.next = node;
            curr.prev = node;
        }
        size++;
    }

    public Node remove(int index) {
        int storedSize = size;
        size--;
        Node curr = head;
        if (index == 0) {
            head = head.next;
            return curr;
        } else if (index == storedSize) {
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
            return tail;
        } else {
            int i = 0;
            while (i++ < index) {
                curr = curr.next;
            }
            Node node = curr;
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            return node;

        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        String s = "[";
        Node curr = head;
        while (curr.next != null) {
            s += curr.data + ", ";
            curr = curr.next;
        }

        return s + curr.data + "]";
    }

    public Node remove() {
        return remove(size);
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(8, 1);
        list.remove(1);
        list.remove(1);
        System.out.println(list.toString());
    }
}
