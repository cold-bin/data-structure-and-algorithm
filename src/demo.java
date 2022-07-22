import java.util.Stack;

public class demo {
    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1); // queue is: [1]
        myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        myQueue.peek(); // return 1
        myQueue.pop(); // return 1, queue is [2]
        myQueue.empty(); // return false
        myQueue.show();
    }
}
class MyQueue {
    Stack<Integer> s1;
    Stack<Integer> s2;

    public void show(){
        int i=0;
        System.out.println("s1: "+s1);
        System.out.println("s2: "+s2);
    }

    public MyQueue() {
        this.s1 = new Stack<>();
        this.s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
    }

    public int pop() {
        while (s1.size() != 0) {
            s2.push(s1.pop());
        }
        int res = s2.pop();
        while (s2.size() != 0) {
            s1.push(s2.pop());
        }
        return res;
    }

    public int peek() {
        while (s1.size() != 0) {
            s2.push(s1.pop());
        }
        int res = s2.peek();
        while (s2.size() != 0) {
            s1.push(s2.pop());
        }
        return res;
    }

    public boolean empty() {
        return s1.empty();
    }
}
