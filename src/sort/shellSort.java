package sort;

import java.util.Arrays;

public class shellSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 5, 4, 6, 4, 1};

        System.out.println("排序前：" + Arrays.toString(arr));
        shellSortBySwap(arr);
        System.out.println("交换法排序后：" + Arrays.toString(arr));
        arr = new int[]{1, 2, 1, 5, 4, 6, 4, 1};
//        arr=new int[]{2,1};
        shellSortByMove(arr);
        System.out.println("移位法排序后：" + Arrays.toString(arr));
    }

    public static void shellSortBySwap(int[] arr) {
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//增量序列,gap为步长,分组
            for (int i = 0; i < arr.length - gap; i++) {//遍历有多少组：(arr.length-gap)组
                for (int j = 0; j < arr.length - gap; j += gap) {//遍历交换组内元素，
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    public static void shellSortByMove(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {//分组
            //对每组所有元素进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int position = i;
                int value = arr[i];
                if (arr[position] < arr[position - gap]) {
                    while (position - gap >= 0 && value < arr[position - gap]) {
                        arr[position] = arr[position - gap];
                        position -= gap;
                    }
                    arr[position] = value;
                }
            }
        }
    }
}
