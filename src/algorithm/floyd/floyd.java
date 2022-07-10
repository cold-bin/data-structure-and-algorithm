package algorithm.floyd;

/*
 * 弗洛伊德算法解决最短路径问题
 * */
public class floyd {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //创建邻接矩阵
        int[][] matrix = new int[vertexes.length][vertexes.length];
        final int N = 65535;
        matrix[0] = new int[]{0, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, 0, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, 0, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, 0, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, 0, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, 0, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, 0};

        graph map = new graph(matrix, vertexes);
//        map.show();
        map.floyd();
        map.show();
//        map.getPath(map.getPosition('A'), map.getPosition('D'), map.pre);
        map.getAllPath();
    }
}

class graph {
    char[] vertexes;//顶点集合
    int[][] dis;//保存从各个顶点出发到各个顶点的距离
    int[][] pre;//保存到达目标顶点的前驱顶点

    public int getPosition(char ch) {
        for (int i = 0; i < this.vertexes.length; i++) {
            if (this.vertexes[i] == ch) return i;
        }
        return -1;
    }

    public graph(int[][] matrix, char[] vertexes) {
        this.vertexes = new char[vertexes.length];
        this.dis = new int[vertexes.length][vertexes.length];
        this.pre = new int[vertexes.length][vertexes.length];

        for (int i = 0; i < vertexes.length; i++) {
            this.vertexes[i] = vertexes[i];
            for (int j = 0; j < vertexes.length; j++) {
                this.dis[i][j] = matrix[i][j];
                this.pre[i][j] = i;
            }
        }
    }

    public void show() {
        for (int i = 0; i < this.vertexes.length; i++) {
            for (int j = 0; j < this.vertexes.length; j++) {
                System.out.print(this.vertexes[pre[i][j]] + " ");
            }
            System.out.println();
            for (int j = 0; j < this.vertexes.length; j++) {
                System.out.print("(" + this.vertexes[i] + "到" + this.vertexes[j] + "的最短距离" + dis[i][j] + ")");
            }
            System.out.println();
            System.out.println();
        }
    }

    public void floyd() {
        //求出最短路径长度
        //k为中间节点
        for (int k = 0; k < this.vertexes.length; k++) {
            //i为起点
            for (int i = 0; i < this.vertexes.length; i++) {
                //j为终点
                for (int j = 0; j < this.vertexes.length; j++) {
                    //遍历所有路径可能，将小的值（也就是更短的路径）进行覆盖
                    if (this.dis[i][k] + this.dis[k][j] < this.dis[i][j]) {
                        this.dis[i][j] = this.dis[i][k] + this.dis[k][j];//更新dis
                        this.pre[i][j] = this.pre[k][j];//更新前驱顶点
                    }
                }
            }
        }
    }

    public void getAllPath() {
        System.out.print("最短路径：");
        for (int i = 0; i < this.vertexes.length; i++) {
            for (int j = 0; j < this.vertexes.length; j++) {
                getPath(i, j, this.pre);
                System.out.println();
            }
        }
    }

    //求出最短路径的走法
    public void getPath(int i, int j, int[][] path) {

        if (path[i][j] == i) {
            if (i != j) System.out.print(this.vertexes[i] + " > " + this.vertexes[j]);
        } else {
            getPath(i, this.pre[i][j], path);
            System.out.print(" > " + this.vertexes[j]);
        }

    }

}