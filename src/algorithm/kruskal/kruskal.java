package algorithm.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * 克鲁斯卡尔算法求最小生成树
 * */
public class kruskal {
    private int edgeNum = 0;
    private char[] vertexes;
    private int[][] matrix;
    private static final int INF = Integer.MAX_VALUE;//两个顶点不能联通

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                //@**/*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};
        kruskal k = new kruskal(vertexes, matrix);
        k.show();
        edge[] minTree = k.getMinTree();

        System.out.println("最短修边如下：\n" + Arrays.toString(minTree));

    }

    //获取最小生成树，kruskal算法
    public edge[] getMinTree() {
        //获取边
        ArrayList<edge> edges = getEdges();
        //将边进行排序
        Collections.sort(edges);

        edge[] result = new edge[this.edgeNum];//结果边
        int index = 0;

        int[] mark = new int[this.vertexes.length];//标记不同顶点
        for (int i = 0; i < mark.length; i++) {
            mark[i] = i;
        }

        //取出边，把两端点终点不相同的边放入结果里
        for (edge e : edges) {
            int start = getPosition(e.start);
            int end = getPosition(e.end);

            if (mark[start] != mark[end]) {//没有构成回路
                //添加
                result[index++] = e;

                int e1 = mark[end];
                //将标记数组的这两个端点更新成同一个数据即可
                //这样标记数组里，相同的值代表是直接或间接联通的。这样可以避免联通端点再联通形成环状结构
                for (int i = 0; i < mark.length; i++) {
                    if (mark[i] == e1) {
                        mark[i] = mark[start];
                    }
                }
////todo            或者
//                int e1 = mark[start];
//                //更新标记
//                for (int i = 0; i < mark.length; i++) {
//                    if (mark[i] == e1){
//                        mark[i] = mark[end];
//                    }
//                }
                //提前退出
                if (index == this.vertexes.length - 1) break;
            }
        }
        return result;
    }

    //获取边的数组
    public ArrayList<edge> getEdges() {
        ArrayList<edge> edges = new ArrayList<>(this.edgeNum);
        for (int i = 0; i < this.vertexes.length; i++) {
            for (int j = i + 1; j < this.vertexes.length; j++) {//省略自己和下半角
                if (this.matrix[i][j] != INF) {
                    edges.add(new edge(getChar(i), getChar(j), this.matrix[i][j]));
                }
            }
        }
        return edges;
    }

    //获取对应字符的下标
    public int getPosition(char ch) {
        for (int i = 0; i < this.vertexes.length; i++) {
            if (ch == this.vertexes[i]) return i;
        }
        return -1;
    }

    //根据下标获取对应字符
    public char getChar(int index) {
        return this.vertexes[index];
    }

    public kruskal(char[] vertexes, int[][] matrix) {
        int vLen = vertexes.length;
        this.vertexes = new char[vLen];
        this.matrix = new int[vLen][vLen];
        //浅拷贝
        for (int i = 0; i < vLen; i++) {
            this.vertexes[i] = vertexes[i];
            for (int j = 0; j < vLen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < this.vertexes.length; i++) {
            for (int j = i + 1; j < this.vertexes.length; j++) {
                if (this.matrix[i][j] != INF) {
                    this.edgeNum++;
                }
            }
        }
    }

    public void show() {
        System.out.println("邻接矩阵：");
        for (int i = 0; i < this.vertexes.length; i++) {
            for (int j = 0; j < this.vertexes.length; j++) {
                System.out.printf("%12d", this.matrix[i][j]);
            }
            System.out.println();
        }

    }
}

class edge implements Comparable<edge> {
    char start;
    char end;
    int weight;

    public edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(edge o) {
        return this.weight - o.weight;
    }
}