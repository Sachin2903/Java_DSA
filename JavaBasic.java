import java.util.*;

public class JavaBasic {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node insert(Node root, int val) {

        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (root.data > val) {
            root.left = insert(root.left, val);
        } else {
            root.right = insert(root.right, val);
        }
        return root;
    }

    public static void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);

    }

    public static Boolean BinarySearchTree(Node root, int k) {
        if (root == null) {
            return false;
        }
        if (root.data == k) {
            return true;
        }

        if (root.data > k) {
            return BinarySearchTree(root.left, k);
        } else {
            return BinarySearchTree(root.right, k);
        }
    }

    public static Node Delete(Node root, int data) {
        if (root.data > data) {
            return Delete(root.left, data);
        } else if (root.data > data) {
            return Delete(root.left, data);
        } else {
            if (root.right == null && root.left == null) {
                return null;
            }
            if (root.right == null) {
                return root.left;
            }
            if (root.left == null) {
                return root.right;
            }

            Node inorderSuccessor = findInorderSuccessor(root.right);
            root.data = inorderSuccessor.data;
            root.right = Delete(root.right, inorderSuccessor.data);

        }
        return root;
    }

    public static Node findInorderSuccessor(Node root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }

    public static void printInRange(Node root, int k1, int k2) {
        if (root == null) {
            return;
        }
        if (root.data >= k1 && root.data <= k2) {
            printInRange(root.left, k1, k2);
            System.out.print(root.data + " ");
            printInRange(root.right, k1, k2);
        } else if (root.data < k1) {
            printInRange(root.left, k1, k2);

        } else {
            printInRange(root.right, k1, k2);
        }

    }

    public static void main(String[] args) {
        int nodes[] = { 8, 5, 3, 1, 4, 6, 10, 11, 14 };
        Node root = null;
        for (int i = 0; i < nodes.length; i++) {
            root = insert(root, nodes[i]);
        }
        inorder(root);
    }
}
