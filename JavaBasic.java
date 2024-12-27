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

    public static class BinaryTree {
        static int idx = -1;

        public static Node buildTree(int node[]) {
            ++idx;

            if (node[idx] == -1) {
                return null;
            }

            Node newNode = new Node(node[idx]);
            newNode.left = buildTree(node);
            newNode.right = buildTree(node);
            return newNode;
        }

        public static void preorder(Node root) {
            if (root == null) {
                return;
            }
            System.out.println(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }

        public static void inorder(Node root) {
            if (root == null) {
                return;
            }
            preorder(root.left);
            System.out.println(root.data + " ");
            preorder(root.right);
        }

        public static void levelTraverse(Node node) {
            if (node == null) {
                return;
            }
            Queue<Node> queue = new LinkedList<>();

            queue.add(node);
            queue.add(null);

            while (!queue.isEmpty()) {
                Node currNode = queue.remove();
                if (currNode == null) {
                    System.out.println();
                    if (!queue.isEmpty()) {
                        queue.add(null);
                    }
                } else {
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        queue.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        queue.add(currNode.right);
                    }
                }
            }

        }

        public static int calculateHeight(Node node) {
            if (node == null) {
                return 0;
            }

            int left = 1 + calculateHeight(node.left);
            int right = 1 + calculateHeight(node.right);

            return Math.max(left, right);
        }

        public static int calculateNoOfNodes(Node node) {
            if (node == null) {
                return 0;
            }

            int left = calculateHeight(node.left);
            int right = calculateHeight(node.right);

            return left + right + 1;
        }

        public static int calculateSumOfNodes(Node node) {
            if (node == null) {
                return 0;
            }
            int left =  calculateSumOfNodes(node.left);
            int right = calculateSumOfNodes(node.right);
            return (node.data + left + right);
        }
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTree bt = new BinaryTree();
        Node root = BinaryTree.buildTree(nodes);
        System.out.println(BinaryTree.calculateSumOfNodes(root));

    }
}
