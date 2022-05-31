package sort;

import java.util.Arrays;

/*
 *   基数排序：这个版本的实现只适用于非负数的数组排序
 * */
public class radixSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 210, 10, 5, 41, 6, 14, 1, 21, 32, 56, 689, 9923};
        System.out.println("排序前：" + Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {

        //每个“桶”就是一个一维数组，定义10个“桶”，角标表示”桶“能装下的对应某位数字。空间换时间
        int[][] buckets = new int[10][arr.length];
        //bucketElementCount[0]记录的是buckets[0]中的数据个数，其他依次类推
        int[] bucketElementCount = new int[10];
        //最大数字
        int maxDigits = 0;
        //最大数字的位数
        int numberOfMaxDigits = 0;
        //取出arr中最大元素
        for (int v : arr) {
            if (v > maxDigits) maxDigits = v;
        }
        //计算其位数
        while (maxDigits != 0) {
            numberOfMaxDigits++;
            maxDigits /= 10;
        }
        //进行numberOfMaxDigits轮基数排序
        for (int i = 0; i < numberOfMaxDigits; i++) {
            //放数据入桶
            for (int j = 0; j < arr.length; j++) {
                //依次取出某位数
                int digitOfElement = arr[j] / (int) Math.pow(10, i) % 10;
                //将这位数放到指定的桶
                buckets[digitOfElement][bucketElementCount[digitOfElement]] = arr[j];
                //记录桶数据个数，这里采用一维数组记录桶的有效数据个数，
                //也可以采用队列数组的方式来做桶排序，这样就不需要提供这样额外的数组了
                bucketElementCount[digitOfElement]++;
            }
            int index = 0;
            //从桶中把数据依次取出，并放入原数组arr中，供下次排序使用
            for (int j = 0; j < bucketElementCount.length; j++) {
                if (bucketElementCount[j] != 0) {
                    //取出对应桶中的数据
                    for (int l = 0; l < bucketElementCount[j]; l++) {
                        arr[index++] = buckets[j][l];
                        buckets[j][l] = 0;//清理桶
                    }
                    bucketElementCount[j] = 0;//清理桶
                }
            }
        }
    }
}
