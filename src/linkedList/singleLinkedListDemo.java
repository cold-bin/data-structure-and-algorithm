package linkedList;

public class singleLinkedListDemo {
    public static void main(String[] args) {
        heroNode h1 = new heroNode(1, "刘海斌");
        heroNode h2 = new heroNode(2, "包文杰");
        heroNode h3 = new heroNode(3, "王铁霖");
        heroNode h4 = new heroNode(4, "牟国柱");

        managerSingleList manager = new managerSingleList();

        manager.addNode(h1);
        manager.addNode(h2);
        manager.addNode(h3);
        manager.addNode(h4);
        manager.showList();
        manager.updateList("cold bin", 1);
        manager.insertNode(new heroNode(0, "无"), 1);
        manager.showList();
        manager.delNode(3);
        manager.showList();

        //单链表中节点的个数：遍历单链表有效节点个数（不算头节点）
        //查找单链表倒数第k个节点：先遍历单链表获取单链表节点个数l（不算头节点），然后再顺序遍历第（l-k+1）个节点
        //单链表反转：先定义一个反转之后的头节点；然后遍历原始链表，每遍历一个链表元素就将这个链表元素插进紧跟反转
        //链表的头节点之后的位置。然后再将原来头节点换成现在的头节点，意即，反转链表
        //逆序打印单链表：
        // 1、打印链表反转之后的链表，这样做的问题是会破坏单链表的结构；（不建议）
        // 2、栈，将各个节点压入栈中，打印时，从栈中取出
    }
}

class managerSingleList {
    //头节点
    private heroNode head;

    public managerSingleList() {
        this.head = new heroNode(0, "");
    }

    //添加
    public void addNode(heroNode node) {
        //遍历链表至最后一个节点
        heroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        //插入尾节点
        temp.next = node;
        node.next = null;
        System.out.println("添加成功");
    }

    //删除
    public void delNode(int no) {
        heroNode head = this.head;
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        heroNode temp = head;
        while (temp.next != null) {
            //记录要删除节点的前一个结点
            heroNode beforeNode = temp;
            temp = temp.next;
            if (temp.no == no) {
                //将删除节点的前一个节点next指向删除节点的下一个节点
                beforeNode.next = temp.next;
                System.out.println("删除成功");
                return;
            }
        }
        System.out.println("没有找到编号");
    }

    //插入：在编号之前
    public void insertNode(heroNode node, int no) {
        heroNode head = this.head;
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }

        heroNode temp = head;
        while (temp.next != null) {
            heroNode beforeNode = temp;
            temp = temp.next;
            if (temp.no == no) {
                //记录对应编号节点的前一个节点位置，将新节点插入这个位置，意即beforeNode之后，temp之前；
                beforeNode.next = node;
                node.next = temp;
                System.out.println("插入成功");
                return;
            }
        }
        System.out.println("没有找到编号");
    }

    //修改
    public void updateList(String newName, int no) {
        heroNode head = this.head;
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        heroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.no == no) {
                temp.name = newName;
                System.out.println("更新成功");
                return;
            }
        }
        System.out.println("没有找到编号");
    }
    //查询
    public void showList() {
        heroNode head = this.head;
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        heroNode temp = head;
        while (temp.next != null) {
            //头节点无数据，因此跳过
            temp = temp.next;
            System.out.println(temp);
        }
    }
}

class heroNode {
    int no;
    String name;
    heroNode next;

    public heroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "heroNode{" +
                "no=" + no +
                ", name=" + name +
                '}';
    }
}
