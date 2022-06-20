package binaryTree;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 赫夫曼树创建
 * */
public class huffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        node tree = createHuffmanTree(arr);
        tree.preShow(tree);
    }

    public static node createHuffmanTree(int[] arr) {
        //创建节点,并塞入集合之中
        ArrayList<node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new node(val));
        }
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //去除权值最小的两个node组成新的二叉树
            node left = nodes.get(0);
            node right = nodes.get(1);
            node parent = new node(left.val + right.val);
            parent.left = left;
            parent.right = right;
            nodes.remove(0);
            nodes.remove(0);//这里的索引应该还是0，前面已经移除了一个元素
            //将新的节点加进去
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class node implements Comparable<node> {
    int val;//哈夫曼树的权值
    node left;
    node right;

    public node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(node o) {
        return this.val - o.val;
    }

    public void preShow(node root) {
        node n = root;
        if (n == null) return;
        System.out.println(n);
        if (n.left != null) preShow(n.left);
        if (n.right != null) preShow(n.right);
    }
}