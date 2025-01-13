# Binary tree ( hierarchical data structure )

leaf nodes
max 2 child
level 1 ( some are start from 0 )
depth
left & right subtrees
ancestors {parent and parent of parent }

## Build tree preorder

1 2 4 -1 -1 5 -1 -1 3 -1 6 -1 -1

```java O(n)
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
    }
    public static void main(String[] args) {
              int nodes[] ={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
              BinaryTree bt=new BinaryTree();
              Node root=BinaryTree.buildTree(nodes);
              System.out.println(root==null?"binary tree empty":root.data);

    }
}
```

## tree traversals

1. preorder
   print root -> left subtree --> right subtree

```java
        public static void preorder(Node root){
            if(root==null){
                  System.out.println("-1"+" ");
                return ;
            }
            System.out.println(root.data+" ");
            preorder(root.left);
            preorder(root.right);
        }
```

2. Inorder Traversals
   print subtree -> root --> right subtree

```java
   public static void inorder(Node root){
            if(root==null){
                return ;
            }
            preorder(root.left);
            System.out.println(root.data+" ");
            preorder(root.right);
        }
```

3. post order
   print left subtree --> right subtree --> root

```java
   public static void inorder(Node root){
            if(root==null){
                return ;
            }
            preorder(root.left);
            preorder(root.right);
            System.out.println(root.data+" ");
        }
```

4. level order traversals
   DFS -> depth fist search
   BFS -> breadth first search

```java
 public static void levelTraverse(Node node){
            if(node==null){
                return;
            }
            Queue<Node> queue=new LinkedList<>();

            queue.add(node);
            queue.add(null);

            while(!queue.isEmpty()){
                Node currNode=queue.remove();
                if(currNode==null){
                    System.out.println();
                    if(!queue.isEmpty()){
                        queue.add(null);
                    }
                }else{
                    System.out.print(currNode.data+" ");
                    if(currNode.left!=null){
                       queue.add(currNode.left);
                    }
                    if(currNode.right!=null){
                        queue.add(currNode.right);
                    }
                }
            }

        }
```

## height of a tree

```java O(Size of binarray tree)  O(N)
public static int calculateHeight(Node node){
            if(node ==null){
                return 0;
            }

            int left=1+calculateHeight(node.left);
            int right=1+calculateHeight(node.right);

            return Math.max(left, right);


            -----  or ----
             int left=calculateHeight(node.left);
            int right=calculateHeight(node.right);

            return Math.max(left, right)+1;
        }
```

## Count Of Nodes

```java
public static int calculateNoOfNodes(Node node){
           if(node ==null){
               return 0;
           }

           int left=calculateHeight(node.left);
           int right=calculateHeight(node.right);

           return left+right+1;
       }
```

## Sum of Nodes

```java

        public static int calculateSumOfNodes(Node node) {
            if (node == null) {
                return 0;
            }
            int left =  calculateSumOfNodes(node.left);
            int right = calculateSumOfNodes(node.right);
            return (node.data + left + right);
        }
```

## diameter of a tree

no of nodes in longest path between 2 nodes in binary tree

```java O(n^2)
public static int diameter(Node root) {
            if (root == null) {
                return 0;
            }

            int leftDiam=diameter(root.left);
            int leftHt=calculateHeight(root.left);
            int rightDiam=diameter(root.right);
            int rightHt=calculateHeight(root.right);

            int selfDiam=leftHt+rightHt+1;
            return Math.max(selfDiam,Math.max(rightDiam, leftDiam));

        }
```

```java O(n)
  static class Info {
            int diam;
            int ht;

            public Info(int diam, int ht) {
                this.diam = diam;
                this.ht = ht;
            }
        }

        public static Info diameterWithClass(Node root){
        Info leftInfo=diameterWithClass(root.left);
        Info rightInfo=diameterWithClass(root.right);
        int diam=Math.max(Math.max(leftInfo.diam,rightInfo.diam),leftInfo.ht+rightInfo.ht+1);
        int ht=Math.max(leftInfo.ht,rightInfo.ht);

        return new Info(diam, ht);
     }
```

## Subtree of another tree

GIven the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same struture and node values of subroot and false otherwise.

        1                         2
      /  \                      /  \
     2    3                    4    5
    / \    \

4 5 6

```java
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
```

## Top View of a tree

        1
      /  \
     2    3
    / \   / \

4 5 6 7
output 4 , 2 , 1 ,3,6

        1
      /  \
     2    3
      \
       4
        \
         5
          \
           6

output 2 , 1 , 3 ,6

> > HashMap<String,Integer> map=new HashMap<>();

       | key  , value |

to add -> put -> map.put(key,value)
to get -> get -> map.get(key)
to check -> containsKey(key)

```java
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
        Infoo inf=new Infoo(root, 0);
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
            }else{
if (!map.containsKey(curr.hd)) {
                map.put(curr.hd, curr.node);
            }

            if (curr.node.left != null) {
                q.add(new Infoo(curr.node.left, curr.hd - 1));
                min = Math.min(min,curr.hd - 1);
            }

            if (curr.node.right != null) {
                q.add(new Infoo(curr.node.right, curr.hd + 1));
                max = Math.max(max, curr.hd + 1);
            }
            }
        }

         for (int i = min; i <= max; i++) {
                System.out.println(map.get(i).data);
            }
    }
```

## Kth Level of a Tree

        1
      /  \
     2    3
    / \   / \
   4   5 6   7

K=3
output 4,5,6,7

```java
public static void KLevel(Node root, int level , int k){
        if(root==null){
            return;
        }
        if(level==k){
            System.out.print(root.data+" ");
            return;
        }
        KLevel(root.left, level+1, k);
        KLevel(root.right, level+1, k);
    }
```

## Lowest COmmon Ancestor
           1                     
         /  \
        2    3
       / \     \
      4   5     6
  n1=4,n2=6  output=1
          1
         /  \
        2    3
       / \   / \
      4   5 6   7

  n1=4,n2=5  output=2  


 ```java
 public static boolean getPath(Node root, int n,ArrayList<Node> path){
        if(root==null){
            return false;
        }
        path.add(root);

        if(root.data==n){
            return true;
        }
        boolean fuoundLeft=getPath(root.left,n,path);
        boolean foundRight=getPath(root.right, n, path);

        if(fuoundLeft||foundRight){
            return true;
        }

        path.remove(path.size()-1);
        return false;
    }

    public static Node lca(Node root, int n1, int n2){
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();

        getPath(root,n1,path1);
        getPath(root,n2,path2);

        int i =0;
        for(;i<path1.size() && i<path2.size();i++){
            if(path1.get(i)!=path2.get(i)){
                    break;
            }
        }

        Node lcs=path1.get(i-1);
        return lcs;

    }
```


## Min Distance between nodes
```java
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
```

## Kth Ancestor of node
           1                     
         /  \
        2    3
       / \     \
      4   5     6
  node=5,k=5  ans=1
```java
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
        return max+1;
    }
```

## TRansform to Sum Tree

           1                     
         /  \
        2    3
       / \   / \
      4   5  7  6

each node =sum of left & right sibtrees
          27                     
         /  \
        9    13
       / \   /  \
      0   0  0   0

# Binary Search Tree

BT                    O(N)
   ----> Seacrh ---->  
BST                   O(H)


           4                     
         /  \
        2    5
       / \    \
      1   3    6

* Left subtree Node < Root
* Right Subtree Node > Root
* Left & Right Subtrees are also BST with no duplicates

* inorder (left root right ) treaversal of a bst is a sorted sequence

## Build a BST
values[] ={5,1,3,4,2,7}
```java
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

        if(root.data>val){
           root.left= insert(root.left, val);
        }else{
            root.right= insert(root.right, val);
        }
        return root;
    }
    public static void inorder(Node root){
        if(root==null){
            return;
        }

        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);

    }
    public static void main(String[] args) {
        int nodes[] = { 5, 1, 3, 4, 2, 7 };
        Node root=null;
        for(int i=0;i<nodes.length;i++){
            root=insert(root, nodes[i]); 
        }
        inorder(root);
    }
}
```

## BST Search ( Search an element )
key = 3;


           4                     
         /  \
        2    5
       / \    \
      1   3    6 


--> time complexity
best case --> o(H)
average case --> balanced BST longN 
worst case --> skewed tree O(n)

key = 1;
           8                     
         /  \
        5    10
       / \    \
      3   6    11
     / \         \
    1   4        14 
```java
public static Boolean BinarySearchTree(Node root, int k) {
        if (root == null) {
            return false;
        }
        if(root.data==k){
            return true;
        }

        if(root.data>k){
            return BinarySearchTree(root.left, k);
        }else{
            return BinarySearchTree(root.right, k);
        }
    }
```

## Delete a Node

* No child (Lead Node)
* one child
* two child
 ___________________________
|inorder successor in a bst|  
|           is             |  
| left Most node in right  |
|         sub tree         |
----------------------------

* inorder successor always has O or 1 child

           8                     
         /  \
        5    10
       / \    \
      3   6    11
     / \         \
    1   4        14 

```java
public static Node Delete(Node root, int data){
        if(root.data>data){
            return Delete(root.left, data);
        }else if(root.data>data){
            return Delete(root.left, data);
        }else{
            if(root.right==null&&root.left==null){
                return null;
            }
            if(root.right==null){
                return root.left;
            }
            if(root.left==null){
                return root.right;
            }

            Node inorderSuccessor=findInorderSuccessor(root.right);
            root.data=inorderSuccessor.data;
            root.right=Delete(root.right,inorderSuccessor.data);


        }
        return root;
    }

    public static Node findInorderSuccessor(Node root){
        while(root.left!=null){
            root=root.left;
        }
        return root;
    }
```

## Print in range ( including 2 )
k1 =5 & K2 = 12

           8                     
         /  \
        5    10
       / \    \
      3   6    11
     / \         \
    1   4        14 
```java
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
```

## Root to Leaf Paths

          8                     
         /  \
        5    10
       / \    \
      3   6    11
                \         
                14 

8 - 5 - 3
8 - 10 -11 -14
8 - 5 -6

```js

 public static void printPath(ArrayList<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i) + " ");
        }
        System.out.println();
    }

    public static void printRoot2Leaf(Node root, ArrayList<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.data);
        if (root.left == null && root.right == null) {
            printPath(path);
        }

        printRoot2Leaf(root.left, path);
        printRoot2Leaf(root.right, path);
        path.remove(path.size() - 1);
    }
```

## Validate BST
--> Approach 1
comapare with left & right node

if a inorder travel is sorted than it is called as Valid BST

--> Approach 2
check if max value in left subtree < node
and min value in right subree > node

```js
public static boolean isValidBST(Node root,Node min,Node max){
        if(root==null){
            return true;
        }

        if(min!=null&&root.data<=min.data){
            return false;
        }else if(max!=null&&root.data>=max.data){
            return false;
        }

        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }
```

## Mirror a BST 

    8                     
   /  \
  5    10
 / \    \
3   6    11

    to

    8                     
   /  \
  10   5
 /    / \    
11   6   3      

```js

    public static void Mirror(Node root) {
        if (root == null) {
            return;
        }

        Mirror(root.left);
        Mirror(root.right);

        Node temp=root.left;
        root.left=root.right;
        root.right=temp;
    }
    
    ------- OR ------
    
    public static void Mirror(Node root) {
        if (root == null) {
            return;
        }

       Node left =Mirror(root.left);
       Node right= Mirror(root.right);

        root.left=right;
        root.left=left;
        root.right=temp;

        return root;
    }
```

## Sorted Array to balanced BST




































































