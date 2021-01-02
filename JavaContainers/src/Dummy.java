public class Dummy implements Comparable<Dummy>{
    public int age;
    public String name;

    @Override
    public int compareTo(Dummy o) {
        return 0;
    }
}
