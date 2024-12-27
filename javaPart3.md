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

