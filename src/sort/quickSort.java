package sort;

import java.util.Arrays;

public class quickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 5, 4, 6, 4, 1};
//        int[] arr = new int[]{-9,78,0,23,-567,70};
        System.out.println("排序前：" + Arrays.toString(arr));
        quickSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr) {
        qsort(arr, 0, arr.length - 1);
    }

    private static void qsort(int[] arr, int low, int high) {
        if (low >= high)
            return;
        int pivotIndex = partition(arr, low, high);        //将数组分为两部分
        qsort(arr, low, pivotIndex - 1);                   //递归排序左子数组
        qsort(arr, pivotIndex + 1, high);                  //递归排序右子数组
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];     //基准
        while (low < high) {
            while (low < high && arr[high] >= pivot) --high;
            arr[low] = arr[high];             //交换比基准小的记录到左端
            while (low < high && arr[low] <= pivot) ++low;
            arr[high] = arr[low];           //交换比基准大的记录到右端
        }
        //扫描完成，基准到位
        arr[low] = pivot;
        //返回的是基准的位置
        return low;
    }
}