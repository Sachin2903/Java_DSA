import java.util.*;

public class JavaBasic {
    public static class LinkedList {
        public static Node head;
        public static Node tail;
        public static int size = 0;

        public class Node {
            int data;
            Node next;

            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        public void addFirst(int data) {
            Node newNode = new Node(data);
            size++;
            if (head == null) {
                head = tail = newNode;
                return;
            }

            newNode.next = head;
            head = newNode;
        }

        void printList() {
            Node currentNode = head;
            while (currentNode != null) {
                System.out.print(currentNode.data + "-->");
                currentNode = currentNode.next;
            }
            System.out.println("null");
        }

        void removeFirst() {
            if (head == null) {
                return;
            }
            if (tail == head) {
                tail = null;
            }
            head = head.next;
            --size;
        }

        void removeLast() {
            if (tail == null) {
                return;
            }
            if (head == tail) {
                tail = head = null;
                size--;
                return;
            }

            Node currentNode = head;
            while (currentNode.next != tail) {
                currentNode = currentNode.next;
            }
            size--;
            currentNode.next = null;
            tail = currentNode;
        }

        int Search(int n) {
            if (head == null) {
                return -1;
            }

            int i = 0;
            Node currentNode = head;
            while (i < size) {
                if (currentNode.data == n) {
                    return i;
                }
                currentNode = currentNode.next;
                i++;
            }
            return -1;
        }

        public int helper(Node head, int key) {
            if (head == null) {
                return -1;
            }

            if (head.data == key) {
                return 0;
            }

            int idx = helper(head.next, key);
            if (idx == -1) {
                return -1;
            }
            return idx + 1;
        }

        void IterativeReverse() {
            if (head == null) {
                return;
            }
            Node currNode = head;
            tail = head;

            while (currNode.next != null) {
                Node nextNode=currNode.next;
                nextNode.next=currNode;
                currNode=nextNode;
            }
            tail.next=null;
            head=currNode;

        }

    }

    public static void main(String[] args) {
        LinkedList li = new LinkedList();
        li.printList();
        li.printList();
        li.printList();
        li.helper(LinkedList.head, 0);
    }
}