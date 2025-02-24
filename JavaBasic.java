import java.util.*;

public class JavaBasic {

    public static void insertionSort(int arr[]) {

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i - 1;

            while(j>=0&&arr[j]>temp){
                arr[j+1]=arr[j];
                j--;
            }

            if(i!=j+1){
                arr[j+1]=temp;

            }
        }

    }

    public static void main(String[] args) {
        int arr[] = { 7, 1, 5, 3, 6, 4 };
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
