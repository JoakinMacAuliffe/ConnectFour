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

    void insert(int key, String value) {
        root = insertRec(root, key, value);
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