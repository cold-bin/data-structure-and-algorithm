package stack;

public class midfixToSuffixDemo {
    public static void main(String[] args) {
        //中缀表达式转化为后缀表达式：`10 + ( ( 2 + 3 ) * 4 ) - 5+1` =》`10 2 3 + 4 * + 5 -`
        //10, 2, 3, 43, 4, 42, 5, 45, 40, 40, 43,
        stack2 s1 = new stack2(30);//符号栈(运算符+小括号)
        stack2 s2 = new stack2(30);//中间结果
        String expression = "10+((2+3)*4)-5";
        String number = "";
        char[] chs = expression.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            while (isDigital(chs[i])) {
                System.out.println("chs[" + i + "]:" + chs[i]);
                number += chs[i++];//叠加相邻数字字符
                if (i == chs.length) break;
            }

            System.out.println("number:" + number);
            if (i < chs.length) System.out.println("chs[" + i + "]:" + chs[i]);
            if (number != "") {
                //拆解数字,放进栈
                for (char v : number.toCharArray()) {
                    s2.push(v);

                }
                //放入数字和符号隔离符 '|'
                s2.push('|');
            }
            number = "";
            if (i < chs.length) {
                if (isLeft(chs[i])) {
                    s1.push(chs[i]);
                } else if (isRight(chs[i])) {
                    while (!isLeft(s1.getTop())) {
                        s2.push(s1.pop());
                        s2.push('|');
                    }
                    if (isLeft(s1.getTop())) s1.pop();//pop掉左括号
                }
            }
            if (i < chs.length) {
                if (isOpr(chs[i])) {
                    if (s1.isEmpty() || isLeft(s1.getTop()) || s1.priority(chs[i]) > s1.priority(s1.getTop())) {
                        System.out.println("ss " + chs[i]);
                        s1.push(chs[i]);
                    } else if (s1.priority(chs[i]) <= s1.priority(s1.getTop())) {
                        s2.push(s1.pop());
                        //放入数字和符号隔离符 '|'
                        s2.push('|');
                        i--;//重新比较
                    }
                }
            }
//           s1.showStack();
            s2.showStack();
            System.out.println();
        }
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
            s2.push('|');
        }
        System.out.println();
        s2.showStack();

    }
    //是否是左括号,支持中英文
    public static boolean isLeft(char ch) {
        return ch == '(' || ch == '（';
    }
    //是否是右括号
    public static boolean isRight(char ch) {
        return ch == ')' || ch == '）';
    }
    //是否是操作符
    public static boolean isOpr(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
    //是否是数字
    public static boolean isDigital(char ch) {
        return Character.isDigit(ch);
    }
}
class stack2 {
    int maxSize;
    int top;
    char[] arr;
    public stack2(int maxSize) {
        this.maxSize = maxSize;
        top = -1;
        arr = new char[maxSize];
    }
    //是否位空
    public boolean isEmpty() {
        return top == -1;
    }
    //是否已满
    public boolean isFull() {
        return top == maxSize - 1;
    }
    //入栈
    public void push(char num) {
        if (isFull()) throw new RuntimeException("栈溢出");
        arr[++top] = num;
    }
    //出栈
    public char pop() {
        if (isEmpty()) throw new RuntimeException("栈空");
        return arr[top--];
    }
    //返回栈顶元素，但不取出
    public char getTop() {
        return arr[top];
    }
    //显示队列元素（不取出队列元素）
    public void showStack() {
        if (isEmpty()) throw new RuntimeException("栈空");
        System.out.print("stack: ");
        for (int i = 0; i < top; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }
    //运算符优先级
    public int priority(char opr) {
        if (opr == '+' || opr == '-') return 0;
        else if (opr == '*' || opr == '/') return 1;
        else return -1;//标识运算符错误
    }
}
