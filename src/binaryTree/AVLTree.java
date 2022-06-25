package binaryTree;

import java.util.Map;

/*
 * 平衡二叉树（AVL）
 * */
public class AVLTree {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};//rr：处理方式左旋转
//        int[] arr = {10, 12, 8, 9, 7, 6};//ll：处理方式右旋转
//        int[] arr = {10, 11, 7, 6, 8, 9};//lr：处理方式先左旋再右旋
        int[] arr = {2,1,6,5,7,3};//rl：处理方式先右旋再左旋

        AVLTreeManager manager = new AVLTreeManager();
        for (int val : arr) {
            manager.add(new node3(val));
        }

        System.out.println("中序遍历：");
        manager.infixShow();

        System.out.println("avl调整之后：");
        System.out.println("整棵树的高度：" + manager.root.height());
        System.out.println("左子树高度：" + manager.root.leftHeight());
        System.out.println("右子树高度：" + manager.root.rightHeight());
        System.out.println();
    }
}

class AVLTreeManager {
    node3 root;

    public void showRoot() {
        System.out.println(root);
    }

    public void add(node3 node) {
        if (root == null) root = node;
        else root.add(node);
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        //找到删除节点
        node3 targetNode = root.search(value);
        if (targetNode == null) {
            System.out.println("没有找到该节点");
            return;
        }
        //找到删除节点的父节点
        node3 parent = root.searchParent(value);

        if (targetNode.left == null && targetNode.right == null) {// 如果要删除的节点是叶子节点
            if (parent == null) {//判断父节点是否为空
                root = null;
                return;
            }
            if (parent.left != null && parent.left.val == value) {
                parent.left = null;
            } else if (parent.right != null && parent.right.val == value) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            //左右子树均不为空，按中序遍历顺序，将删除节点的前驱节点移至删除节点并代替或者后继节点移至删除节点代替，
            //也就是说：将前驱节点--》删除节点的左子树里最大的节点；后继节点--》删除节点右子树里最小的节点（右子树里最左子树节点）
            node3 temp = targetNode.right;
            node3 tempParent = targetNode;
            while (temp.left != null) {//找到最左子节点，也就是右子树最小的节点，即后继节点
                tempParent = temp;
                temp = temp.left;
            }
            //将后继节点的值覆盖到删除节点
            targetNode.val = temp.val;
            //再将原来的后继节点删除（注意：需要防止最小节点是右子树根节点的情况）
            if (tempParent == targetNode) {
                tempParent.right = null;
            } else {
                tempParent.left = null;
            }
        } else {//如果删除的节点有一个子节点
            if (targetNode.left != null) {
                if (parent == null) {
                    root = targetNode.left;
                    return;
                }
                if (parent.left == targetNode) parent.left = targetNode.left;
                else if (parent.right == targetNode) parent.right = targetNode.left;
            } else {
                if (parent == null) {
                    root = targetNode.right;
                    return;
                }
                if (parent.left == targetNode) parent.left = targetNode.right;
                else if (parent.right == targetNode) parent.right = targetNode.right;
            }
        }
    }

    public void infixShow() {
        if (root == null) {
            System.out.println("树位空");
            return;
        }
        root.infixShow();
    }
}

class node3 {
    int val;
    node3 left;
    node3 right;

    //左旋转
    public void leftRotate() {
        //创建当前根节点的副本
        node3 node = new node3(this.val);
        //将副本左子节点指向当前节点的左子节点
        node.left = this.left;
        //副本右子节点指向当前节点的右子节点的左子节点
        node.right = this.right.left;
        //将当前节点的值换成右子节点的值
        this.val = this.right.val;
        //将新的当前节点的左子节点指向副本节点
        this.left = node;
        //将新的当前节点的右子树的右子树连接
        this.right = this.right.right;
    }

    //右旋转
    public void rightRotate() {
        node3 node = new node3(this.val);
        node.left = this.left.right;
        node.right = this.right;
        this.val = left.val;
        this.right = node;
        this.left = this.left.left;
    }

    //当前节点左子树高度
    public int leftHeight() {
        if (this.left == null) return 0;
        return this.left.height();
    }

    //当前节点右子树高度
    public int rightHeight() {
        if (this.right == null) return 0;
        return this.right.height();
    }

    //返回当前节点为跟节点的树的高度
    public int height() {//递归查找，每递归一次树的深度+1
        return Math.max((this.left == null ? 0 : this.left.height()), this.right == null ? 0 : this.right.height()) + 1;
    }

    //查找节点
    public node3 search(int val) {
        if (this.val == val) {
            return this;
        } else if (this.val > val) {
            if (this.left == null) return null;
            return this.left.search(val);
        } else {
            if (this.right == null) return null;
            return this.right.search(val);
        }
    }

    //查找父节点
    public node3 searchParent(int val) {
        if ((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)) {
            return this;
        } else {
            if (this.val > val && this.left != null) {//当前节点的值小于查找节点，往左子树递归
                return this.left.searchParent(val);
            } else if (this.val <= val && this.right != null) {//当前节点的值大于查找节点，往右子树递归(注意相同情况)
                return this.right.searchParent(val);
            } else return null;
        }
    }

    //添加二叉排序树的节点
    public void add(node3 node) {
        if (node == null) return;
        if (this.val > node.val) {//添加节点比当前节点小，向左子树处理
            if (this.left == null) this.left = node;
                //向左子树递归
            else this.left.add(node);
        } else {//添加节点比当前节点大，向右子树处理
            if (this.right == null) this.right = node;
                //向右子树递归
            else this.right.add(node);
        }
        //节点添加完毕，需要判断平衡因子
        if (this.rightHeight() - this.leftHeight() > 1) {//右子树比左子树高，触发左旋转
            //如果右子节点的左子树高度大于右子节点的右子树高度，先局部右旋转：当前节点的右子节点
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                this.right.rightRotate();
            }
            this.leftRotate();
        } else if (this.leftHeight() - this.rightHeight() > 1) {//左子树比右子树高，触发右旋转
            //如果左子节点的右子树高度大于左子节点的左子树高度，先局部左旋转：当前节点的左子节点
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                this.left.leftRotate();
            }
            this.rightRotate();
        }
    }

    //中序遍历：根据二叉排序树的特点：根节点小于右子节点，大于左子节点。使用中序遍历的到的结果是升序结果
    public void infixShow() {
        if (this.left != null) {
            this.left.infixShow();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixShow();
        }
    }

    public node3(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "node{" +
                "val=" + val +
                '}';
    }
}
