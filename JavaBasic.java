import java.util.Arrays;

public class JavaBasic {

   public static int targetsearch(int arr[], int tar, int si, int ei) {
      if (si > ei) {
         return -1;
      }
      int mid = si + (ei - si) / 2;

      if (arr[mid] == tar)
         return mid;

      if (arr[si] <= arr[mid]) {
         if (arr[si] <= tar && tar <= arr[mid]) {
            return targetsearch(arr, tar, si, mid-1);
         } else {
            return targetsearch(arr, tar, mid + 1, ei);
         }

      } else {
         if (arr[mid] <= tar && tar <= arr[ei]) {
            return targetsearch(arr, tar, mid + 1, ei);
         } else {
            return targetsearch(arr, tar, si, mid - 1);
         }

      }

   }

   public static void main(String[] args) {
      int arr[]={4,5,6,7,0,1,2};
      int target=6;
      int index= targetsearch(arr,target,0,arr.length-1);
      System.out.println(index);

   }
}
