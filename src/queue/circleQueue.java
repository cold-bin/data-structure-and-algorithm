package queue;

/*
 *   循环队列实现、复用数组空间
 * */

public class circleQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    //判断队列是否为空
    public boolean isEmpty() {
        return this.front == this.rear;
    }

    //判断队列是否满了
    public boolean isFull() {
        return (this.rear + 1) % maxSize == this.front;
    }

    //添加队列元素
    public void addQueue(int e) {
        if (isFull()) {
            throw new RuntimeException("queue is full\n");
        }
        this.arr[this.rear] = e;
        //注意取模
        this.rear = (this.rear + 1) % this.maxSize;
    }

    //取出队列元素
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("queue is empty\n");
        }
        int v = arr[front];
        //取模
        front = (front + 1) % maxSize;
        return v;
    }

    //获取有效个数
    public void list() {
        for (int i = 0; i < (rear + maxSize - front) % maxSize; i++) {
            System.out.print(arr[front + i] + " ");
        }
    }

    public circleQueue(int maxSize) {
        this.maxSize = maxSize;
        this.front = 0;
        this.rear = 0;
        this.arr = new int[maxSize];
    }

    public int getMaxSize() {
        return maxSize;
    }

    public int getFront() {
        return front;
    }

    public int getRear() {
        return rear;
    }

    public int[] getArr() {
        return arr;
    }
}
