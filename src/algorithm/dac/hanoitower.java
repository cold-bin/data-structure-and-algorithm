package algorithm.dac;
/*
* 汉诺塔
* */
public class hanoitower {
    public static void main(String[] args) {
        move(5, 'A', 'B', 'C');
    }

    //将第num个盘从a移到c处，中间借助b
    public static void move(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            return;
        }
        //先将最上面的所有盘，从a移动b
        move(num - 1, a, c, b);
        //把最下边的盘，从a移动到c
        System.out.println("第" + num + "个盘从 " + a + "->" + c);
//        move(1,a,b,c);
        //再把b塔所有盘，从b移动至c，借助a塔
        move(num - 1, b, a, c);

    }
}