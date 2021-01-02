import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.TreeSet;

public class Sets {
    public static void main(String[] args) {
        HashSet<Integer> hashSet = new HashSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();

        Random r = new Random();
        int rand;
        for(int i=0; i<10; i++)
        {
            rand = r.nextInt(20);
            System.out.println(i+" " + rand);
            hashSet.add(rand);
            linkedHashSet.add(rand);
            treeSet.add(rand);
        }

        System.out.println("\n\n");
        System.out.println(hashSet);
        System.out.println(linkedHashSet);
        System.out.println(treeSet);

    }
}
