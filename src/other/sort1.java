package other;

import java.util.Arrays;

public class sort1 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{2,0};
        int[] arr2 = new int[]{1};
        merge(arr1, 1, arr2, 1);
        System.out.println(Arrays.toString(arr1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        //先合并末尾数据
        for (int i = m, j = 0; i < nums1.length && j < n; i++, j++) {
            nums1[i] = nums2[j];
        }
        //按递增排序(希尔排序)
        for (int gap = nums1.length / 2; gap > 0; gap /= 2) {//步长
            //对每组进行直接插入排序
            for (int i = gap; i < nums1.length; i++) {
                int index = i;
                int value = nums1[i];
                while (index - gap >= 0 && nums1[index - gap] > value) {
                    nums1[index] = nums1[index - gap];
                    index -= gap;
                }
                nums1[index] = value;

            }
        }
    }
}
