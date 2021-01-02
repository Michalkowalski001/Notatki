import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<Integer> stack =  new Stack<>();

        stack.add(1);
        stack.add(null);

        for (Integer a: stack) {
            System.out.println(a);
        }
    }
}
