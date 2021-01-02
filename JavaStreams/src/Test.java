import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args)
    {
        List<Integer> numberList = Arrays.asList(
                12, 123, 12, 1, 23, 12, 41, 123
        );

        List<Person> personList = Arrays.asList(
                new Person(12,"Ala","As"),
                new Person(123,"Bolek","Bąk"),
                new Person(2,"Cezary","Czyżyk"),
                new Person(55,"Danuta","Dąb"),
                new Person(23,"Elżbieta","Enes"),
                new Person(18,"Filip","Frączek"),
                new Person(13,"Gabriella","Gwóźdź")
                );

//        numberList.stream().distinct().forEach(System.out::println);
//        numberList.stream().filter(n -> n>12).forEach(System.out::println);
//        Stream.iterate(2, n -> n*2).skip(10).limit(10).forEach(System.out::println);
//        personList.stream().map(Person::getAge).sorted((p1,p2) -> p2-p1).forEach(System.out::println);
//        Stream.generate( () -> 12).limit(2).forEach(System.out::println);

//        System.out.println(numberList.stream().allMatch(x -> x>22));
//        System.out.println(numberList.stream().distinct().findAny());
        System.out.println(numberList.stream().sorted((a,b) -> b-a).findFirst().orElseGet(  () -> 2));
//        System.out.println(numberList.stream().reduce((a,b) -> a*b));
//        System.out.println(personList.stream().map(Person::getAge).collect(Collectors.toList()));
//        System.out.println(numberList.stream().filter(x -> x < 100).count());
//        System.out.println(Arrays.toString(personList.stream().toArray()));
    }
}
