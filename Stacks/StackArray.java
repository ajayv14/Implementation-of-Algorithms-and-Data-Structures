

public class StackArray {

    int[] arr;
    int top = -1;

    public static void main(String[] args) {

        StackArray s = new StackArray();
        s.stackSize(100);
        s.push(10);
        s.push(20);
        System.out.println("pushed");
        s.pop();

        System.out.println(s.top());
        System.out.println(s.isNull());


    }

    public void push(int num) {
        top = top + 1;
        arr[top] = num;
    }

    public int pop() {
        top = top - 1;
        return arr[top + 1];
    }


    public String top() {

        String r = null;
        if (!isNull()) {
            r = " " + arr[top];
        }
        return r;
    }

    public boolean isNull() {

        if (top == -1) {
            return true;
        } else {
            return false;
        }
    }


    public void stackSize(int size) {
        arr = new int[size];
    }


}
