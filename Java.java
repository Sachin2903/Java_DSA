public class Java {
    public static int stairsClimb(int n, int f[]) {
        if (n == 2 || n == 1 || n == 0) {
            return n;
        }
        if (f[n] != 0) {
            return f[n];
        }
        f[n] = stairsClimb(n - 1, f) + stairsClimb(n - 2, f);
        return f[n];
    }

    public static int countWaysTab(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }

    public static int knapsack(int val[], int wt[], int W, int n) {
        if (W == 0 || n == 0) {
            return 0;
        }
        if (wt[n - 1] <= W) {
            int ans1 = val[n - 1] + knapsack(val, wt, W - wt[n - 1], n - 1);
            int ans2 = knapsack(val, wt, W, n - 1);
            return Math.max(ans1, ans2);
        } else {
            return knapsack(val, wt, W, n - 1);
        }
    }

    public static void main(String arg[]) {
        int n = 44;
        int f[] = new int[n + 1];
        System.out.println(stairsClimb(n, f));
    }
}
