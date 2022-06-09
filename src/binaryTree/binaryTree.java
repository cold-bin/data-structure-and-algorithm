package binaryTree;

public class binaryTree {
    public static void main(String[] args) {
        treeManager treeManager = new treeManager(new treeNode(1, "宋江"));
        treeNode n2 = new treeNode(2, "吴用");
        treeNode n3 = new treeNode(3, "卢俊义");
        treeNode n4 = new treeNode(4, "林冲");
        treeManager.root.leftChildNode = n2;
        treeManager.root.rightChildNode = n3;
        n3.rightChildNode = n4;
        System.out.println("前序遍历：");
        treeManager.preShow();
        System.out.println();

        System.out.println("后序遍历：");
        treeManager.postShow();
        System.out.println();

        System.out.println("中序遍历：");
        treeManager.infixShow();

        System.out.println("插入节点后");
        treeNode n5 = new treeNode(5, "关胜");
        treeManager.add(n5);

        System.out.println("前序遍历：");
        treeManager.preShow();
        System.out.println();

        System.out.println("后序遍历：");
        treeManager.postShow();
        System.out.println();

        System.out.println("中序遍历：");
        treeManager.infixShow();

    }
}

class treeManager {
    treeNode root;

    public treeManager(treeNode root) {
        this.root = root;
    }

    //添加节点
    public void add(treeNode node) {
        root.add(node);
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void preShow() {
        if (isEmpty()) {
            System.out.println("树为空");
            return;
        }
        root.preShow();
    }

    public void infixShow() {
        if (isEmpty()) {
            System.out.println("树为空");
            return;
        }
        root.infixShow();
    }

    public void postShow() {
        if (isEmpty()) {
            System.out.println("树为空");
            return;
        }
        root.postShow();
    }
}

class treeNode {
    int no;
    String name;

    treeNode leftChildNode;
    treeNode rightChildNode;

    public treeNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "treeNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
    //前序遍历

    public void add(treeNode node) {
        if (this.no == 3) {
            this.leftChildNode = node;
        }
        if (this.leftChildNode != null) {
            this.leftChildNode.add(node);
        }
        if (this.rightChildNode != null) {
            this.rightChildNode.add(node);
        }
    }

    public void preShow() {

        System.out.println(this);
        if (this.leftChildNode != null) {
            this.leftChildNode.preShow();
        }
        if (this.rightChildNode != null) {
            this.rightChildNode.preShow();
        }
    }

    //中序遍历
    public void infixShow() {
        if (this.leftChildNode != null) {
            this.leftChildNode.infixShow();
        }
        System.out.println(this);
        if (this.rightChildNode != null) {
            this.rightChildNode.infixShow();
        }
    }

    //后序遍历
    public void postShow() {
        if (this.leftChildNode != null) {
            this.leftChildNode.postShow();
        }
        if (this.rightChildNode != null) {
            this.rightChildNode.postShow();
        }
        System.out.println(this);
    }
}
