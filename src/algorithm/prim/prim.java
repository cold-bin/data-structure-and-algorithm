package algorithm.prim;

import java.util.Arrays;

/*
 * prim算法解决最小生成树问题
 * */
public class prim {
    public static void main(String[] args) {
        char[] vertexes = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexNum = vertexes.length;
        int max = Integer.MAX_VALUE;
        int[][] weight = new int[][]{
                {max, 5, 7, max, max, max, 2},
                {5, max, max, 9, max, max, 3},
                {7, max, max, max, 8, max, max},
                {max, 9, max, max, max, 4, max},
                {max, max, 8, max, max, 5, 4},
                {max, max, max, 4, 5, max, 6},
                {2, 3, max, max, 4, 6, max},
        };

        MGraph graph = new MGraph(vertexNum);
        MST mst = new MST();
        mst.createGraph(graph, vertexNum, vertexes, weight);
//        mst.showGraph(graph);
        mst.prim(graph, 1);
    }
}

//创建最小生成树
class MST {
    //构建图
    public void createGraph(MGraph graph, int vertexNum, char[] vertexes, int[][] weight) {
        graph.vertexNum = vertexNum;
        //浅拷贝
        for (int i = 0; i < vertexNum; i++) {
            graph.vertexes[i] = vertexes[i];
            for (int j = 0; j < vertexNum; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] row : graph.weight) {
            System.out.println(Arrays.toString(row));
        }
    }

    //使用prim算法，从第v个节点开始进行访问
    public void prim(MGraph graph, int v) {
        boolean[] isVisited = new boolean[graph.vertexNum];
        //标记第一个节点已被访问
        isVisited[v] = true;
        //记录最小边的to节点下标
        int x = -1;
        int y = -1;
        int min = Integer.MAX_VALUE;

        //需要循环 graph.vertexNum-1 次，因为有 graph.vertexNum-1 条边
        for (int k = 1; k < graph.vertexNum; k++) {
            //这个是确定每一次生成的子图 ，和哪个结点的距离最近，就找到一个边：所有被访问节点与未访问节点中，寻找最短边
            for (int i = 0; i < graph.vertexNum; i++) {
                for (int j = 0; j < graph.vertexNum; j++) {
                    //这里i表示已访问节点，j表示未访问节点
                    if (isVisited[i] && !isVisited[j] && graph.weight[i][j] < min) {
                        min = graph.weight[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
            System.out.println("边<" + graph.vertexes[x] + "--" + graph.vertexes[y] + ">" + " 权值：" + graph.weight[x][y]);
            isVisited[y] = true;
            min = Integer.MAX_VALUE;
        }
    }
}

class MGraph {
    int vertexNum;//图节点的个数
    char[] vertexes;//存放节点
    int[][] weight;//存放边，邻接矩阵

    public MGraph(int nodeNum) {
        this.vertexNum = nodeNum;
        this.vertexes = new char[nodeNum];
        this.weight = new int[nodeNum][nodeNum];
    }
}