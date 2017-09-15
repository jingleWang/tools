package jingl.wang.tools;

/**
 * Created by Wang Jinglu on 2017/8/30.
 */
public class HashMap<K, V> {
    private final int DEFAULT_CAPACITY = 16;

    private final int AVG_COUNT = 10;

    private int capacity = DEFAULT_CAPACITY;

    private int avgCount = AVG_COUNT;

    private int size = 0;

    Node<K, V>[] table;

    public HashMap() {
        table = new Node[capacity];
    }

    public void put(K key, V value) {
        resize();
        int index = 0;
        if (key != null) {
            index = hash(key.hashCode());
        }

        table[index] = putVal(table[index], key, value);
    }

    private Node<K, V> putVal(Node list, K key, V value) {
        Node currentNode = list;
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                currentNode.setValue(value);
                return list;
            }

            currentNode = currentNode.nextNode;
        }

        Node<K, V> newNode = new Node<>(key.hashCode(), key, value, list);
        return newNode;
    }

    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key.hashCode());
        Node<K, V> list = table[index];

        if (list != null) {
            Node<K, V> current = list;
            while (current != null) {
                if (current.getKey().equals(key)) {
                    return current.value;
                }

                current = current.nextNode;
            }
        }
        return null;
    }

    private class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> nextNode;

        public Node(int hash, K key, V value, Node<K, V> nextNode) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.nextNode = nextNode;
        }

        public int getHash() {
            return hash;
        }

        public void setHash(int hash) {
            this.hash = hash;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node<K, V> nextNode) {
            this.nextNode = nextNode;
        }
    }

    private int hash(int hashCode) {
        return hashCode % capacity;
    }

    private void resize() {}
}
