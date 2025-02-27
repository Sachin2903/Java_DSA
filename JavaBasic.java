import java.util.*;

public class JavaBasic {

    public static int rotateSort(int arr[], int tar, int s, int e) {
        if (s > e) {
            return -1;
        }
        int mid = s + (e - s) / 2;
        if (arr[mid] == tar) {
            return mid;
        }

        if (arr[s] <= arr[mid]) {
            if (arr[s] <= tar && tar <= arr[mid]) {
                return rotateSort(arr, tar, s, mid - 1);
            } else {
                return rotateSort(arr, tar, mid + 1, e);
            }

        } else {
            if(arr[mid]<=tar&&tar<=arr[e]){
                return rotateSort(arr, tar, mid + 1, e);
            }else{
                return rotateSort(arr, tar, s, mid-1);
            }
        }

    }

    public static void main(String[] args) {
        int arr[] = { 4, 5, 6, 7, 0, 1, 2 };
        int index = rotateSort(arr, 2, 0, arr.length - 1);
        System.out.println(index);
    }
}
