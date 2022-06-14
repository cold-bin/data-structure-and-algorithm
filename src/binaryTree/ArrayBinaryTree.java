package binaryTree;
/*
* 顺序存储二叉树（前序、中序、后序方式）
* */
public class ArrayBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};//将数组看作是二叉树进行遍历，角标与对应二叉树有一个数学关系
        managerTree managerTree = new managerTree(arr);
        System.out.print("数组转化为二叉树\n前序遍历：");
        managerTree.preShow(0);
        System.out.println();
        System.out.print("中序遍历：");
        managerTree.infixShow(0);
        System.out.println();
        System.out.print("后序遍历：");
        managerTree.postShow(0);
    }
}

class managerTree {
    int[] arr;

    public managerTree(int[] arr) {
        this.arr = arr;
    }

    //将数组当成二叉树进行前序遍历
    public void preShow(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("二叉树为空");
            return;
        }
        System.out.print(arr[index] + " ");
        if (index * 2 + 1 < arr.length) {
            preShow(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length) {
            preShow(index * 2 + 2);
        }
    }

    //将数组当成二叉树进行中序遍历
    public void infixShow(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("二叉树为空");
            return;
        }
        if (index * 2 + 1 < arr.length) {
            infixShow(index * 2 + 1);
        }
        System.out.print(arr[index] + " ");
        if (index * 2 + 2 < arr.length) {
            infixShow(index * 2 + 2);
        }
    }

    //将数组当成二叉树进行后序遍历
    public void postShow(int index) {
        if (arr == null || arr.length == 0) {
            System.out.println("二叉树为空");
            return;
        }
        if (index * 2 + 1 < arr.length) {
            postShow(index * 2 + 1);
        }
        if (index * 2 + 2 < arr.length) {
            postShow(index * 2 + 2);
        }
        System.out.print(arr[index] + " ");

    }
}