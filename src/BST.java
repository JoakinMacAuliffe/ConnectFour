class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BST {
    Node root;
    void insert(int x) {
        if (root == null) {
           root = new Node(x);
           return;
        }
        Node aux = root;
        while (aux != null) {
            if (aux.value > x) {
                if (aux.left == null) {
                    aux.left = new Node(x);
                    return;
                }
                aux = aux.left;
            }
            if (aux.value < x) {
                if (aux.right == null) {
                    aux.right = new Node(x);
                }
                aux = aux.right;
            }
        }
    }
}