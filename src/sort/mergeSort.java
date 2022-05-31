package sort;

import java.util.Arrays;

public class mergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] arr = {4, 5, 7, 8, 1, 2, 3, 6};
        int[] temp = new int[arr.length];
        System.out.println("排序前：" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    //分+和
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    //mid指左子序列最后一个元素
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//指左子序列起点处
        int j = mid + 1;//指右子序列起点处
        int t = 0;//temp数组索引
        //先将左右两边有序数据填充到temp数组，直到又一边填充完毕
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else if (arr[i] > arr[j]) {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        //再将一边剩下的有序数据移到temp后面
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        //拷贝指定数量至arr数组,注意左右拷贝边界
        t = 0;
        int tLeft = left;
        while (tLeft <= right) {
            arr[tLeft++] = temp[t++];
        }

    }
}
