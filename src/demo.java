import java.util.ArrayList;

/*
*   测试
* */
public class demo {
    public static void main(String[] args) {
//        qSort(new int[]{1, 2, 1, 5, 4, 6, 4, 1},0,7);
//        System.out.println((byte) Integer.parseInt("11111100", 2));
//        System.out.println( Integer.toBinaryString(-4));
//
//        System.out.println();
//
//        System.out.println((byte) Integer.parseInt("00000100", 2));
//        System.out.println( Integer.toBinaryString(4));
        System.out.println(bytesToBitString(true,(byte)28));

        ArrayList<String> list =new ArrayList<>();
        list.add("ss");
        for (String s : list) {
            System.out.println(s);
        }
    }

    public static String bytesToBitString(boolean flag,byte b){
        //转化为int
        int temp=b;
        StringBuilder prefix= new StringBuilder();//用于正数前缀补零
        String str=Integer.toBinaryString(temp);
        int len=str.length();
        if (flag){//如果是最后一位，可能会少于8位，前面记录在LastLength全局变量里
            //如果是正数，需要补高位，补足LastLength位
            if (b>=0){
                while (len<8){
                    prefix.append('0');
                    len++;
                }
                return prefix+str;
            }else {//如果是负数，就需要只保留后8位
                return str.substring(str.length()-8);
            }
        }else {//不是最后一位，一定是满8位
            //如果是正数，需要补高位补足8位
            if (b>=0){
                while (len<8){
                    prefix.append('0');
                    len++;
                }
                return prefix+str;
            }else {//如果是负数，就需要只保留后8位
                return str.substring(str.length()-8);
            }
        }
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
