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
            int left = calculateSumOfNodes(node.left);
            int right = calculateSumOfNodes(node.right);
            return (node.data + left + right);
        }

        public static int diameter(Node root) {
            if (root == null) {
                return 0;
            }

            int leftDiam = diameter(root.left);
            int leftHt = calculateHeight(root.left);
            int rightDiam = diameter(root.right);
            int rightHt = calculateHeight(root.right);

            int selfDiam = leftHt + rightHt + 1;
            return Math.max(selfDiam, Math.max(rightDiam, leftDiam));

        }
    }

    static class Info {
        int diam;
        int ht;

        public Info(int diam, int ht) {
            this.diam = diam;
            this.ht = ht;
        }
    }

    public static Info diameterWithClass(Node root) {
        Info leftInfo = diameterWithClass(root.left);
        Info rightInfo = diameterWithClass(root.right);
        int diam = Math.max(Math.max(leftInfo.diam, rightInfo.diam), leftInfo.ht + rightInfo.ht + 1);
        int ht = Math.max(leftInfo.ht, rightInfo.ht);

        return new Info(diam, ht);
    }

    public static boolean isIdentical(Node root, Node subRoot) {
        if (root == null && subRoot == null) {
            return true;
        } else if (root == null || subRoot == null || root.data != subRoot.data) {
            return false;
        }

        if (!isIdentical(root.left, subRoot.left)) {
            return false;
        }

        if (!isIdentical(root.right, subRoot.right)) {
            return false;
        }
        return true;

    }

    public static boolean isSubtree(Node root, Node subRoot) {
        if (root == null) {
            return false;
        }
        if (root.data == subRoot.data) {
            if (isIdentical(root, subRoot)) {
                return true;
            }
        }

        boolean leftAns = isSubtree(root.left, subRoot);
        boolean rightAns = isSubtree(root.right, subRoot);

        return leftAns || rightAns;
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTree bt = new BinaryTree();
        Node root = BinaryTree.buildTree(nodes);
        System.out.println(BinaryTree.calculateSumOfNodes(root));

    }
}
