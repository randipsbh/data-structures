import java.util.Random;

public class BinaryTree {
    // constants

    // fields
    private Node root;
    private int size;

    public BinaryTree(int data) {
        this.root = new Node(data);
        this.size = 1;
    }

    public BinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public void insert(Integer data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        if (root.data == data) {
            return;
        }

        Node curr = root;
        Node parent = null;
        while (curr != null) {
            if (data < curr.data) {
                parent = curr;
                curr = curr.left;
            } else if (data > curr.data) {
                parent = curr;
                curr = curr.right;
            } else {
                return;
            }
        }
        if (parent != null) {
            if (data < parent.data) {
                parent.left = new Node(data);
            }
            if (data > parent.data) {
                parent.right = new Node(data);
            }
        }
    }

    public void remove(int key) {
        Node curr = root;
        Node parent = null;
        while (curr != null) {
            if (key > curr.data) {
                parent = curr;
                curr = curr.right;
            } else if (key < curr.data) {
                parent = curr;
                curr = curr.left;
            } else {
                if (curr.left != null) {
                    Node max = deleteMax(curr.left);
                    if (max == curr.left) {
                        curr.left = null;
                    }
                    curr.data = max.data;
                    return;
                }
                if (curr.right != null) {
                    Node min = deleteMin(curr.right);
                    if (min == curr.right) {
                        curr.right = null;
                    }
                    curr.data = min.data;
                    return;
                }
                if (parent == null) {
                    root = null;
                } else if (key > parent.data) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
                return;
            }
        }
    }

    private Node deleteMin(Node node) {
        if (node == null) {
            return null;
        }
        Node parent = null;
        Node curr = node;
        while (curr.left != null) {
            parent = curr;
            curr = curr.left;
        }
        if (parent != null) {
            parent.left = null;
        }
        return curr;
    }

    private Node deleteMax(Node node) {
        if (node == null) {
            return null;
        }
        Node parent = null;
        Node curr = node;
        while (curr.right != null) {
            parent = curr;
            curr = curr.right;
        }
        if (parent != null) {
            parent.right = null;
        }
        return curr;
    }

    public void printPostOrder(Node root) {
        if (root == null) {
            return;
        }

        printPostOrder(root.left);
        printPostOrder(root.right);
        System.out.println(root.data + " ");
    }

    public String toString() {
        return printInOrderRecursively(root, "");
    }

    public void printInOrder() {
        System.out.println(printInOrderRecursively(root, ""));
    }

    private String printInOrderRecursively(Node root, String s) {
        if (root == null) {
            return "";
        }

        s += printInOrderRecursively(root.left, "");
        s += root.data + " ";
        s += printInOrderRecursively(root.right, "");
        return s;
    }

    public void printPreOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.data + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        // test 2
        Random rand = new Random();
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(25) + 1;
            tree.insert(arr[i]);
            System.out.printf("insert %02d: %s\n", arr[i].intValue(), tree);
        }
        System.out.printf("complete : %s\n", tree);

        for (int i = 0; i < arr.length; i++) {
            tree.remove(arr[i]);
            System.out.printf("remove %02d: %s\n", arr[i].intValue(), tree);
        }
        System.out.printf("empty tree %s\n", tree);

        // test 1;
        // tree.insert(13);
        // tree.insert(4);
        // tree.insert(12);
        // tree.insert(14);
        // tree.insert(10);
        // tree.insert(5);
        // tree.insert(1);
        // tree.insert(8);
        // tree.insert(2);
        // tree.insert(7);
        // tree.insert(9);
        // tree.insert(11);
        // tree.insert(6);
        // tree.insert(18);
        // tree.printInOrder();
        // tree.remove(4);
        // tree.printInOrder();

    }
}

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(Integer data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}
