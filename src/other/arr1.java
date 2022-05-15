package other;

import java.util.Arrays;
/*
* 每日Leecode：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
* 比较相邻的数字，相同就把后面的数字覆盖至前面
* */

class Solution1 {
    public static void main(String[] args) {
        int[] arr =new int[]{1,1,13};
        int num = removeDuplicates(arr);
        System.out.println(num+Arrays.toString(arr));
    }
    public static int removeDuplicates(int[] nums) {
        int len=nums.length;
        int index=0;
        int v=0;
        int num=0;
        for(int i=0;i<len;i++){
            v=nums[i];
            for(int j=i+1;j<len;j++){
                if(v!=nums[j]){
                    //记录不相等字符的起初位置
                    index=j;
                    break;
                }
            }
            //将最后一个重复数字移至i
            if(index!=0&&i<len-1){
                //将下一个不同的值复制至上次值的后续所有位
                for (int a=0;a<index-i;a++){
                    nums[i+1+a]=nums[index];
                }
            }
        }
        //处理尾部
        for (int i=0;i<len;i++){
            int n=nums[len-1];
            if (nums[i]==n){
                num=i;
                break;
            }
        }

        System.out.print((num+1)+",[");
        for(int i=0;i<num+1;i++){
            if (i==num){
                System.out.print(nums[i]);
                break;
            }
            System.out.print(nums[i]+",");
        }
        System.out.println("]");
        return num;
    }
}
