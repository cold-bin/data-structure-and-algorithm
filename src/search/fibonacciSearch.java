package search;

import java.util.Arrays;

/*
 *   斐波那契查找
 *  */
public class fibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibonacciSearch(arr, 1000);
        System.out.println(index);
    }

    //斐波那契查找算法
    public static int fibonacciSearch(int[] arr, int val) {
        int low = 0;
        int high = arr.length - 1;//代表原数组最高的下标4
        int k = 0;//斐波那契分割数值的下标
        int mid;
        int[] f = getFib();
        //获取到斐波那契分割数值的下标 1 1 2 3 5 8
        while (arr.length > f[k] - 1) {//6 2
            k++;//4
        }
        //因为f[k]值可能大于a的长度，因此我们需要使用Arrays类，构造一个新的数组，并指向a[]
        int[] temp = Arrays.copyOf(arr, f[k]);
        //填充新的数组，因为新数组长度如果比原数组大，多的部分会被填充0，因此需要使用最后面的最大值来填充，防止数组顺序被破坏
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        //找到中间值mid后开始查找
        while (low <= high) {//只要这个条件满足，就可以继续查找
            mid = low + f[k - 1] - 1;//
            if (val < temp[mid]) {//向左查找
                high = mid - 1;
                //为什么是k--
                //说明
                //1.全部元素 = 前面的元素 + 后边元素
                //2.f[k] = f[k-1]+f[k-2]
                //因为前面有f[k-1]个元素，所以可以继续拆分f[k-1] = f[k-2] + f[k-3]
                //即在f[k-1]的前面继续查找k--
                //即下次循环mid = f[k-1-1]-1
                k--;
            } else if (val > temp[mid]) {
                low = mid + 1;
                //为什么是k-=2
                //说明
                //继续拆分f[k-2] = f[k-3] + f[k-4]
                //即在f[k-2]的前面继续查找k-=2
                //即下次循环mid = f[k-1-1-1]-1
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    //数组扩容过，此时返回mid就会超出原数组的长度
                    return high;
                }
            }
        }
        return -1;
    }

    //生成一个斐波那契数列
    public static int[] getFib() {
        int[] f = new int[maxSize];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
