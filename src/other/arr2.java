package other;

/*
*   每日leecode：https://leetcode.cn/problems/remove-element/submissions/
*   给你一个数组 nums和一个值 val，你需要 原地 移除所有数值等于val的元素，并返回移除后数组的新长度。
*   不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
*   元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
* */

import java.util.Arrays;

class Solution2 {
    public static void main(String[] args) {
        int[] arr =new int[]{0,1,2,2,3,3,0,2,4,2,3,3,3,2,4};
        int num = removeElement(arr,2);
        System.out.println(num+" "+ Arrays.toString(arr));
    }
    public static int removeElement(int[] nums, int val) {
        int j=0;
        int i=0;
        int len=nums.length;
        for(i=0;i<len;i++){
            if(val==nums[i]){
                //将之后的元素全部往前挪移覆盖一位
                for(j=i+1;j<len;j++){
                    nums[j-1]=nums[j];
                }
                //将数组索引也往前挪
                i--;
                len--;
            }
        }
        return len;
    }
}