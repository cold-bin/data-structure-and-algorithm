/*
*   测试
* */
public class demo {
    public static void main(String[] args) {
        qSort(new int[]{1, 2, 1, 5, 4, 6, 4, 1},0,7);
    }

    public static void qSort(int[] arr,int left,int right){
        int pivotIndex=(left+right)/2;//记录基准角标
        int pivotValue=arr[pivotIndex];//记录基准值
        while (left<right){
            System.out.println("外");
            //移动left
            while (left<right&&arr[left]<=pivotValue){
                left++;
                System.out.println("内1");
            }
            arr[pivotIndex]=arr[left];
            //
            while (right>left&&arr[right]>=pivotValue){
                right--;
                System.out.println("内2");
            }
            arr[pivotIndex]=arr[right];
        }
        arr[right]=pivotValue;
    }
}
