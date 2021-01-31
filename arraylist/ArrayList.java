import java.util.Arrays;

public class ArrayList {
    // constant
    public static final int DEFAULT_CAPACITY = 10;

    // fields
    private int[] list;
    private int size;

    public ArrayList() {
        this.list = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayList(int intialCapacity) {
        this.list = new int[intialCapacity];
    }

    public void add(Integer n) {
        if (size == list.length) {
            ensureCapacity();
        }
        list[size++] = n;
    }

    public void add(int index, Integer n) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        if (size == list.length) {
            System.out.println("in loop");
            ensureCapacity();
        }

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = n;
        size++;
    }

    public Integer get(int index) {
        return list[index];
    }

    public void ensureCapacity() {
        int newSize = 2 * list.length;
        list = Arrays.copyOf(list, newSize);
    }

    public void remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(list[i] + ", ");
        }
        sb.append(list[size - 1] + "]");
        return sb.toString();
    }
}