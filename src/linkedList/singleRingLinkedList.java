package linkedList;

public class singleRingLinkedList {
    public static void main(String[] args) {
        managerList m = new managerList();
        m.addNode(41);
        m.list();
        System.out.println();
        m.pops(1, 3, 5);
    }
}

class managerList {
    boyNode head;

    public managerList() {
        //头节点，标记首位置
        head = new boyNode(-1);
        head.next = head;//注意闭环
    }

    //添加
    public void addNode(int num) {
        if (num < 1) {
            System.out.println("参数错误");
            return;
        }
        boyNode curNode = head;
        for (int i = 1; i <= num; i++) {
            boyNode node = new boyNode(i);
            if (i == 1) {
                head.no = node.no;
                continue;
            }
            curNode.next = node;
            node.next = head;
            //记录当前节点
            curNode = node;
        }
        System.out.println("添加成功");
    }

    //遍历链表
    public void list() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        boyNode curNode = head;
        while (curNode.next != head) {
            System.out.println(curNode);
            curNode = curNode.next;
        }
        System.out.println(curNode);
    }

    //删除:返回下一个节点的编号
    public void pops(int startNo, int countNum, int nums) {
        //helper指针指向链表尾节点
        boyNode helper = head;
        while (helper.next != head) {
            helper = helper.next;
        }
        //移动到报数节点
        for (int i = 1; i < startNo; i++) {
            helper = helper.next;
            head = head.next;
        }
        while (head != helper) {
            //报数移动countNum-1次
            for (int i = 1; i < countNum; i++) {
                helper = helper.next;
                head = head.next;
            }
            int number = head.no;
            head = head.next;
            helper.next = head;
            System.out.println("移除节点：" + number);
        }
        System.out.println("最后的节点：" + head.no);
    }
}

class boyNode {
    int no;//编号
    boyNode next;//指针域

    public boyNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "boyNode{" +
                "no=" + no +
                '}';
    }
}