import java.util.Arrays;

public class Java {

   
    public static void maxSubArraySum(int[] arr){
        int max=0;
        for(int i =0;i<arr.length;i++){
            max+=arr[i];
            arr[i]=max;
        }

        for(int i=0;i<arr.length;i++){
            
        }

        System.out.println(Arrays.toString(arr));


    }
    public static void main(String arg[]) {
      int arr[] = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
      maxSubArraySum(arr);
        
        
    }
}
