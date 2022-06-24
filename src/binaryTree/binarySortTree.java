package binaryTree;

/*
 * 二叉排序树
 * */
public class binarySortTree {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTreeManager binarySortTreeManager = new BinarySortTreeManager();
        for (int val : arr) {
            binarySortTreeManager.add(new node2(val));
        }
        binarySortTreeManager.infixShow();

        binarySortTreeManager.delNode(2);
        System.out.println("删除过后：");
        binarySortTreeManager.infixShow();
        System.out.println();

        binarySortTreeManager.delNode(9);
        binarySortTreeManager.delNode(5);
        binarySortTreeManager.delNode(1);
        binarySortTreeManager.delNode(10);
        binarySortTreeManager.delNode(12);
        binarySortTreeManager.delNode(3);
        binarySortTreeManager.delNode(7);

        System.out.println("删除过后：");
        binarySortTreeManager.infixShow();
        System.out.println();

        System.out.println("根节点：");
        binarySortTreeManager.showRoot();
    }
}

class BinarySortTreeManager {
    node2 root;

    public void showRoot(){
        System.out.println(root);
    }
    public void add(node2 node) {
        if (root == null) root = node;
        else root.add(node);
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        //找到删除节点
        node2 targetNode = root.search(value);
        if (targetNode == null) {
            System.out.println("没有找到该节点");
            return;
        }
        //找到删除节点分父节点
        node2 parent = root.searchParent(value);

        if (targetNode.left == null && targetNode.right == null) {// 如果要删除的节点是叶子节点
            if (parent==null){//判断父节点是否为空
                root=null;
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
            node2 temp = targetNode.right;
            node2 tempParent = targetNode;
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
                if (parent==null){
                    root=targetNode.left;
                    return;
                }
                if (parent.left == targetNode) parent.left = targetNode.left;
                else if (parent.right == targetNode) parent.right = targetNode.left;
            } else {
                if (parent==null){
                    root=targetNode.right;
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

class node2 {
    int val;
    node2 left;
    node2 right;

    //查找节点
    public node2 search(int val) {
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
    public node2 searchParent(int val) {
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
    public void add(node2 node) {
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

    public node2(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "node{" +
                "val=" + val +
                '}';
    }
}