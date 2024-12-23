import java.util.*;

public class JavaBasic {
    public class Queue{
        Deque<Integer> deque=new LinkedList<>();

        public void add(int data){
            deque.addFirst(data);
        }

        public int remove(){
          return  deque.removeFirst();
        }

        
        public void peek(int data){
            deque.getFirst();
        }
    }
    


    public static void main(String[] args) {

    }
}
