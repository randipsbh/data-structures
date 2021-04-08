import java.util.Arrays;

public class MaxHeap {
    private Integer[] heap;
    private int size;
    private int capacity;

    public MaxHeap() {
        this.size = 0;
        this.capacity = 10;
        this.heap = new Integer[capacity];
    }

    private void ensureCapacity() {
        if (size == capacity) {
            heap = Arrays.copyOf(heap, 2 * capacity);
            capacity = 2 * capacity;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public void insert(int data) {
        // ensureCapacity();
        heap[size++] = data;
        heapifyUp(size - 1);
    }

    public Integer delete(int i) {
        Integer data = heap[i];
        heap[i] = heap[size - 1];
        size--;
        heapifyDown(i);
        return data;
    }

    private void heapifyDown(int i) {
        int child;
        int temp = heap[i];
        while (getLeftChildIndex(i) < size) {
            child = maxChild(i);
            if (temp < heap[child]) {
                heap[i] = heap[child];
            } else {
                break;
            }
            i = child;
        }
        heap[i] = temp;
    }

    private int maxChild(int i) {
        int leftChild = getLeftChildIndex(i);
        int rightChild = getRightChildIndex(i);
        return heap[leftChild] > heap[rightChild] ? leftChild : rightChild;
    }

    private void heapifyUp(int i) {
        Integer temp = heap[i];
        while (i > 0 && temp > heap[getParentIndex(i)]) {
            heap[i] = heap[getParentIndex(i)];
            i = getParentIndex(i);
        }
        heap[i] = temp;
    }

    public void printHeap() {
        System.out.print("Heap = ");
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.insert(10);
        heap.insert(4);
        heap.insert(9);
        heap.insert(1);
        heap.insert(7);
        heap.insert(5);
        heap.insert(3);
        heap.printHeap();

        for (int i = 0; i < 7; i++) {
            System.out.print(heap.delete(0) + " ");
        }
        System.out.println();
    }
}
