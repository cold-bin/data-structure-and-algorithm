package linkedList;

public class doublyLinkedListDemo {
    public static void main(String[] args) {
        heroNode1 h1 = new heroNode1(1, "刘海斌");
        heroNode1 h2 = new heroNode1(2, "包文杰");
        heroNode1 h3 = new heroNode1(3, "王铁霖");
        heroNode1 h4 = new heroNode1(4, "牟国柱");

        managerHeroNode1 m =new managerHeroNode1();
        m.addNode(h1);
        m.addNode(h2);
        m.addNode(h3);
        m.addNode(h4);

        m.listNode();

        m.updateNode(new heroNode1(2,"小包"));
        m.insertNode(new heroNode1(3,"邓涔浩"));
        m.deleteNode(4);

        m.listNode();
    }
}

class managerHeroNode1 {
    heroNode1 head;
    public managerHeroNode1() {
        this.head = new heroNode1(-1, "");
    }
    public boolean isEmpty() {
        if (head.next != null) return false;
        else return true;
    }
    //末尾添加node
    public void addNode(heroNode1 node) {
        heroNode1 temp = head;
        //遍历到末尾
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
        System.out.println("成功添加");
    }
    //修改节点
    public void updateNode(heroNode1 node) {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        heroNode1 temp = head;
        while (temp.no != node.no) {
            temp = temp.next;
            if (temp == null) {
                System.out.println("没找到该节点");
                return;
            }
        }
        temp.name = node.name;
        System.out.println("修改成功:" + temp);
    }
    //删除节点
    public void deleteNode(int no) {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }

        heroNode1 temp = head;
        while (temp.no != no) {
            temp = temp.next;
        }
        //temp的下一个节点的pre指针指向temp的上一个节点
//        temp.next.pre=temp.pre;//注意如果temp是最后一个节点，这里有错：
        if (temp.next != null) temp.next.pre = temp.pre;
        //temp的上一个节点的next指针指向temp的下一个节点
        temp.pre.next = temp.next;
        System.out.println("删除成功:" + temp);
    }
    //按序插入节点
    public void insertNode(heroNode1 node) {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        heroNode1 temp = head;
        while (temp.no <= node.no) {
            temp = temp.next;
            //如果插入节点在最后，直接添加
            if (temp==null){
                addNode(node);
                return;
            }
        }
        //此操作注意先后顺序，否则会因为链条一部分断裂而导致后面的节点变成null
        //首先
        node.pre=temp;
        node.next=temp.next;
        temp.next=node;
        temp.next.pre=node;
        System.out.println("插入成功:" + node);
    }
    //遍历链表
    public void listNode() {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        heroNode1 temp = head;
        while (temp.next != null) {
            temp = temp.next;
            System.out.println(temp);
        }
    }
}
class heroNode1 {
    //节点data属性
    int no;
    String name;
    //指针域
    heroNode1 pre;
    heroNode1 next;
    public heroNode1(int no, String name) {
        this.no = no;
        this.name = name;
    }
    @Override
    public String toString() {
        return "heroNode1{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}