package binaryTree;

public class threadedBinaryTree {
    public static void main(String[] args) {
        treeNode1 n1 = new treeNode1(1, "宋江");
        treeManager1 treeManager = new treeManager1(n1);
        treeNode1 n2 = new treeNode1(3, "吴用");
        treeNode1 n3 = new treeNode1(6, "卢俊义");
        treeNode1 n4 = new treeNode1(8, "林冲");
        treeNode1 n5 = new treeNode1(10, "sda");
        treeNode1 n6 = new treeNode1(14, "fsd");
        treeManager.root.leftChildNode = n2;
        treeManager.root.rightChildNode = n3;
        n2.leftChildNode = n4;
        n2.rightChildNode = n5;
        n3.leftChildNode = n6;

        //记录父节点
        n2.parentNode = n1;
        n3.parentNode = n1;
        n4.parentNode = n2;
        n5.parentNode = n2;
        n6.parentNode = n3;

        System.out.println("前序遍历:");
        treeManager.preShow();

        System.out.println("中序遍历:");
        treeManager.infixShow();

        System.out.println("后序遍历:");
        treeManager.postShow();

        //注意线索化的二叉树不能再随意的使用前序中序后序的遍历方法，可能会陷入死循环，由于不断递归导致爆栈
        threadedTree tdBinaryTree = new threadedTree();

//        tdBinaryTree.infixThreadOrder(n1);
//        System.out.println("中序线索化遍历：");
//        tdBinaryTree.infixThreadList(n1);

        tdBinaryTree.preThreadOrder(n1);
        System.out.println("前序线索化遍历：");
        tdBinaryTree.preThreadList(n1);

//        tdBinaryTree.postThreadOrder(n1);
//        System.out.println("后序线索化遍历：");
//        tdBinaryTree.postThreadList(n1);

//        System.out.println("10号节点的前驱节点是：");
//        System.out.println(n5.leftChildNode);
    }
}

//线索化二叉树
class threadedTree {
    treeNode1 preNode;    //记录前一个节点

    //前序线索化
    public void preThreadOrder(treeNode1 node) {
        if (node == null) return;
        if (node.leftChildNode == null) {
            node.leftChildNode = this.preNode;
            node.leftTag = 1;
        }
        //右子树为空,指向后继节点:将前一个节点记录下来,当前节点node是preNode的后继节点,因此将后继节点指向preNode的空右子节点
        if (this.preNode != null && this.preNode.rightChildNode == null) {
            this.preNode.rightChildNode = node;
            this.preNode.rightTag = 1;
        }
        this.preNode = node;//记录下一个节点
        if (node.leftTag == 0) preThreadOrder(node.leftChildNode);//防止陷入无限循环
        if (node.rightTag == 0) preThreadOrder(node.rightChildNode);
    }

    //前序遍历线索化二叉树
    public void preThreadList(treeNode1 root) {
        treeNode1 node = root;
        if (node == null) return;

        while (node != null) {
            //一直沿左子树遍历，直到遇到线索
            while (node.leftTag == 0) {
                System.out.println(node);
                node = node.leftChildNode;
            }
            System.out.println(node);
            //此时已遍历一颗子树的根节点和左子树
            //再遍历其后继节点
            while (node.rightTag==1) {
                node = node.rightChildNode;
                System.out.println(node);
            }
            //遇到当前节点无线索时，表示此时的节点有左右子树，左边已经遍历，该遍历右子树
            node=node.rightChildNode;
        }
    }

    //中序线索化
    public void infixThreadOrder(treeNode1 node) {
        //递归结束
        if (node == null) return;
        //递归左子树
        infixThreadOrder(node.leftChildNode);
        //将当前节点进行线索化
        //当左子树为空,指向前驱节点
        if (node.leftChildNode == null) {
            node.leftChildNode = this.preNode;
            node.leftTag = 1;
        }
        //右子树为空,指向后继节点:将前一个节点记录下来,当前节点node是preNode的后继节点,因此将后继节点指向preNode的空右子节点
        if (this.preNode != null && this.preNode.rightChildNode == null) {
            this.preNode.rightChildNode = node;
            this.preNode.rightTag = 1;
        }
        this.preNode = node;
        //递归右子树
        infixThreadOrder(node.rightChildNode);
    }

    //中序遍历线索化二叉树：线索化的二叉树其实质就是一个双向链表，当然一般都是先从左边开始遍历，这样才满足二叉树
    //如果是前序线索化，则遍历拿到的顺序是二叉树前序遍历的结果，这种方式不需要使用递归，递归比循环要慢一点
    public void infixThreadList(treeNode1 root) {
        treeNode1 node = root;
        while (node != null) {
            //找到遍历开始节点,中序遍历是最后一个左子节点
            while (node.leftTag == 0) node = node.leftChildNode;
            //输出当前节点
            System.out.println(node);
            //不断找寻后继节点
            while (node.rightTag == 1) {
                node = node.rightChildNode;//获取后继节点
                System.out.println(node);
            }
            //替换这个遍历的节点为前驱
            node = node.rightChildNode;
        }
    }

    //后序线索化
    public void postThreadOrder(treeNode1 node) {
        if (node == null) return;

        if (node.leftTag == 0) postThreadOrder(node.leftChildNode);
        if (node.rightTag == 0) postThreadOrder(node.rightChildNode);

        if (node.leftChildNode == null) {
            node.leftChildNode = this.preNode;
            node.leftTag = 1;
        }
        if (this.preNode != null && this.preNode.rightChildNode == null) {
            this.preNode.rightChildNode = node;
            this.preNode.rightTag = 1;
        }
        this.preNode = node;
    }

    public void postThreadList(treeNode1 root) {
        treeNode1 node = root;
        if (node == null) return;
        //遍历找到最左子节点，从此处开始遍历
        while (node != null && node.leftTag == 0) node = node.leftChildNode;
        //暂存上一个节点
        treeNode1 preNode = null;

        while (node != null) {
            //找到后序遍历输出的头节点时，不断输出后继节点
            while (node.rightTag == 1) {
                System.out.println(node);
                preNode = node;//记录上一个遍历的节点
                node = node.rightChildNode;
            }
            //如果上一个处理的节点当前节点的右子节点，则说明左右子树处理完毕，继续处理父节点
            while (node.rightChildNode == preNode) {
                System.out.println(node);
                if (node.parentNode == null) return;
                preNode = node;
                node = node.parentNode;
            }
            //如果上一个处理的节点是当前节点的左子节点
            if (node.leftChildNode == preNode) {
                //找到右子树的最左子节点
                node = node.rightChildNode;
                while (node != null && node.leftTag == 0) node = node.leftChildNode;
            }
        }
    }
}


class treeManager1 {
    treeNode1 root;

    public treeManager1(treeNode1 root) {
        this.root = root;
    }

    //添加节点
    public void add(treeNode1 node) {
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

    public treeNode1 preSearch(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return null;
        }
        return root.preSearch(no);
    }

    public treeNode1 infixSearch(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return null;
        }
        return root.infixSearch(no);
    }

    public treeNode1 postSearch(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return null;
        }
        return root.postSearch(no);
    }

    //删除
    public boolean delete(int no) {
        if (isEmpty()) {
            System.out.println("树为空");
            return false;
        }
        //如果只有root根节点，则直接删除
        if (root.leftChildNode == null && root.rightChildNode == null && root.no == no) {
            root = null;
            return true;
        }
        return root.delete(no);
    }


}

class treeNode1 {
    int no;
    String name;
    int leftTag;//当为0时，表示左子树不为空，为1时，左子树为空，应该指向前驱节点
    int rightTag;//当为0，表示右子树不为空，为1时右子树为空，应该指向后继节点
    treeNode1 leftChildNode;
    treeNode1 rightChildNode;

    treeNode1 parentNode;//记录此时节点的父节点（用于遍历后序线索化二叉树。只有知道当前节点的父节点才能进行）

    public treeNode1(int no, String name) {
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

    //添加指定节点
    public void add(treeNode1 node) {
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

    //前序遍历
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

    //前序查找
    public treeNode1 preSearch(int no) {
        System.out.println("进入前序查找");
        //如果当前节点满足，则返回当前节点
        if (this.no == no) {
            return this;
        }
        treeNode1 node = null;
        //再向左子树递归
        if (this.leftChildNode != null) {
            node = this.leftChildNode.preSearch(no);
        }
        //先校验左子树递归结果，不然会被右子树的结果给覆盖掉
        if (node != null) {
            return node;
        }
        //再向右子树递归
        if (this.rightChildNode != null) {
            node = this.rightChildNode.preSearch(no);
        }
        return node;
    }

    //中序查找
    public treeNode1 infixSearch(int no) {
        treeNode1 node = null;

        if (this.leftChildNode != null) {
            node = this.leftChildNode.infixSearch(no);
        }
        //如果已找到提前返回
        if (node != null) {
            return node;
        }
        System.out.println("进入中序查找");
        if (this.no == no) {
            return this;
        }

        if (this.rightChildNode != null) {
            node = this.rightChildNode.infixSearch(no);
        }
        return node;
    }

    //后续查找
    public treeNode1 postSearch(int no) {
        treeNode1 node = null;
        if (this.leftChildNode != null) {
            node = this.leftChildNode.postSearch(no);
        }
        //左子节点找到要返回，否则会被右子节点覆盖掉
        if (node != null) {
            return node;
        }

        if (this.rightChildNode != null) {
            node = this.rightChildNode.postSearch(no);
        }
        //右子节点不为空，需要提前返回
        if (node != null) {
            return node;
        }

        System.out.println("进入后续查找");
        if (this.no == no) {
            return this;
        }
        return null;
    }

    //删除节点或节点及其子树
    public boolean delete(int no) {
        boolean res = false;
        //
        if (this.leftChildNode != null && this.leftChildNode.no == no) {
            this.leftChildNode = null;
            return true;
        }
        if (this.rightChildNode != null && this.rightChildNode.no == no) {
            this.rightChildNode = null;
            return true;
        }
        if (this.leftChildNode != null) {
            res = this.leftChildNode.delete(no);
        }
        //提前返回结果，避免左子树覆盖
        if (res) {
            return true;
        }
        if (this.rightChildNode != null) {
            res = this.rightChildNode.delete(no);
        }
        return res;
    }

    //只删除节点，不删除子树
    public boolean delete2(int no) {
        boolean res = false;
        if (this.leftChildNode != null && this.leftChildNode.no == no) {
            //判断删除节点是否含有子树
            if (this.leftChildNode.leftChildNode == null && this.leftChildNode.rightChildNode == null) {
                //无子树，删除当前节点
                this.leftChildNode = null;
            } else if (this.leftChildNode.leftChildNode != null && this.leftChildNode.rightChildNode == null) {
                //有左子树，无右子树，左子树代替删除节点
                this.leftChildNode = this.leftChildNode.leftChildNode;
            } else if (this.leftChildNode.leftChildNode == null && this.leftChildNode.rightChildNode != null) {
                //无左子树，有右子树，右子树代替删除节点
                this.leftChildNode = this.leftChildNode.rightChildNode;
            } else {
                //左右子树都有，左子树节点代替删除节点
                this.leftChildNode = this.leftChildNode.leftChildNode;
            }
            return true;
        }
        if (this.rightChildNode != null && this.rightChildNode.no == no) {
            //判断删除节点是否含有子树
            if (this.rightChildNode.leftChildNode == null && this.rightChildNode.rightChildNode == null) {
                //无子树，删除当前节点
                this.rightChildNode = null;
            } else if (this.rightChildNode.leftChildNode != null && this.rightChildNode.rightChildNode == null) {
                //有左子树，无右子树，左子树代替删除节点
                this.rightChildNode = this.rightChildNode.leftChildNode;
            } else if (this.rightChildNode.leftChildNode == null && this.rightChildNode.rightChildNode != null) {
                //无左子树，有右子树，右子树代替删除节点
                this.rightChildNode = this.rightChildNode.rightChildNode;
            } else {
                //左右子树都有，左子树节点代替删除节点
                this.rightChildNode = this.rightChildNode.leftChildNode;
            }
            return true;
        }
        if (this.leftChildNode != null) {
            res = this.leftChildNode.delete(no);
        }
        //提前返回结果，避免左子树覆盖
        if (res) {
            return true;
        }
        if (this.rightChildNode != null) {
            res = this.rightChildNode.delete(no);
        }
        return res;
    }
}
