package algorithm.DP;

public class standard_back_pack {
    public static void main(String[] args) {
        int[] v = new int[]{15, 20, 30};// 第i个物品的价值
        int[] w = new int[]{1, 3, 4};    // 第i个物品的重量
        int bag = 4;                       // 背包的容量

        int[] dp = new int[bag + 1]; // dp[i]表示当前背包容量为j的最大价值
        dp[0] = 0;// 背包容量为0时，最大价值是0
        for (int i = 0; i < 3; i++) {// 物品
            for (int j = bag; j >= 1; j--) {// 背包容量、
                // 需要倒序遍历背包容量。如果是顺序放入，那么根据dp转移方程：
                // 第j个状态可能是由前面的第j-w[i]个状态转移过来。
                // 如果顺序遍历，那么就会先算出前面的值，算出前面的值过后，后面dp转移的时候又可能会用到，
                // 那么也就是说，一个物品被放入了多次。这就不是01背包问题了，而是完全背包
                if (j >= w[i]) dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }

        System.out.println(dp[bag]);
    }
}
