package recursion;

/*
 *   迷宫回溯
 * */
public class miGong {
    public static void main(String[] args) {
        //初始化地图，假设有墙的点值为1；没有墙，可以走的点的值为0；走过的点的值设置为2；已经探测过的点设置为3;
        //走迷宫的策略方法：下-》右-》上-》左；如果该点走不通则回溯;
        //当然策略也可以更换其他
        int[][] map = new int[8][7];
        //上下两行赋值为墙
        for (int j = 0; j < map[0].length; j++) {
            map[0][j] = 1;
            map[7][j] = 1;
        }
        //中间
        for (int i = 1; i < map.length - 1; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //内部墙
        map[3][1] = 1;
        map[3][2] = 1;
        //地图
        for (int[] arr : map) {
            for (int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);
        System.out.println();
        //地图
        for (int[] arr : map) {
            for (int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    //i,j表示从地图哪个位置出发，如果能到map[6][5],则说明通路找到
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路找到
            return true;
        } else {
            if (map[i][j] == 0) {//当前这个点还没有走过
                //假定可以走通，如果走不通会更改其值
                map[i][j] = 2;
                //按照策略走下一步:下（1）-》右（2）-》上（3）-》左（4）
                if (setWay(map, i + 1, j)) {//先走下
                    return true;
                } else if (setWay(map, i, j + 1)) {//下路走不通再走右路
                    return true;
                } else if (setWay(map, i - 1, j)) {//右路走不通再走上路
                    return true;
                } else if (setWay(map, i, j - 1)) {//上路走不通再走左路
                    return true;
                } else {//都走不通，回溯
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果该点不为0，为1 2 3时，表明该点可能为墙，或者已经走过，或者此点已经被探测过以后的路走不通
                return false;
            }
        }
    }
}
