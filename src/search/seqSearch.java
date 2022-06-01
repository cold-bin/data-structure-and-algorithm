package search;
/*
*   顺序查找
* */
public class seqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 4, -1, 2, -4, 8, 90};
        int index = seqSearch(arr, 90);
        System.out.println("查找的元素在索引为" + index + "的位置");
    }

    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        System.out.println("没有该数据");
        return 0;
    }
}
