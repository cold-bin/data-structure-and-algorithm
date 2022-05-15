package other;

class Solution3 {
    public static void main(String[] args) {
        int[] nums=new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;//最大和
        int thisSum = 0;//当前和
        int len = nums.length;
        for(int i = 0; i < len; i++) {
            thisSum += nums[i];
            if(maxSum < thisSum) {
                maxSum = thisSum;
            }
            //如果当前和小于0则归零，因为对于后面的元素来说这些是减小的。于是归零，意即从此处算开始最大和
            if(thisSum < 0) {
                thisSum = 0;
            }
        }
        return maxSum;
    }
}