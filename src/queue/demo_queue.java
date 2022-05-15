package queue;

import java.util.Scanner;

public class demo_queue {
    public static void main(String[] args) {
        queue q = new queue(5);
        boolean isContinued = true;
        Scanner sc = new Scanner(System.in);
        while (isContinued) {
            System.out.println("请输入：1：增加队列元素；2：取出元素；3：遍历队列");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    q.addQueue(sc.nextInt());
                    break;
                case 2:
                    int e = q.getQueue();
                    System.out.println(e);
                    break;
                case 3:
                    q.list();
                    break;
                default:
                    System.out.println("the inout is wrong");
            }

            System.out.println("请输入1继续、2退出");
            // 1:继续下一次;2:退出循环

            int input = sc.nextInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    isContinued = false;
                    break;
                default:
                    input = 1;
                    System.out.println("the inout is wrong");
            }
        }
    }
}
