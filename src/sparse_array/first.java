package sparse_array;

import java.util.Arrays;

public class first {
    public static void main(String[] args) {
        //初始化二维数组
        int[][] oldArray = new int[10][10];
        oldArray[1][3] = 1;
        oldArray[2][7] = 3;
        oldArray[5][2] = 10;
        System.out.println(Arrays.deepToString(oldArray));
        int validNum = 0;
        //获取不同值个数
        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[i].length; j++) {
                //将非重复元素存到压缩数组
                if (oldArray[i][j] != 0) {
                    validNum++;
                }
            }
        }

        //初始化稀疏数组，确定深度
        int[][] parseArray = new int[validNum + 1][3];
        int index = 0;
        //初始化稀疏数组第一个索引，用来表示原数组的个数及值重值
        parseArray[0][0] = oldArray.length;
        parseArray[0][1] = oldArray[0].length;
        parseArray[0][2] = 0;
        for (int i = 0; i < oldArray.length; i++) {
            for (int j = 0; j < oldArray[i].length; j++) {
                //将非重复元素存到压缩数组
                if (oldArray[i][j] != 0) {
                    index++;
                    //记录非零值
                    parseArray[index][0] = i;
                    parseArray[index][1] = j;
                    parseArray[index][2] = oldArray[i][j];
                }
            }
        }
        //稀疏数组结果
        System.out.println(Arrays.deepToString(parseArray));

        //还原稀疏数组
        //初始化稀疏数组大小
        int[][] newArray = new int[parseArray[0][0]][parseArray[0][1]];

        //读取稀疏数组数值
        for (int i = 1; i < parseArray.length; i++) {
            newArray[parseArray[i][0]][parseArray[i][1]] = parseArray[i][2];
        }
        System.out.println(Arrays.deepToString(newArray));
    }
}
