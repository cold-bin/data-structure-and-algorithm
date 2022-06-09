package other;

import java.util.Arrays;

class Solution5 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix(4)));
    }

    public static int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int val = 1;
        int i = 0, j = 0;//arr数组索引，i为行标，j列标
        int tempN1 = n;//表示每列或每行在某环的行走最大值
        int tempN2 = 0;//表示每列或每行在某环的行走最小值

        for (int l = 0; l < n - n / 2; l++) {//环数
            while (j < tempN1) {
                arr[i][j] = val++;
                j++;
            }
            j--;//还原j值
            i++;//下一个数
            while (i < tempN1) {
                arr[i][j] = val++;
                i++;
            }
            i--;//还原i值
            j--;//下一个数
            while (j >= tempN2) {
                arr[i][j] = val++;
                j--;
            }
            j++;//还原j值
            i--;//下一个数
            while (i > tempN2) {
                arr[i][j] = val++;
                i--;
            }
            i++;//还原i数
            j++;//指向下一个数字
            tempN1--;
            tempN2++;
        }
        return arr;
    }
}