package algorithm.DP;

import java.util.Arrays;

/*
 * 01背包问题：
 *  有n件物品和一个最多能背重量为w 的背包。
 *  第i件物品的重量是weight[i]，得到的价值是value[i] 。每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大。
 * */
public class backpackProblem {
    public static void main(String[] args) {
        int[] v = new int[]{15, 20, 30};// 第i个物品的价值
        int[] w = new int[]{1, 3, 4};    // 第i个物品的重量
        int bag = 4;                       // 背包的容量

        // 状态表示：dp[i][j]表示当前背包装有前i个物品，j重量的物品的最大价值（i、j从1开始）
        int[][] dp = new int[4][bag+1];
        // 状态初始化: 取第0个物品时，能够装下背包容量大于第0个物品重量的价值为0
        // 所以已经初始化完毕

        // 状态转移
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                // 当j<w[i]时，就表明当前背包容量小，无法放入这个物品，那么只能去找美方这个物品前的最大价值
                if (j < w[i-1]) dp[i][j] = dp[i - 1][j];
                    // 当j>=w[i]时，表明当前背包可以是j-w[i]容量背包装了w[i]的物品转移而来，那么价值选择放这个物品和不放这个物品的最大价值
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i-1]] + v[i-1]);
            }
        }

        System.out.println("状态dp：" + Arrays.deepToString(dp));
        System.out.println("最大价值：" + dp[3][4]);
    }
}