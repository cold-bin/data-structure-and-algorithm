package recursion;

/*
 *   八皇后
 * */
public class eightQueens {
    //皇后数
    static int max = 8;
    //保存皇后放置位置结果
    static int[] arr = new int[max];
    //结果数
    static int num = 0;

    public static void main(String[] args) {
        placeQueen(0);
        System.out.println("总共：" + num + "种结果");
    }

    //放置第n个皇后
    public static void placeQueen(int n) {
        if (n == max) {
            num++;
            print();
            return;
        }
        //放置皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //第n个皇后放到第i列
            arr[n] = i;
            //判断此时放置的位置与前面放好的八皇后是否冲突
            if (judge(n)) {
                //如果不冲突就放置下一个皇后
                placeQueen(n + 1);
            }
            //如果冲突，把皇后放到下一列
        }
    }

    //查看当我们放置第n个皇后是否与前面的皇后冲突
    public static boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //arr[i]==arr[n] -》表示同列
            //Math.abs(n-i)==Math.abs(arr[i]-arr[n]) -》表示同斜线
            //是否在同一行，没有必要没有必要
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[i] - arr[n])) {
                return false;
            }
        }
        return true;
    }

    //打印八皇后位置
    public static void print() {
        System.out.print("八皇后位置:");
        for (int e : arr) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
}
