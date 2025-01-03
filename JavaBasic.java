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

    public static class Infoo {
        Node node;
        int hd;

        public Infoo(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void topView(Node root) {
        Queue<Infoo> q = new LinkedList<>();
        HashMap<Integer, Node> map = new HashMap<>();

        int min = 0, max = 0;
        Infoo inf = new Infoo(root, 0);
        q.add(inf);
        q.add(null);

        while (!q.isEmpty()) {
            Infoo curr = q.remove();
            if (curr == null) {
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            }

            if (!map.containsKey(curr.hd)) {
                map.put(curr.hd, curr.node);
            }

            if (curr.node.left != null) {
                q.add(new Infoo(curr.node.left, curr.hd - 1));
                min = Math.min(min, curr.hd - 1);
            }

            if (curr.node.right != null) {
                q.add(new Infoo(curr.node.right, curr.hd + 1));
                max = Math.max(max, curr.hd + 1);
            }

            for (int i = min; i <= max; i++) {
                System.out.println(map.get(i).data);
            }

        }
    }

    public static void KLevel(Node root, int level, int k) {
        if (root == null) {
            return;
        }
        if (level == k) {
            System.out.print(root.data + " ");
            return;
        }
        KLevel(root.left, level + 1, k);
        KLevel(root.right, level + 1, k);
    }

    public static boolean getPath(Node root, int n, ArrayList<Node> path) {
        if (root == null) {
            return false;
        }
        path.add(root);

        if (root.data == n) {
            return true;
        }
        boolean fuoundLeft = getPath(root.left, n, path);
        boolean foundRight = getPath(root.right, n, path);

        if (fuoundLeft || foundRight) {
            return true;
        }

        path.remove(path.size() - 1);
        return false;
    }

    public static Node lca(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getPath(root, n1, path1);
        getPath(root, n2, path2);

        int i = 0;
        for (; i < path1.size() && i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                break;
            }
        }

        Node lcs = path1.get(i - 1);
        return lcs;

    }

    public static int minDist(Node root, int n1, int n2) {
        Node lca = lca(root, n1, n2);
        int dist1 = lcaDist(lca, n1);
        int dist2 = lcaDist(lca, n1);

        return dist1 + dist2;
    }

    public static int lcaDist(Node root, int n) {
        if (root == null) {
            return -1;
        }
        if (root.data == n) {
            return 0;
        }

        int leftDist = lcaDist(root.left, n);
        int rightDist = lcaDist(root.right, n);

        if (leftDist == -1 && rightDist == -1) {
            return -1;
        } else if (leftDist == -1) {
            return rightDist + 1;
        } else {
            return leftDist + 1;
        }
    }

    public static int KAncestor(Node root, int n, int k) {
        if (root == null) {
            return -1;
        }
        if (root.data == n) {
            return 0;
        }

        int leftDist = KAncestor(root.left, n, k);
        int rightDist = KAncestor(root.right, n, k);

        if (leftDist == -1 && rightDist == -1) {
            return -1;
        }

        int max = Math.max(leftDist, rightDist);
        if (max + 1 == k) {
            System.out.println(root.data);
        }
        return max + 1;
    }

    public static int sumTree(Node root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sumTree(root.left);
        int rightSum = sumTree(root.right);

        int data = root.data;
        root.data = leftSum + rightSum;
        return root.data + data;
    }

    public static void main(String[] args) {
        int nodes[] = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };
        BinaryTree bt = new BinaryTree();
        Node root = BinaryTree.buildTree(nodes);
        System.out.println(BinaryTree.calculateSumOfNodes(root));

    }
}
