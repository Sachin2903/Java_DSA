import java.util.*;

public class JavaBasic {
    public class DoubleLL {
        public class Node {
            int data;
            Node next;
            Node prev;

            public Node(int data) {
                this.data = data;
                this.next = null;
                this.prev = null;
            }
        }

        public static Node head;
        public static Node tail;
        public static int size=0;

        void addNew(){
            Node newNode =new Node(2);
            if(head==null){
                head=tail=newNode;
                return;
            }

            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
        }

        public void reverse(){
            Node curr=head;
            Node prev=null;
            Node next;


            while(curr!=null){
                next=curr.next;
                curr.next=prev;
                 curr.prev=next;

                 prev=curr;
                 curr=next;
            }

            head=prev;
            
        }

    }

    public static void main(String[] args) {
        JavaBasic.DoubleLL dllist = new JavaBasic().new DoubleLL();

    }
}