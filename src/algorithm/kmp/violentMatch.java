package algorithm.kmp;

/*
 * 暴力匹配字符串：str2在str1之中的位置
 * */
public class violentMatch {
    public static void main(String[] args) {
        String str1 = "i am a goood good boy";
        String str2 = "good";

        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();

        int len1 = chs1.length;
        int len2 = chs2.length;

        int i = 0;
        int j = 0;


        while (i < len1 && j < len2) {
            if (chs1[i] == chs2[j]) {
                i++;
                j++;
            } else {//不相等,则回溯到源字符串的下一个位置，如果回溯到原位置会无限循环
                i=i-j+1;
                j=0;
            }
        }
        System.out.println("匹配字符串下标: "+(i-j));
    }
}