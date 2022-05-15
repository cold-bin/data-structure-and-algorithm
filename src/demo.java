public class demo {
    public static void main(String[] args) {

        int maxSize = 10;
        int front = 1;
        int rear = 7;
        System.out.println((rear + maxSize - front) % maxSize);
        System.out.println(Math.abs(rear - front));
    }
}
