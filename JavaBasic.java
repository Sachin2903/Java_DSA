import java.util.ArrayList;
import java.util.PriorityQueue;

public class JavaBasic {

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
}
