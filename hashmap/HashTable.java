public class HashTable<K, V> {
    public class Node<K, V> {
        public K key;
        public V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public Node[] table;
    private double loadFactor;
    private int size;
    private static int[] PRIMES = { 7, 17, 37, 79, 163, 331, 673, 1361, 2729, 5471, 10949, 21911, 43853, 87719, 175447,
            350899, 701819, 1403641, 2807303, 5614657, 11229331, 22458671, 44917381, 89834777, 179669557, 359339171,
            718678369, 1437356741 };

    public HashTable() {
        table = (Node<K, V>[]) new Node[PRIMES[0]];
        size = 0;
        loadFactor = 0.5;
    }

    public V put(K key, V value) {
        int index = (key.hashCode() % table.length);
        V oldValue = null;
        int i = 0;
        while (table[index] != null) {
            K oldKey = (K) table[index].key;
            // check if table contains key
            if (oldKey != null && oldKey.equals(key)) {
                oldValue = (V) table[index].value;
                break;
            } else {
                index = (index + 1) % table.length;
            }
        }

        // table does not contain key, value, grow size
        if (oldValue == null) {
            size++;
        }

        table[index] = new Node<K, V>(key, value);

        if (size > loadFactor * table.length) {
            grow();
        }

        return oldValue;
    }

    public V get(K key) {
        if (table == null) {
            return null;
        }

        int index = (key.hashCode() % table.length);

        if (table[index] != null && key.equals(table[index].key)) {
            return (V) table[index].value;
        } else {
            System.out.println("key doesn't exist");
        }
        return null;
    }

    private int getNextsize() {
        for (int i = 0; i < PRIMES.length; i++) {
            if (PRIMES[i] > table.length) {
                return PRIMES[i];
            }
        }
        return Integer.MAX_VALUE;
    }

    private void grow() {
        this.size = 0;
        Node[] oldTable = table;
        this.table = (Node<K, V>[]) new Node[getNextsize()];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                K key = (K) oldTable[i].key;
                V value = (V) oldTable[i].value;
                this.put(key, value);
            }

        }
    }

    public int size() {
        return this.size;
    }

    public void print() {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                StringBuffer sb = new StringBuffer();
                sb.append("key: ");
                sb.append(table[i].key);
                sb.append(" -> ");
                sb.append("value: ");
                sb.append(table[i].value);
                System.out.println(sb.toString());
            }
        }
    }

    public static void main(String[] args) {
        HashTable<Integer, Integer> ht = new HashTable<>();
        for (int i = 0; i < 10; i++) {
            ht.put(i, i);
        }
        ht.put(11, 11);
        ht.print();
    }
}
