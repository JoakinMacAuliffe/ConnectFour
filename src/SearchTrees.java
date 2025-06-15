class Node<K extends Comparable<K>, V>{
    K key;
    V value;
    Node<K, V> left, right;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

class BST {

    Node<Integer, String> root;

    void insert(Player p) {
        root = insertRec(root, p.getWins(), p.getPlayerName());
    }

    private Node<Integer, String> insertRec(Node<Integer, String> node, int key, String value) {

        if (node == null) {
            return new Node<>(key, value);
        }
        if (key < node.key) {
            node.left = insertRec(node.left, key, value);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    String get(int key) {
        Node<Integer, String> current = root;
        while (current != null) {
            if (key < current.key) {
                current =current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

}

class HashST {

    private static final int CAPACITY = 16;
    private Node<String, Player>[] table;

    @SuppressWarnings("unchecked")
    public HashST() {
        table = (Node<String, Player>[]) new Node[CAPACITY];
    }

    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % CAPACITY;
    }

    public void insert(Player p) {

        String key = p.getPlayerName();
        Player value = p;

        int idx = hash(key);
        Node<String, Player> node = table[idx];
        while (node != null) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
            node = node.right;
        }
        Node<String, Player> newNode = new Node<>(key, value);
        newNode.right = table[idx];
        table[idx] = newNode;
    }

    public Player get(String key) {
        int idx = hash(key);
        Node<String, Player> node = table[idx];
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.right;
        }
        return null;
    }

}