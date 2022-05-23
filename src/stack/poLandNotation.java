package stack;

import java.util.Arrays;

/*
*   逆波兰计算器
* */

public class poLandNotation {
    public static void main(String[] args) {
        //先定义一个逆波兰表达式：（3+4）*5-6 -》34+5*6-
        String suffixExpression="3 4 + 5 * 6 -";
        char[] chs=suffixExpression.toCharArray();
        stackNumber stackNumber=new stackNumber(10);
        String number ="";//数字拼接
        int num1=0;
        int num2=0;
        int val=0;
        for (int i = 0; i < chs.length;i++) {
            //扫描数字
            while (!isSpace(chs[i])){
                if (isDigital(chs[i])) number+=chs[i++];
                //如果是下一个字符为操作符，跳出循环
                else if (isOpr(chs[i])) break;
                //有数字则入栈
                if (number!=""){
                    stackNumber.push(Integer.parseInt(number));
                }
            }
            //扫描字符串
            if (!isSpace(chs[i])){
                if (isOpr(chs[i])) {
                    num1=stackNumber.pop();
                    num2=stackNumber.pop();
                    val=stackNumber.calculate(num2,num1,chs[i]);
                    stackNumber.push(val);
                }
            }
            number="";//清理本次number拼接字符
        }
//        stackNumber.showStack();
        System.out.print("计算结果：");
        stackNumber.showBottom();
    }
    //是否是操作符
    public static boolean isOpr(char ch){
        return ch=='+'||ch=='-'||ch=='*'||ch=='/';
    }
    //是否是数字
    public static boolean isDigital(char ch){
        return Character.isDigit(ch);
    }
    //数字结束符 ‘ ’空格
    public static boolean isSpace(char ch){
        return ch==' ';
    }
}
class stackNumber{
    int maxSize;
    int top;
    int[] arr;
    public stackNumber(int maxSize) {
        this.maxSize = maxSize;
        top=-1;
        arr=new int[maxSize];
    }
    //是否位空
    public boolean isEmpty(){
        return top==-1;
    }
    //是否已满
    public boolean isFull(){
        return top==maxSize-1;
    }
    //入栈
    public void push(int num){
        if (isFull()) throw new RuntimeException("栈溢出");
        arr[++top]=num;
    }
    //出栈
    public int pop(){
        if (isEmpty()) throw new RuntimeException("栈空");
        return arr[top--];
    }
    //显示队列元素（不取出队列元素）
    public void showStack(){
        if (isEmpty()) throw new RuntimeException("栈空");
        System.out.println("栈元素："+ Arrays.toString(arr));
    }
    //显示栈底元素
    public void showBottom(){
        System.out.println(arr[0]);
    }

    //计算方法
    public int calculate(int num1,int num2,char opr){
        switch (opr){
            case '+':
                return num1+num2;
            case '-':
                return num1-num2;
            case '*':
                return num1*num2;
            case '/':
                return num1/num2;
            default:
                System.out.println("无效的操作符");
                return -1;
        }
    }
}