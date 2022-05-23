package stack;

import java.util.Scanner;

/*
*   无括号版波兰计算器
* */

public class calculatorByStackDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入合法表达式：");//假设输入的数字只有一位数
        String express = sc.next();
        char[] chars = express.toCharArray();
        //数字栈
        stack stackNumber = new stack(10);
        //符号栈
        stack stackOperation = new stack(10);
        int num1 = 0;
        int num2 = 0;
        int val = 0;
        int operation = 0;
        String number = "";
        for (int i = 0; i < chars.length; ) {
            //符号
            if (stackOperation.isOperation(chars[i])) {
                //符号栈为空，将符号压入符号栈
                if (stackOperation.isEmpty()) stackOperation.push(chars[i]);
                else if (!stackOperation.isFull()) {
                    //如果符号栈有操作符，进行比较，如果当前操作符的优先级低于或等于栈中的操作符，
                    //就需要从数栈中pop两个数，再从符号栈里pop出一个符号进行运算，将得到的结果压
                    //入数栈，然后再将当前的操作符压入符号栈中
                    if (stackOperation.priority(chars[i]) <= stackOperation.priority(stackOperation.showTop())) {
                        num1 = stackNumber.pop();
                        num2 = stackNumber.pop();
                        operation = stackOperation.pop();
                        val = stackNumber.calculate(num2, num1, operation);
                        stackNumber.push(val);
                        stackOperation.push(chars[i]);
                    } else {
                        //当前操作符优先级更高，再压入符号栈中
                        stackOperation.push(chars[i]);
                    }
                }
                i++;
            }
            //如果是数字，先别慌入栈，再判断下一位是否是数字,是数字就拼接在一起
//            if (stackNumber.isDigital(chars[i])){
//                stackNumber.push(Character.getNumericValue(number));
//            }
            while (stackNumber.isDigital(chars[i])) {
                number = number + chars[i];
                i++;
                if (i == chars.length) break;
            }
            System.out.println(number);
            stackNumber.push(Integer.parseInt(number));
            number = "";
        }
        //扫描完毕后，顺序从数栈和符号栈pop出两个数字和一个符号，并运算，最后数栈只有一个数字就是结果
        //最后的栈中运算符都是同等优先级的运算符
        while (!stackOperation.isEmpty()) {
            num1 = stackNumber.pop();
            num2 = stackNumber.pop();
            operation = stackOperation.pop();
            val = stackOperation.calculate(num2, num1, operation);
            stackNumber.push(val);
        }
        System.out.println("计算的结果是：" + stackNumber.showButton());
    }
}

class stack {
    int top;
    int maxSize;
    int[] nodes;

    public stack(int maxSize) {
        top = -1;
        this.maxSize = maxSize;
        nodes = new int[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    //入栈
    public void push(int node) {
        if (isFull()) throw new RuntimeException("栈溢出");
        nodes[++top] = node;
    }

    //出栈
    public int pop() {
        if (isEmpty()) throw new RuntimeException("栈空");
        return nodes[top--];
    }

    //显示栈顶元素，但不取出
    public int showTop() {
        return nodes[top];
    }

    public int showButton() {
        return nodes[0];
    }

    //优先级
    public int priority(int operation) {
        if (operation == '*' || operation == '/') return 1;
        else if (operation == '+' || operation == '-') return 0;
        return -1;
    }

    //判断是不是操作符
    public boolean isOperation(int operation) {
        return operation == '+' || operation == '-' || operation == '*' || operation == '/';
    }

    //判断是不是数字
    public boolean isDigital(int number) {
        return Character.isDigit(number);
    }

    //计算方法
    public int calculate(int num1, int num2, int operation) {
        switch (operation) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0;
        }
    }
}
