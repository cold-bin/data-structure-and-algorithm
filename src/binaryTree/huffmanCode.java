package binaryTree;

import javax.swing.plaf.basic.BasicDesktopIconUI;
import java.util.*;

public class huffmanCode {
    static Map<Character, String> CodeTable = new HashMap<>();//哈夫曼编码表
    static StringBuilder Code=new StringBuilder();//暂存

    public static void main(String[] args) {
        String text = "i like like like java do you like a java";
        System.out.println("字符串长度：" + text.length());
        //转化为字符数组
        char[] chs = text.toCharArray();
        List<node1> nodes = getNodes(chs);

        node1 root = createHuffmanTree(nodes);
        System.out.println("前序遍历：");
        root.preShow(root);

        System.out.println("哈夫曼编码：");

        StringBuilder result=new StringBuilder();
        getHuffmanCode(root,"",result);
        System.out.println("编码:"+Code);
        System.out.println("编码表："+CodeTable.toString());
    }

    //得到所有叶子节点的哈夫曼编码
    public static void getHuffmanCode(node1 node, String code, StringBuilder result) {
        StringBuilder code1 = new StringBuilder(result);
        code1.append(code);
        if (node != null) {
            if (node.data == 0 ) {//非叶子节点
                getHuffmanCode(node.left, "0", code1);//左子节点递归
                getHuffmanCode(node.right, "1", code1);//右子节点递归
            } else {//叶子节点
                Code.append(code1);
                CodeTable.put(node.data,code1.toString());
            }
        }
//        return code1;
    }

    //创建哈夫曼树，返回父节点
    public static node1 createHuffmanTree(List<node1> nodes) {
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取出首两个节点
            node1 left = nodes.get(0);
            node1 right = nodes.get(1);
            node1 parent = new node1(left.weight + right.weight);
            //组成二叉树
            parent.left = left;
            parent.right = right;
            //移除首两个节点
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //统计每个字符出现的次数（权重），然后记录在链表里
    public static List<node1> getNodes(char[] chs) {
        ArrayList<node1> nodes = new ArrayList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : chs) {
            Integer weight = map.get(ch);
            if (weight == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, weight + 1);
            }
        }
        //将map里的值转化为node1并华为链表
        //遍历map
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            nodes.add(new node1(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

class node1 implements Comparable<node1> {
    char data;//存放的数据
    int weight;//权值
    node1 left;
    node1 right;

    //前序遍历
    public void preShow(node1 root) {
        node1 temp = root;
        if (temp == null) return;
        System.out.println(temp);
        if (temp.left != null) preShow(temp.left);
        if (temp.right != null) preShow(temp.right);
    }

    @Override
    public String toString() {
        //为空的权值不输出
        if (data == 0) return "node1{weight=" + weight + '}';
        return "node1{" + "data=" + data + ", weight=" + weight + '}';
    }

    public node1(int weight) {
        this.weight = weight;
    }

    public node1(char data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(node1 o) {
        return this.weight - o.weight;
    }
}
