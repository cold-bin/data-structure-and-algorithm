package sort;

import java.util.Arrays;

/*
 *   冒泡排序
 * */
public class bubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 5, 4, 6, 4, 1};
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {//需要多少轮两两比较，每一轮都会选出一个排好序的数字
            flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) { // 从第一个元素到第i个元素
                if (arr[j] > arr[j + 1]) {
                    //交换值
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}