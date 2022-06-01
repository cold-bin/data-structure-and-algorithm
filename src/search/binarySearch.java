package search;

/*
 *   二分查找有序序列
 * */
public class binarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 10, 11};
        int index = binarySearch1(arr, 4);
        System.out.println("查找的元素在索引为" + index + "的位置");

        int index1 = binarySearch2(arr, 4, 0, arr.length - 1);
        System.out.println("查找的元素在索引为" + index1 + "的位置");
    }

    //折半查找:数组已经有序，从小到大的顺序
    public static int binarySearch1(int[] arr, int value) {
        int left = 0, right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;//边界条件的移动也促使mid的移动
            if (arr[mid] > value) {//value在arr[mid]之前
                right = mid - 1;//将边界条件缩小至mid-1
            } else if (arr[mid] < value) {//value在arr[mid]之后
                left = mid + 1;//将边界条件扩大至mid+1
            } else {
                //如果有多个数据，可以从此处索引往前和往后遍历至不等于value为止，将所有数据放入数组在返回
                return mid;
            }
        }
        System.out.println("数据不存在");
        return -1;
    }

    //递归查找
    public static int binarySearch2(int[] arr, int value, int left, int right) {
        int mid = left + (right - left) / 2;//避免数据过大
        if (left > right) {
            System.out.println("数据不存在");
            return -1;
        }
        if (arr[mid] < value) {//向右子序列递归
            return binarySearch2(arr, value, mid + 1, right);
        } else if (arr[mid] > value) {//向左子序列递归
            return binarySearch2(arr, value, left, mid - 1);
        } else {//递归结束条件
            return mid;
        }
    }
}
