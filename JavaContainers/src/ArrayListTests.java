import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTests {
    public static void main(String[] args)
    {
        ArrayList<Integer> list1 = new ArrayList<>(Arrays.asList(0,1,2,3,4)); // or initial capacity

        // Addition
        list1.add(11);
        list1.add(1,11);
        list1.addAll(Arrays.asList(13,14,15));
        System.out.println("List: "+list1);

        // Clear
        list1.clear();
        System.out.println("After clear: "+list1);

        //
        list1.addAll(Arrays.asList(16,17,18));
        System.out.println("Second el: "+list1.get(2));
        if(!list1.isEmpty()){
            Integer [] array = list1.toArray(new Integer[0]);
            System.out.println("Array: "+Arrays.toString(array));
        }

        //////////////////////////////////


    }
}
