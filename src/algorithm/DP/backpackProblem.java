package algorithm.DP;

import java.util.Arrays;

/*
 * 01背包问题
 * */
public class backpackProblem {
    public static void main(String[] args) {
        //创建表
        int[][] v = new int[4][5];//前i个物品中能够装入容量为j的背包中的最大价值
        int[] w = new int[4];//第i个物品重量
        w[1] = 1;
        w[2] = 4;
        w[3] = 3;
        int[] a = new int[4];//物品第i个物品单价
        a[1] = 1500;
        a[2] = 3000;
        a[3] = 2000;

        //开始决策填表

        //填充第一列
        int rowNum = v.length;
        for (int i = 0; i < rowNum; i++) {
            v[i][0] = 0;
        }
        //填充第一行
        int colNum = v[0].length;
        for (int j = 0; j < colNum; j++) {
            v[0][j] = 0;
        }
        //填充第二行：只能放第一个商品
        for (int j = 0; j < colNum; j++) {
            if (w[1] <= j) v[1][j] = a[1];
        }
        //决策其他单元格
        for (int i = 2; i < rowNum; i++) {
            for (int j = 1; j < colNum; j++) {
                //当前物品放不下，只有依赖上次决策的结果
                if (w[i] > j) v[i][j] = v[i - 1][j];

                //当前物品放得下，需要比较将当前物品放入之后再填放以前物品的加之和不放当前物品的价值
                else v[i][j] = Math.max(v[i - 1][j], a[i] + v[i - 1][j - w[i]]);
            }
        }

        for (int[] ints : v) {
            System.out.println(Arrays.toString(ints));
            System.out.println();
        }
    }
}
