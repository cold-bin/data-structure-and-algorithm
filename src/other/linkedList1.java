package other;

import java.util.Arrays;

public class linkedList1 {
    public static void main(String[] args) {
        Solution s=new Solution();
        ListNode n1=new ListNode();
        ListNode n2=new ListNode(2);
//        ListNode n3=new ListNode(3);
//        ListNode n4=new ListNode(4);
//        ListNode n5=new ListNode(5);
        n1.next=n2;
//        n2.next=n3;
//        n3.next=n4;
//        n4.next=n5;
        s.removeNthFromEnd(n1,1).show();

    }
}

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        tempMemory tempMemory = new tempMemory(40);
        //遍历链表，每遍历一次放入栈中
        ListNode tempNode = head.next;
        while (tempNode != null) {
            tempMemory.addElement(tempNode);
            tempNode = tempNode.next;
        }

//        System.out.println("数组："+Arrays.toString(tempMemory.stack)+"\n指针："+tempMemory.point);
        //遍历完毕，再从栈的正数第n个取出来
        if (tempMemory.point + 1 - n > 0) {//删除节点前有一个节点
            ListNode preDelNode = null;
            int index = 0;
            while (index != n + 1) {//pop出删除节点的上一个节点
                preDelNode = tempMemory.popElement();
                index++;
            }
            if (preDelNode == null) {
                return head;
            }
            preDelNode.next = preDelNode.next.next;
        } else if (tempMemory.point + 1 - n == 0) {//删除节点前没有节点，说明是删除的头节点的第一个节点
            if (head.next != null) {
                head.next = head.next.next;
            }
        }
        return head;
    }
}

//队列模拟栈
class tempMemory {
    int maxSize;
    int point;
    ListNode[] stack;

    public tempMemory(int maxSize) {
        this.maxSize = maxSize;
        this.point = -1;
        this.stack = new ListNode[maxSize];
    }

    public boolean isFull() {
        return this.point == this.maxSize - 1;
    }

    public boolean isEmpty() {
        return this.point == -1;
    }

    //添加
    public void addElement(ListNode node) {
        if (isFull()) {
            return;
        }
        this.stack[++this.point] = node;
    }

    //弹出
    public ListNode popElement() {
        if (isEmpty()) {
            return null;
        }
        return this.stack[this.point--];
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void show(){
        ListNode node=this;
        while (node!=null){
            node=node.next;
            System.out.print(node+" ");
        }
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}