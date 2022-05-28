package sort;

import java.util.Arrays;

/*
 *	插入排序
 */
public class insertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 5, 4, 6, 4, 1};
        System.out.println(Arrays.toString(arr));
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
//        for (int i = 1; i < arr.length; ++i) {
//            int value = arr[i];//记录当前需要比较的数据
//            int position = i;//记录当前数据角标
//            while (position > 0 && arr[position - 1] > value) {//大于value的素数组元素往后挪一位，直到不大于
//                arr[position] = arr[position - 1];
//                position--;
//            }
//            arr[position] = value;
//        }
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int index = i;
            for (; index > 0; index--) {
                if (arr[index - 1] > val) {
                    arr[index] = arr[index - 1];
                } else {
                    break;
                }
            }
            arr[index] = val;
        }
    }
}
