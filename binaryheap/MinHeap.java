import java.util.Arrays;
import java.util.Random;

public class MinHeap {
    private Integer[] heap;
    private int size;
    private int capacity;

    public MinHeap() {
        this.size = 0;
        capacity = 10;
        heap = new Integer[capacity];
    }

    public static int parent(int i) {
        return (i - 1) / 2;
    }

    public static int leftChild(int i) {
        return 2 * i + 1;
    }

    public static int rightChild(int i) {
        return 2 * i + 2;
    }

    public boolean hasLeftChildren(int i) {
        return leftChild(i) < size;
    }

    public boolean hasRightChildren(int i) {
        return rightChild(i) < size;
    }

    public boolean hasParent(int i) {
        return parent(i) >= 0;
    }

    public void insert(Integer data) {
        ensureCapacity();
        heap[size] = data;
        size++;
        heapifyUp();
    }

    public int deleteMax() {
        Integer temp = heap[0];
        size--;
        heap[0] = heap[size];
        heapifyDown();
        return temp;
    }

    public void heapifyDown() {
        int index = 0;

        while (hasLeftChildren(index)) {
            int smallestChildIndex = leftChild(index);

            if (hasRightChildren(index) && heap[rightChild(index)] < heap[leftChild(index)]) {
                smallestChildIndex = rightChild(index);
            }

            if (heap[index] < heap[smallestChildIndex]) {
                break;
            } else {
                swap(index, smallestChildIndex);
            }
            index = smallestChildIndex;
        }
    }

    private void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && heap[parent(index)] > heap[index]) {
            swap(parent(index), index);
            index = parent(index);
        }
    }

    private void swap(int parentIndex, int i) {
        Integer temp = heap[parentIndex];
        heap[parentIndex] = heap[i];
        heap[i] = temp;
    }

    public void ensureCapacity() {
        if (size == capacity) {
            heap = Arrays.copyOf(heap, 2 * capacity);
            capacity = 2 * capacity;
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap();
        Integer[] arr = new Integer[10];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(25) + 1;
            heap.insert(arr[i]);
            System.out.printf("insert %02d: ", arr[i].intValue());
            heap.printHeap();
        }
        System.out.printf("complete : ");
        heap.printHeap();

        Integer[] arrDel = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            int n = heap.deleteMax();

            arrDel[i] = n;

            System.out.printf("remove %02d: ", n);
            heap.printHeap();
        }
        System.out.println("arrDel = ");
        System.out.print(Arrays.toString(arrDel));
    }

}