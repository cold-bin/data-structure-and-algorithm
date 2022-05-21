package stack;

public class stackDemo {
    public static void main(String[] args) {
        managerStack m = new managerStack(5);
        m.push(new node(1));
        m.push(new node(2));
        m.push(new node(3));
        m.list();
        System.out.println(m.pop());
        System.out.println(m.pop());
        m.list();
        System.out.println(m.pop());
        m.push(new node(4));
        m.list();
        System.out.println(m.pop());
    }
    //入栈
}

class managerStack {
    int top;
    int maxSize;
    node[] nodes;

    public managerStack(int maxSize) {
        //初始化栈顶
        top = -1;
        //栈的容量
        this.maxSize = maxSize;
        //初始化栈的大小
        nodes = new node[maxSize];
    }

    //入栈
    public void push(node node) {
        if (top < maxSize - 1) {
            nodes[++top] = node;
            return;
        }
        System.out.println("栈溢出");
    }

    //出栈
    public node pop() {
        if (top == -1) {
            throw new RuntimeException("栈空");
        }
        return nodes[top--];
    }

    //遍历
    public void list() {
        if (top == -1) {
            throw new RuntimeException("栈空");
        }
        System.out.println("栈元素：");
        for (int i = top; i >= 0; i--) {
            System.out.printf("nodes[%d]=%d\n", i, nodes[i].no);
        }
    }
}

class node {
    int no;

    public node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "node{" +
                "no=" + no +
                '}';
    }
}
