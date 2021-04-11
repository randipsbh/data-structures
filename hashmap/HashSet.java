public class HashSet<K> {
    public HashTable<K, Boolean> hashSet;

    public HashSet() {
        this.hashSet = new HashTable<>();
    }

    public boolean contains(K key) {
        return hashSet.get(key) != null && hashSet.get(key);
    }

    public void insert(K key) {
        hashSet.put(key, true);
    }

    public void remove(K key) {
        hashSet.put(key, false);
    }

    public int size() {
        return hashSet.size();
    }

    public void print() {
        HashTable.Node[] entry = hashSet.table;
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        int i;
        for (i = 0; i < entry.length; i++) {
            if (entry[i] != null && (Boolean) entry[i].value) {
                sb.append(entry[i].key);
                sb.append(", ");

            }
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("}");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            hashSet.insert(i);
        }
        hashSet.insert(38988);
        // hashSet.remove(4);
        hashSet.insert(3);
        hashSet.print();
    }
}
