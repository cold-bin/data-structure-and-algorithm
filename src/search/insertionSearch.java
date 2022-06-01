package search;

/*
 *   插值查找，改进版二分查找，使用自适应的mid来靠近value，以避免盲目的分区，节省比较次数
 * */
public class insertionSearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 10, 11};
        int index1 = insertionSearch(arr, 3, 0, arr.length - 1);
        System.out.println("查找的元素在索引为" + index1 + "的位置");
    }

    public static int insertionSearch(int[] arr, int value, int left, int right) {
//        int mid = left + (right - left) / 2;//避免数据过大
        int mid = left + (value - arr[left]) / (arr[right] - arr[left]) * (right - left);//自适应mid
        if (left > right) {
            System.out.println("数据不存在");
            return -1;
        }
        if (arr[mid] < value) {//向右子序列递归
            return insertionSearch(arr, value, mid + 1, right);
        } else if (arr[mid] > value) {//向左子序列递归
            return insertionSearch(arr, value, left, mid - 1);
        } else {//递归结束条件
            return mid;
        }
    }
}
