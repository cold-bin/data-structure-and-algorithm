package sort;

import java.util.Arrays;

/*
 *   选择排序
 * */
public class selectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 5, 4, 6, 4, 1};
        System.out.println(Arrays.toString(arr));
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr) {
        int temp, min, index = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            min = arr[i];//最小值
            for (int j = i + 1; j < arr.length; j++) {//从i的下一个元素开始到最后一个元素进行比较
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            if (min != arr[i]) {//min值改变，说明找到更小的值，需要交换
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
    }
}
