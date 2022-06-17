package sort;

import java.util.Arrays;

/*
 * 堆排序：升序排列
 * */
public class heapSort {
    public static void main(String[] args) {
        int[] arr = {4, 13, 11, 0, 1, 2, 3, 4, 3, 2, 1, 6, 8, 5, 9, 14, 12, 9, 0};
        System.out.println(Arrays.toString(sortArray(arr)));
    }

    //堆排序
    public static int[] sortArray(int[] nums) {
        //建立初始大根堆
        buildMaxHeap(nums);
        //调整大根堆
        for (int i = nums.length - 1; i > 0; i--) {
            //将大根堆顶与最后一个元素交换，通过不断交换，最后得到一个升序数组（每次交换从堆中移出已排好序的元素，堆逐渐变小）
            swap(nums, 0, i);
            //调整剩余数组，使其满足大顶堆
            maxHeapify(nums, 0, i);
        }
        return nums;
    }

    //建立初始大根堆
    public static void buildMaxHeap(int[] nums) {
        //最后一个非叶子节点开始，这样不断地从左至右，从下至上的调整，每次调整只是调整最多三个节点的小堆，方便处理
        for (int i = nums.length / 2 - 1; i >= 0; i--) {
            //调整每一个子树为大根堆
            maxHeapify(nums, i, nums.length);
        }
    }

    //调整大根堆，第二个参数为堆顶，第三个参数为，堆的大小
    public static void maxHeapify(int[] nums, int i, int heapSize) {
        //左子树
        int l = 2 * i + 1;
        //右子树
        int r = l + 1;
        //记录根结点、左子树结点、右子树结点三者中的最大值下标
        int largest = i;
        //与左子树结点比较
        if (l < heapSize && nums[l] > nums[largest]) {
            largest = l;
        }
        //与右子树结点比较
        if (r < heapSize && nums[r] > nums[largest]) {
            largest = r;
        }
        //如果当前节点的左子树或右子树比它大，则赋值
        if (largest != i) {
            //将最大值交换为根结点
            swap(nums, i, largest);
            //再次调整交换数字后的大顶堆
            //当树的深度够大时，如果没有下面这条语句，
            //也就是说，每次交换只会停留在每一个小堆（最多三个节点构成的小堆）
            //还需要递归看看其左右子树是否存在比当前节点大的数，有则交换上来，无则退出递归
            maxHeapify(nums, largest, heapSize);
        }
    }

    //交换
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}

