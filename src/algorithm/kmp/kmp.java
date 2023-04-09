package algorithm.kmp;

import java.util.Arrays;

public class kmp {
    public static void main(String[] args) {
        String str1 = "abab";
        String str2 = "ABCDABD";
//        String str2 = "ABCDABDABCE";
//        String str2 = "agctagcagctagct";
//        System.out.println(Arrays.toString(kmpNext(str2)));
//        System.out.println("第一次出现的位置：" + kmpSearch(str1, str2, kmpNext(str2)));
    }

    //kmp搜索
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //在部分匹配表往前递归，知道找到相等字符值才停止，
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取子串的部分匹配值表
    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];

        for (int i = 1; i < dest.length(); i++) {
            int j = next[i - 1];//j指针记录移动的位置，也是移动了的步数

            //不断递归判断是否存在子对称，k=0说明不再有子对称，
            // Pattern[i] != Pattern[k]说明虽然对称，但是
            // 对称后面的值和当前的字符值不相等，所以继续往前递推，递推的关系式：j=next[j-1];
            // 意即：不断的向前子串中寻找一个字符，使得这个字符等于当前字符
            while (j != 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //找到了这个子对称，或者是直接继承了前面的对称性，这两种都在前面的基础上++：就是将j指针移动后一位，一比较下一位是否相同
            if (dest.charAt(i) == dest.charAt(j)) {
                next[i] = j + 1;
            } else {//如果遍历了所有子对称都无效，说明这个新字符不具有对称性，清0
                next[i] = 0;
            }
        }

        return next;
    }
}
