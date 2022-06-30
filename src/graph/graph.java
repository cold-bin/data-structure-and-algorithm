package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/*
 * 图
 * */
public class graph {
    private final ArrayList<String> vertexList;//存储顶点集合
    private final int[][] edges;//表示图对应的邻接矩阵
    private final int numOfEdge;//表示边的数目
//    private final boolean[] isVisited;//记录某个节点是否被访问

    public static void main(String[] args) {
        int n = 8;
//        String[] vertexes = {"A", "B", "C", "D", "E"};
        String[] vertexes = {"1", "2", "3", "4", "5", "6", "7", "8"};
        graph g = new graph(n);
        //添加顶点
        for (String v : vertexes) {
            g.insertVertex(v);
        }
        //添加边
//        g.insertEdge(0, 1, 1);
//        g.insertEdge(0, 2, 1);
//        g.insertEdge(1, 2, 1);
//        g.insertEdge(1, 3, 1);
//        g.insertEdge(1, 4, 1);
        g.insertEdge(0, 1, 1);
        g.insertEdge(0, 2, 1);
        g.insertEdge(1, 3, 1);
        g.insertEdge(1, 4, 1);
        g.insertEdge(3, 7, 1);
        g.insertEdge(4, 7, 1);
        g.insertEdge(2, 5, 1);
        g.insertEdge(2, 6, 1);
        g.insertEdge(5, 6, 1);


        g.show();

        System.out.println("深度优先：");//right: 1->2->4->8->5->3->6->7->
        g.DFSTraverse();
        System.out.println();

        System.out.println("广度优先：");//right: 1->2->3->4->8->5->6->7->
        g.BFSTraverse();
    }

    public graph(int n) {
        this.edges = new int[n][n];
        this.numOfEdge = 0;
        this.vertexList = new ArrayList<>(n);
    }

    //BFS
    public void BFSTraverse() {
        boolean[] isVisited = new boolean[this.vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                BFS(isVisited, i);
            }
        }
    }

    //BFS
    public void BFS(boolean[] isVisited, int i) {
        //表示队列头节点对应下标
        int u;
        //队列记录当前顶点
        LinkedList<Integer> queue = new LinkedList<>();
        //访问节点
        System.out.print(getValueByIndex(i) + "->");
        //标记访问
        isVisited[i] = true;
        //节点入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {//如果当前队列不为空
            u = queue.removeFirst();//队头元素出队列，接下来将以这个出队列的元素为起点访问邻接点
            //遍历跳过0点，直接遍历含有边的矩阵点
            for (int j = getFirstNeighbor(u); j > 0; j = getNextNeighbor(u, j)) {
                if (!isVisited[j]) { //判断其它顶点与当前顶点是否有边且是否访问过
                    isVisited[j] = true;    //标记符合条件的顶点状态为已访问
                    System.out.print(getValueByIndex(j) + "->"); //打印顶点
                    queue.addLast(j);   //此顶点入队列
                }
            }
        }
    }

    //对图进行深度优先遍历
    public void DFSTraverse() {
        boolean[] isVisited = new boolean[this.vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                DFS(isVisited, i);
            }
        }
    }

    //深度优先遍历（DFS）
    public void DFS(boolean[] isVisited, int i) {
        //访问节点
        System.out.print(getValueByIndex(i) + "->");
        //记录访问
        isVisited[i] = true;
        //查找当前节点的第一个邻接节点下标
        int w = getFirstNeighbor(i);
        //如果存在
        while (w != -1) {
            if (!isVisited[w]) {//且未被访问，就递归访问
                DFS(isVisited, w);
            } else {//已被访问，访问它的下一个邻接结点（往深处走）
                w = getNextNeighbor(i, w);
            }
        }

    }

    //返回第一个邻接节点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < this.vertexList.size(); i++) {
            if (this.edges[index][i] > 0) return i;
        }
        return -1;
    }

    //返回下一个邻接节点
    public int getNextNeighbor(int index1, int index2) {
        //index2表示已经访问的节点小标
        for (int i = index2 + 1; i < this.vertexList.size(); i++) {
            if (this.edges[index1][i] > 0) return i;
        }
        return -1;
    }

    //返回节点的个数
    public int getNumOfVertex() {
        return this.vertexList.size();
    }

    //返回边的个数
    public int getNumOfEdge() {
        return this.numOfEdge;
    }

    //返回节点对应的数据
    public String getValueByIndex(int index) {
        return this.vertexList.get(index);
    }

    //返回权值
    public int getWeight(int index1, int index2) {
        return this.edges[index1][index2];
    }

    //插入节点
    public void insertVertex(String vertex) {
        this.vertexList.add(vertex);
    }

    //添加边
    public void insertEdge(int index1, int index2, int weight) {
        this.edges[index1][index2] = weight;
        this.edges[index2][index1] = weight;
    }

    //显示图对应的矩阵
    public void show() {
        for (int[] v1 : this.edges) {
            System.out.println(Arrays.toString(v1));
        }
    }
}
