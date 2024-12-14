import java.util.*;

public class JavaBasic {

    public static boolean findPairSum2(ArrayList<Integer> list, int target) {
        int bp = -1;
        int n = list.size();
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i = 1)) {
                bp = i;
                break;
            }
        }

        int lp = bp + 1;
        int rp = bp==-1?list.size()-1:bp;

        while (lp != rp) {
            int sum = list.get(lp) + list.get(rp);
            if (sum == target) {
                return true;
            }

            if (list.get(lp) + list.get(rp) < target) {
                lp = (lp + 1) % n;
            } else {
                rp = (n + rp - 1) % n;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(11, 15, 6, 8, 9, 10));
        findPairSum2(list, 5);

    }
}