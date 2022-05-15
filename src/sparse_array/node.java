package sparse_array;
/*
 *   稀疏数组的节点
 * */

public class node {
    int i;
    int j;
    int value;

    public node(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "node{" +
                "i=" + i +
                ", j=" + j +
                ", value=" + value +
                '}';
    }
}
