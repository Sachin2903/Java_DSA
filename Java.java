
import java.util.*;

public class Java {

    public static void main(String[] args) {
        int arr[] = {64, 34, 25, 12, 22, 11, 90};

        for (int i = 1; i < arr.length - 1; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;

            }
            if (j + 1 != i) {
                arr[j + 1] = temp;
            }

        }

        System.out.println(Arrays.toString(arr));
    }
}
