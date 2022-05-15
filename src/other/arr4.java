package other;

import java.util.Arrays;

class Solution4 {
    public static void main(String[] args) {
        int[] nums=new int[]{9,9,9,1};
        System.out.println(Arrays.toString(plusOne(nums)));
    }
    public static int[] plusOne(int[] digits) {
        int len1=digits.length;
        int len2=len1+1;
        int i,j;
        int[] arr=new int[len2];
        arr[len2-1]=1;

        for(i=len1-1,j=len2-1;;i--,j--){
            int sum=digits[i]+arr[j];
            if(sum>9){
                //进1
                arr[j-1]++;
                //取余
                arr[j]=(sum)%10;
            }else{
                arr[j]=sum;

            }
            if (i==0){
                break;
            }
        }
        //根据首位判断是否输出首位
        if (arr[0]==0){
            for (i=1,j=0;i<len2;){
                digits[j++]=arr[i++];
            }
            return digits;
        }
        return arr;
    }
}