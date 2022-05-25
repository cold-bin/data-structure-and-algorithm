package recursion;

public class recursionDemo {
    public static void main(String[] args) {
        test(10);
    }
    public static void test(int n){
        if (n>2) test(n-1);//每递归一次，就把这个函数压入栈中，直到不符合再次递归的条件时，再出栈依次运行求出结果
        System.out.println("n="+n);
    }
}
