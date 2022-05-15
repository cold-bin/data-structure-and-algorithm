package queue;

/*
 *   简单非循环队列实现
 *   缺点：数组空间无法复用，一旦填满数组无法再次使用已经使用了的数组空间
 * */

public class queue {
    //队列深度
    private int maxSize;
    //队头指针
    private int front;
    //队尾指针
    private int rear;
    //队列元素使用数组作为队列结构
    private int[] arr;

    //添加队列元素
    public void addQueue(int e) {
        if (isFull()) {
            System.out.println("the queue is full...");
            return;
        }
        this.rear++;
        this.arr[this.rear] = e;
    }

    //取出队列元素
    public int getQueue() {
        //判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("the queue is empty...");
        }
        this.front++;
        return this.arr[this.front];
    }

    //显示队列数据
    public void list() {
        for (int v : arr) {
            System.out.print(v + " ");
        }
    }

    //判断队列是否已满
    public boolean isFull() {
        return this.maxSize - 1 == this.rear;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    //构造队列
    public queue(int maxSize) {
        this.maxSize = maxSize;
        this.front = -1;
        this.rear = -1;
        this.arr = new int[maxSize];
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }
}
