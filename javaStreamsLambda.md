
## Java Lambda

Czemu lambdy:
- parallel processing
- lepsza czytelność

Materiały:
- [Lambda Tips Bealdung](https://www.baeldung.com/java-8-lambda-expressions-tips)
- [Java Brains](https://www.youtube.com/watch?v=gpIUfj3KaOc&list=PLqq-6Pq4lTTa9YGfyhyW2CqdtW9RtY-I3) - trochę mocno rozległe?

Info:
- `() -> {}` - lambda xD
- `() -> ...` - jak jest jedno linijkowe, to nie trzeba `{}`

Jeden input - nie trzeba `()`. Nie trzeba też generalnie typów dawać, nawet chyba jest niezalecane (kompilator sam ogarnia).

`@FunctionalInterface` - daje info, że to z zamierzenia jest interfejs funkcjonalny (opcjonalna adnotacja).
Tylko jedna metoda abstrakcyjna (od 8 można [defaultowe metody](https://www.geeksforgeeks.org/default-methods-java/) w interfejsach)

Lambdy **podobne** do anonimowych klas (anonimowe inner klasy - `new List(){// tu implementuję wszystkie metody interfejsu List xD}`)

Generalnie, żeby przypisać lambdę do zmiennej, potrzebuje oddzielny interfejs.
Interfejs z metodą z takimi samymi parametrami jak lambda.
```java
class Test
{
    public static void main(String[] args){
      TestInterface = () -> System.out.println("Ala ma kota");
    }
}  
interface TestInterface
{
    void foo();
}
```

[util.function](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html) ma różne interfejsy, przez co nie muszę tworzyć własnego, jak potrzebuję.
- `Predicate<T>:: boolean test(T t)` - na `t` testuje warunek
- `Supplier<T>:: T get()` - zwraca zmienną typu `T`
- `Function<T,R>:: R apply(T t)` - dostaje `T t`, zwraca zmienną typu `R`
- `UnaryOperator<T>:: T apply(T t)` - Specjalizacja `Function`.
- `BinaryOperator<T,T,T>:: T apply(T t1, T t2)` - Specjalizacja `BiFunction`
- `Consumer<T>:: void accept(T t) `


Closures - Zamrażanie zmienne na taką wartość, jaką miała wcześniej (+-).

Effectively final - kompilator uznaje, że nie będę tego zmieniał, ale obserwuje mnie/tę zmienną. (powiązane z Closures)

```java
public class Test
{
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(
                12,123,12,1,23,12,41,123
        );

        int b = 12;
        list.forEach( a -> {
            b+=2; // error o final/effectively final
            return;
        });
    }
}
```

`This` w lambdach
Nie da się - `this` w lambdzie jest nieprzedefiniowane, więc tak jakby to w czym lambdę wywołuję jest `this`'em.

Method references - generalnie to fajne odwoływanie się: `MethodRegfeence::printMessage` (metoda 0, lub 1 argumentowa)

`forEach` w collection może być wykonywany równolegle, w pentlach `forEach` i `for` nie

Łapanie wyjątków: normalnie lub robimy wrappera na naszą lambdę :D


# Java Streams

Materiały:
- [Samouczek](https://www.samouczekprogramisty.pl/strumienie-w-jezyku-java/) - całkiem spoko, na szybko
- [Bealdung Introduction](https://www.baeldung.com/java-8-streams-introduction) - też całkiem spoko (samouczek chyba lepszy)
- [Bealdung](https://www.baeldung.com/java-8-streams) - dużo tam jest (bardziej zaawansowane rzeczy)
- [JustJoinIT](https://geek.justjoin.it/zastosowanie-stream-api-z-java-8-przyklady) - klasycznie, ok przykłady na _ParallelStream_
- [TutorialsPoint](https://www.tutorialspoint.com/java8/java8_streams.htm) - nie czytałem, ale chyba dobre na szybkie przypomnienie.

**Stream** - Sequence of elements supporting sequent and parallel aggregate operation

Operacje na strumieniach - 3 rozłączne klasy:
- tworzenie strumienia
- przetwarzanie danych wew. strumienia (zwraca strumień; pośrednie).
- zakończenie strumienia (zwraca nie strumień; końcowe).

Zasady:
- operacje nie mogą być stanowe.
- operacje nie mogą modyfikować źródła danych.
- Strumienie to nie struktury danych
- Strumienie są leniwe.
- Streams can't be reused - This kind of behavior is logical because streams were designed to provide an ability to apply a finite sequence of operations to the source of elements in a functional style, but not to store elements.

Są `IntStream`, `LongStream` oraz `DoubleStream` dla typów prostych. Mają też inne metody niż zwykły `Stream`. Wspólna klasa to `BaseStream`

[Operacje](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html):
- Pośrednie
    - `distinct()` - bazuje na .equals()
    - `filter(Predicate)`
    - `limit(long)`
    - `skip(long)` - skippuje zadaną ilość elementów
    - `map(Function)` - mapuje xD
    - `sorted([Comparator])` - sortuje [zadanym Comparatorem]
    - `static generate(Supplier)` - nieskończona list z Suppliera. `Stream.generate(new Random()::nextDouble).limit(2);`
    - `static iterate(seed, UnaryOperator)` - nieskończona lista na zasadzie f(x), f(f(x)) ... `Stream.iterate(2, a -> a*2).limit(6).`
- Końcowe
    - `boolean allMatch(Predicate)`
    - `boolean anyMatch(Predicate)`
    - `boolean noneMatch(Predicate)`
    - `Optional findAny()` - zwraca jakikolwiek
    - `Optional findFirst()`
    - `Optional max(Comparator)`
    - `Optional reduce([T t], BinaryOperator)` - `T t` jako wartość początkowa.
    - `* collect(Collector...)` - zebranie do np: Listy (_Collectors.toList()_)
    - `long count()`
    - `void forEach(Consumer)`
    - `Object[] toArray()`

_Jak ktoś umie obsługiwać młotek, to każdy problem wygląda jak gwóźdź_

[Optional<T>](https://docs.oracle.com/javase/8/docs/api/java/util/Optional.html):
- `T get()`
- `boolean isPresent()`
- `void ifPresent(Consumer)` - jak jest, to wykonaj` Consumera`, jak nie ma to nic.
- `T orElse(T t)` - jak jest `null`, to zwróć `t`. ([` VS orGetElse(Supplier)`](https://www.baeldung.com/java-optional-or-else-vs-or-else-get))
- ma też takie metody jak `Stream`: `filter`, `map`.


# Maven

- Opisuje jak zbudować projekt i jakie zależności sa potrzebne.
- Nie tylko Java (C#, Ruby, Scala).
- Kiedyś część Jakarty

**[Maven-naming convension](https://maven.apache.org/guides/mini/guide-naming-conventions.html)** - groupId jak w Javie, artifactId to nazwa projektu +-

## Maven Lifecycles:
- default - odpowiedzialny za project deployment
- clean - usunięcie plików z poprzednich buildów
- site - site documentation

**Lifecycle** - składa się z faz

**Faza** - stan w lifecyclu budowania. Sekwencja goali

**Plugin** - group of goals

[Lifecycle vs Phase vs Plugin vs Goal](https://stackoverflow.com/questions/26607834/maven-lifecycle-vs-phase-vs-plugin-vs-goal)

[Goals phases](https://www.baeldung.com/maven-goals-phases)