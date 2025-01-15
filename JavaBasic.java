import java.util.ArrayList;

public class JavaBasic {

    static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();

        public void add(int data) {
            arr.add(data);

            int x = arr.size() - 1;
            int par = (x - 1) / 2;

            while (arr.get(x) < arr.get(par)) {
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);
                x = par;
                par = (x - 1) / 2;
            }
        }

        public int peek() {
            if (arr.isEmpty()) {
                return -1;
            }
            return arr.get(0);
        }

        public int remove() {
            int data = arr.get(0);
            int temp = arr.get(arr.size() - 1);
            arr.set(arr.size() - 1, data);
            arr.set(0, temp);
            arr.remove(arr.size() - 1);

            heapify(0);
            return data;
        }

        public boolean isEmpty() {
            return arr.size() == 0;
        }

        public void heapify(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIdx = i;

            if (left < arr.size() && arr.get(minIdx) > arr.get(left)) {
                minIdx = left;
            }

            if (right < arr.size() && arr.get(minIdx) > arr.get(right)) {
                minIdx = right;
            }

            if (minIdx != i) {
                int temp = arr.get(i);
                arr.set(i, arr.get(minIdx));
                arr.set(minIdx, temp);

                heapify(minIdx);
            }

        }

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

    }

    public static void main(String[] args) {
        int arr[] = new int[1];
        System.out.println(arr[5]);
    }
}
