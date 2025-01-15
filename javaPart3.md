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

```java

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

```java
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

```java

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

## Sorted Array to balanced BST ( minimum possible height )
--> mid from sorted array
--> left section for left bst 
--> right section for right bst
```java
    public static Node createBst(int arr[], int st, int end) {
        if (st > end) {
            return null;
        }
        int mid = (st + end) / 2;
        Node root = new Node(arr[mid]);
        root.left = createBst(arr, st, mid - 1);
        root.right = createBst(arr, mid + 1, end);
        return root;
    }
```

## Convert BST to Balanced BST
        8
       / \
     6    10
    /      \  
   5        11
  /           \
 3            12

       to

       8
     /   \
    5     11
   / \    / \
  3   6  10  12

--> Inorder traversal array to balanced BST

use arrayList instead array in createBST 

```java
 public static void getInorder(Node root, ArrayList<Integer> inorder) {
        if (root == null) {
            return;
        }
        getInorder(root.left, inorder);
        inorder.add(root.data);
        getInorder(root.right, inorder);

        
    }

    public static Node balanceBst(Node root) {
        ArrayList<Integer> inorder = new ArrayList<>();
        getInorder(root, inorder);

        return createBst(inorder, 0, inorder.size()-1);

    }

```
## Size of Largest BST in BT
       50
     /   \
    30    60
   / \    / \
  5   20 45  70
            /  \
           65  80


```java
static class Info {
        boolean isBST;
        int size;
        int min;
        int max;
        
        public Info(boolean isBST,int size,int min,int max){
            this.isBST=isBST;
            this.size=size;
            this.min=min;
            this.max=max;
        }
    }

    public static int maxBST=0;

    public static Info largestBST(Node root){
        if(root==null){
            return new Info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        Info left=largestBST(root.left);
        Info right=largestBST(root.right);
        int size=left.size+right.size+1;
        int min=Math.min(root.data,Math.min(left.min,right.min));
        int max=Math.max(root.data,Math.min(left.max,right.max));

        if(root.data<=left.max||root.data>=right.min){
            return new Info(false, size, min, max);
        }
        
        if(left.isBST&&right.isBST){
            maxBST=Math.max(maxBST,size);
            return new Info(true, size, min, max);
        }

        return new Info(false, size, min, max);

        
    }
```

## Merge 2 BST
1.
       2
     /   \
    1     4
2.
       9
     /   \
    3     12
       
       to
       
       3
     /   \
    1     9
     \    / \
      2  4  12
            
* -> inorder requence of 2s
* -> merge them 
* -> than create a Balanced BST


## AVL Trees ( Self - balanced BST )
property : | HL - HR | < 2
BALANCED FACTOR : -1, 0, 1
bf = lh - rh

total possible bst ( un-balance and balance ) n!


# Heaps / Priority Queue

###### concept of priority queues

Priority Queues in JCF
add() O(logn)
remove() O(logn)
peek() O(1)
isEmpty() O(1)
PQ Integer low int have high priporty

```java
import java.util.PriorityQueue;

public class JavaBasic {
 
    public static void main(String[] args) {
       // in ascending order
       PriorityQueue<Integer> pq=new PriorityQueue<>();

       // in descending order
       PriorityQueue<Integer> pq=new PriorityQueue<>(Comparato.reverseOrder());
       pq.add(3);
       pq.add(4);
       pq.add(1);
       pq.add(7);

    }
}
```
##### OBjects in PQ
```java
import java.util.PriorityQueue;

public class JavaBasic {
 
   static class Student implements Comparable<Student>{
        String name;
        int rank;

        public Student(String name,int rank){
            this.name=name;
            this.rank=rank;
        }
        @Override
        public int compareTo(Student s2) {
        return this.rank-s2.rank;
        }
    }
    public static void main(String[] args) {
       PriorityQueue<Student> pq=new PriorityQueue<>();
       pq.add(new Student("A", 4));
       pq.add(new Student("B", 3));
       pq.add(new Student("C", 2));
       pq.add(new Student("D", 1));

    }
}
``` 

### Heaps
visualize --> Heap
implement -> Array

Max heap
       10
     /   \
    4     5
   / \   
  1   2  

Min Heap
       1
     /   \
    2     4
   / \   
  5   10 

* Binary Tree
at most 2 children

* Complete BInary Tree
CBT is a BT in which all the levels are completely filled except possibly the last one, which is filled from the left to right.

* Heap Order Property
Children >= Parent ( maxHeap )
Children >= Parent ( maxHeap ) 

* Heap is not implemented as a class
 to insert
insert than correct the heap

* heap is visiually treated as tree because we want to show parent and child relation ship

* Heap as an array/arraylist
       1
     /   \
    2     4
   / \   
  5   10 

2,3,4,5,10
node idx = i
left child=2i+1
right child=2i+2

>> to get parent index
x-1/2 here x is child index 
## Insert in min Heap
1.  add a last
2. fix heap (check parent  and if parent is large than swap )

heap has n number than height is (O)logn

```java
static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();

        public void add(int data) {
            arr.add(data);

            int x=arr.size()-1;
            int par = (x - 1) / 2;

            while(arr.get(x) < arr.get(par)){
                  int temp=arr.get(x);
                  arr.set(x,arr.get(par));
                  arr.set(par,temp);
                  x=par;
                  par = (x - 1) / 2;
            }
        }
    }
```

## get Min from Heap
```java
        public int peek(){
            if(arr.isEmpty()){
                return -1;
            }
            return arr.get(0);
        }
```

## Delete in Heap
2,3,4,5,10,6

* first and last node swap
* remove last idx
* fix heap ( heapify )

```java
public int remove() {
            int data = arr.get(0);
            int temp = arr.get(arr.size() - 1);
            arr.set(arr.size() - 1, data);
            arr.set(0, temp);
            arr.remove(arr.size() - 1);
            
            heapify(0);
            return data;
        }

        public boolean isEmpty(){
            return arr.size()==0;
        }

        public void heapify(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIdx = i;

            if (left < arr.size() && arr.get(minIdx) > arr.get(left)) {
                minIdx = left;
            }

            if (right < arr.size() && arr.get(minIdx) > arr.get(right)) {
               minIdx=right;
            }

            if(minIdx!=i){
                int temp=arr.get(i);
                arr.set(i,arr.get(minIdx));
                arr.set(minIdx,temp);

                heapify(minIdx);
            }

        }
```

## Heap Sort O(nlogn)
>> asc -> Max Heap
>> desc -> Min Heap
arr=1,2,4,5,3

       1
     /   \
    2     4
   / \   
  5   3  


* maxHeapyfy n/2 to 0 ( non leaf node ) to convert min to max Heap
* move first to last and make last as not a part of heap

```java 
public static void heapifyMax(int arr[], int i, int size) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int maxIdx = i;

            if (left < size && arr[left] > arr[maxIdx]) {
                maxIdx = left;
            }
            if (right < size && arr[right] > arr[maxIdx]) {
                maxIdx = right;
            }

            if (maxIdx != i) {
                int temp = arr[i];
                arr[i]=arr[maxIdx];
                arr[i]=temp;
                heapifyMax(arr, maxIdx, size);
            }

        }

        public static void heapSort(int arr[]) {

            int n = arr.length;
            for (int i = n / 2; i >= 0; i--) {
                heapifyMax(arr, i, n);
            }

            for (int i = n - 1; i > 0; i--) {
                int temp = arr[0];
                arr[0] = arr[i];
                arr[i] = temp;

                heapifyMax(arr, 0, i);
            }
        }
```

## Nearby Cars
we are given N points in a 2D plane which are locations of N cars . if we are at the origin, print the nearest k cars.

CO (3,3)
C1 (5,-1)
C2 (-2,4)
K = 2 
ans = CO & C2

```java
    static class Point implements Comparable<Point>{
        int x;
        int y;
        int distSq;
        int idx;
        public Point(int x,int y,int distSq,int idx){
            this.idx=idx;
            this.x=x;
            this.y=y;
            this.distSq=distSq;

        }

        @Override
        public int compareTo(JavaBasic.Point arg0) {
           return this.distSq-arg0.distSq;
        }
    }

    public static void main(String[] args) {
         int pts[][]={{3,3},{5,-1},{-2,4}};
         int k=2;
         PriorityQueue<Point> pq=new PriorityQueue<>();
         for(int i=0;i<pts.length;i++){
            int distSq=pts[i][0]*pts[i][0]+pts[i][1]*pts[i][1];
            pq.add(new Point(pts[i][0],pts[i][1],distSq,i));
         }
         for(int i=0;i<k;i++){
            System.out.println("C"+pq.remove().idx);
         }
    }
```

## Given are N ropes of different lengths, the task is to connect these ropes into one rope with minimum cose, such that the cost to connect two ropes is equal to the sum of their lengths.

ropes={4,3,2,6};

ans=29;

connect 2 & 3 [5]
connect 5  & 4 [9]
connect 9 & 6 [15]

```java
 public static void main(String[] args) {
        int ropes[] = { 2, 3, 3, 4, 6 };
        PriorityQueue<Integer> pq=new PriorityQueue<>();

        for(int i=0;i<ropes.length;i++){
            pq.add(ropes[i]);
        }

        int cost=0;
        while(pq.size()>1){
            int min=pq.remove();
            int min2=pq.remove();
            cost+=min+min2;
            pq.add(min+min2);
        }
        System.out.println("cost of connecting n ropes = "+cost);
    }
```


## Weakest Soldier
We are given an mxn binary matrix of 1's (soldiers) and O's (cicilians). the soldiers are positioned in front of the civilians. that is, all the 1's will appear to the left of alll the 0's in each row.

A row is weaker than a row j if one of the following is true
* the number of soldiers in row i is less than the number of soldiers in row j.
* Both rows have the same number if soldiers and i<j.
Fidn the K weakest rows.

m=4,n=4,k=2  ans=row0 & row2
1 0 0 0
1 1 1 1
1 0 0 0
1 0 0 0

```java
    static class Row implements Comparable<Row>{
        int soldiers;
        int idx;
        public Row(int s,int i){
            this.soldiers=s;
            this.idx=i;
        }
        @Override
        public int compareTo(JavaBasic.Row arg0) {
           if(this.soldiers==arg0.soldiers){
            return this.idx-arg0.idx;
           }else{
            return this.soldiers-arg0.soldiers;
           }
        }
    }

    public static void main(String[] args) {
        int arr[][]={{1,0,0,0},{1,1,1,1},{1,0,0,0},{1,0,0,0}};

        int k=2;

        PriorityQueue<Row> pq=new PriorityQueue<>();

        for(int i=0;i<arr.length;i++){
            int count=0;
            for(int j=0;j<arr[0].length;j++){
                count+=arr[i][j]==1?1:0;
            }
            pq.add(new Row(count,i));
        }

        for(int i=0;i<k;i++){
            System.out.println("R"+pq.remove().idx);
        }
    }
```

## SLiding Window Maximum O(nlogk)

maximum of all subarrrays of size k
1,2,3,4,5,6,7,8,9,10  k=3

ans=3,4,5,6,7,8,9,10

2.
1,3,-1,-3,5,3,6,7 k=3
ans=3,3,5,5,6,7 


add k number in PQ
check the peek index should be from range i-k PQ.peek()
add another in PQ


# Hashing 





 
















































