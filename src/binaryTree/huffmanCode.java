package binaryTree;

import java.util.*;

public class huffmanCode {
    static Map<Byte, String> CodeTable = new HashMap<>();//哈夫曼编码表
    static int LastLength = -1;//记录最后一个数压缩的长度，方便解压正确

    public static void main(String[] args) {
        String text = "i like like like java do you like a java -asasda 1 0 -1";
        System.out.println("字符串长度：" + text.length());
        //转化为字符数组
        byte[] chs = text.getBytes();
        System.out.println(Arrays.toString(huffmanZip(chs)));

        System.out.println(new String(decode(CodeTable, huffmanZip(chs))));
    }

    public static byte[] decode(Map<Byte, String> huffmanCodeTable, byte[] huffmanBytes) {
        //将压缩数据转化为二进制字符串，即解压
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < huffmanBytes.length; i++) {
            if (i == huffmanBytes.length - 1) flag = true;
            String bitString = bytesToBitString(flag, huffmanBytes[i]);
            builder.append(bitString);
            System.out.println("结果：" + huffmanBytes[i] + " " + bitString);
        }
        System.out.println(builder);
        //根据哈夫曼编码表，将二进制字符串还原成源字符串，即编码
        //调换键值顺序
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodeTable.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //集合存储byte
        List<Byte> list = new ArrayList<>();

        for (int i = 0; i < builder.length(); ) {
            int count = 1;//计数指针
            boolean f = true;
            Byte b = null;

            while (f) {
                String key = builder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    f = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    //将一个byte转化为有个二进制字符串，flag标志最后一位
    public static String bytesToBitString(boolean flag, byte b) {
        StringBuilder prefix = new StringBuilder();//用于正数前缀补零
        String str = Integer.toBinaryString(b);
        int len = str.length();
        if (flag) {//如果是最后一位，可能会少于8位，前面记录在LastLength全局变量里
            //如果是正数，需要补高位，补足LastLength位
            if (b >= 0) {
                while (len < LastLength) {
                    prefix.append('0');
                    len++;
                }
                return prefix + str;
            } else {//如果是负数，就需要只保留后8位
                return str.substring(str.length() - 8);
            }
        } else {//不是最后一位，一定是满8位
            //如果是正数，需要补高位补足8位
            if (b >= 0) {
                while (len < 8) {
                    prefix.append('0');
                    len++;
                }
                return prefix + str;
            } else {//如果是负数，就需要只保留后8位
                return str.substring(str.length() - 8);
            }
        }
    }


    //将原始字符串的bytes数据编码并压缩，返回压缩结果
    public static byte[] huffmanZip(byte[] bytes) {
        //根据原始数据生成哈夫曼编码树
        List<node1> nodes = getNodes(bytes);
        node1 root = createHuffmanTree(nodes);
        //根据生成的哈夫曼编码树生成编码表
        StringBuilder result = new StringBuilder();
        getHuffmanCode(root, "", result);

        //根据哈夫曼编码表和原始bytes数组数据，进行压缩
        return zip(bytes, CodeTable);
    }

    //根据赫夫曼编码表，将字符串的byte数组，返回一个赫夫曼编码，压缩后的byte数组
    public static byte[] zip(byte[] oldBytes, Map<Byte, String> codeTable) {
        StringBuilder builder = new StringBuilder();
        //根据哈夫曼编码表，将byte数组转化为编码数据（变长了）
        //这个编码数据还不是最后的压缩结果，根据显示，显然这个数据的长度还变长了，并没有压缩
        for (byte b : oldBytes) {
            builder.append(codeTable.get(b));
        }
        System.out.println(builder);
        //将编码的字符串数据进行压缩，每8位一个字节数据,不足8位的往前补一位
        int len;
        int index = 0;
        if (builder.length() % 8 == 0) len = builder.length() / 8;
        else len = builder.length() / 8 + 1;
        //创建压缩后的byte数组
        byte[] compressedBytes = new byte[len];
        for (int i = 0; i < builder.length(); i += 8) {
            String strByte;
            if (i + 8 > builder.length()) {//将最后剩余的字节加进去（可能少于8个）
                strByte = builder.substring(i);
                //todo 为方便解码最后一位数字，需要记录最后一位byte数字的二进制长度
                LastLength = strByte.length();
            } else {
                strByte = builder.substring(i, i + 8);
            }
            //每8个二进制位压缩成一个数字：
            compressedBytes[index++] = (byte) Integer.parseInt(strByte, 2);
        }
        return compressedBytes;
    }

    //得到所有叶子节点的哈夫曼编码表
    public static void getHuffmanCode(node1 node, String code, StringBuilder result) {

        StringBuilder code1 = new StringBuilder(result);
        code1.append(code);
        if (node != null) {
            if (node.data == 0) {//非叶子节点
                getHuffmanCode(node.left, "0", code1);//左子节点递归
                getHuffmanCode(node.right, "1", code1);//右子节点递归
            } else {//叶子节点
                CodeTable.put(node.data, code1.toString());
            }
        }
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
    public static List<node1> getNodes(byte[] chs) {
        ArrayList<node1> nodes = new ArrayList<>();
        HashMap<Byte, Integer> map = new HashMap<>();
        for (byte ch : chs) {
            Integer weight = map.get(ch);
            if (weight == null) {
                map.put(ch, 1);
            } else {
                map.put(ch, weight + 1);
            }
        }
        //将map里的值转化为node1并化为链表
        //遍历map
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new node1(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

class node1 implements Comparable<node1> {
    byte data;//存放的数据
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

    public node1(byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(node1 o) {
        return this.weight - o.weight;
    }
}
