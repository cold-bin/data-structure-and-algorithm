package hashTable;
/*
*   哈希表
* */
public class hashTable {
    public static void main(String[] args) {
        hashTableManager hashTableManager = new hashTableManager(10);
        hashTableManager.add(new node(114, "cold bin", "重庆"));
        hashTableManager.add(new node(224, "wtl", "重庆"));
        hashTableManager.add(new node(334, "sss", "ss"));
        hashTableManager.add(new node(3, "bwj", "重庆"));
        hashTableManager.show();
        hashTableManager.delete(224);
        hashTableManager.show();
        hashTableManager.update(new node(334, "aaa", ""));
        hashTableManager.show();
        node node = hashTableManager.findNode(224);
        if (node != null) {
            System.out.println("找到节点" + node);
        }
    }
}

class hashTableManager {
    int size;//哈希表的数组长度
    nodeManager[] hashTable;//哈希表的内存结构

    public hashTableManager(int size) {
        this.size = size;
        hashTable = new nodeManager[size];

        //实例化数组里的对象
        for (int i = 0; i < size; i++) {
            nodeManager nodeManager = new nodeManager(20);
            hashTable[i] = nodeManager;
        }

    }

    //散列函数
    public int hashFunc(int id) {
        return id % this.size;
    }

    //添加
    public void add(node node) {
        int nodeManagerNo = hashFunc(node.id);
        this.hashTable[nodeManagerNo].add(node);
    }

    public void delete(int id) {
        int nodeManagerNo = hashFunc(id);
        this.hashTable[nodeManagerNo].delete(id);
    }

    public void update(node node) {
        int nodeManagerNo = hashFunc(node.id);
        this.hashTable[nodeManagerNo].update(node);
    }

    //查询id对应的员工信息
    public node findNode(int id) {
        int nodeManagerNo = hashFunc(id);
        return this.hashTable[nodeManagerNo].findNode(id);
    }

    //遍历
    public void show() {
        for (int i = 0; i < this.size; i++) {
            if (!this.hashTable[i].isEmpty()) {
                System.out.print("哈希表第" + (i + 1) + "行:");
                this.hashTable[i].show();
            } else {
                System.out.println("哈希表第" + (i + 1) + "行为空");
            }
        }
    }
}

class nodeManager {
    int maxSize;//节点最大容量
    node head;//带数据的头节点

    //初始化空的带头节点链表
    public nodeManager(int maxSize) {
        this.head = new node(-1);
        this.maxSize = maxSize;
    }

    public boolean isEmpty() {
        return head.next == null;
    }

    public boolean isFull() {
        int num = 0;
        node temp = head;
        while (temp.next != null) {
            num++;
            temp = temp.next;
        }
        return num == maxSize - 1;
    }

    //增：按照id递增插入
    public void add(node node) {
        if (isFull()) {
            System.out.println("链表已满");
            return;
        }
        node temp = head;
        while (temp != null) {
            node beforeNode = temp;
            if (temp.next == null) {
                temp.next = node;
                return;
            }
            temp = temp.next;
            if (temp.id > node.id) {
                //记录对应编号节点的前一个节点位置，将新节点插入这个位置，意即beforeNode之后，temp之前；
                beforeNode.next = node;
                node.next = temp;
                System.out.println("插入成功");
                return;
            }
        }
        System.out.println("没有找到编号");
    }

    //删
    public void delete(int id) {
        if (isEmpty()) {
            System.out.println("链表已空");
            return;
        }
        node temp = head.next;
        while (temp != null) {
            node preNode = temp;
            temp = temp.next;
            if (temp.id == id) {
                preNode.next = temp.next;
                System.out.println("删除成功");
                return;
            }
        }
        System.out.println("删除失败，没有该id");
    }

    //改
    public void update(node node) {
        if (isEmpty()) {
            System.out.println("链表已空");
            return;
        }
        node temp = head.next;
        while (temp != null) {
            temp = temp.next;
            if (temp.id == node.id) {
                temp.name = node.name;
                temp.address = node.address;
                System.out.println("更新成功");
                return;
            }
        }
        System.out.println("更新失败，没有该id");
    }

    //查找节点
    public node findNode(int id) {
        if (isEmpty()) {
            System.out.println("链表为空");
            return null;
        }
        node temp = head;
        while (temp != null && temp.id != id) {
            temp = temp.next;
        }
        if (temp != null) {
            return temp;
        } else {
            System.out.println("没有该id");
            return null;
        }
    }

    //查
    public void show() {
        if (isEmpty()) {
            System.out.println("链表为空");
            return;
        }
        node temp = head.next;
        System.out.print("链表：");
        while (temp != null) {
            System.out.print(temp);
            temp = temp.next;
        }
        System.out.println();
    }
}

class node {
    int id;
    String name;
    String address;
    node next;

    public node(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public node(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
