public class JavaBasic {

    public static long findTotalPair(long n) {
        if (n < 3) {
            return n;
        }
        return findTotalPair(n-1)+(n-1)*findTotalPair(n-2);
    }

    public static void main(String[] args) {
        long n = 1000000000;
        System.out.println(findTotalPair(n));

    }
}
