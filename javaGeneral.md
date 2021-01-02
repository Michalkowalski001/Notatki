https://stackoverflow.com/questions/8892350/immutable-vs-unmodifiable-collection
https://www.java2novice.com/java_interview_questions/fail-fast/ Fajna strona?
Value based classes (Optional)

# Java

[Fail-fast vs Fail-safe iterators](https://www.geeksforgeeks.org/fail-fast-fail-safe-iterators-java/)


**Fail-fast** - wyrzuca `ConcurrentModificationException`, operuje na oryginalnej kolekcji. Przykładowe kolekcje: `ArrayList`, `HashMap`, `Vector`.
**Fail-safe** - (nie ma de'facto czegoś takiego w JavaDocks). Operują na kopii (dlatego nie rzucają `ConcurrentModificationException`), potrzebują extra memory (na kopię) i czas na skopiowanie. Przykładowe kolekcje: `CopyOnWriteArrayList`

W sumie to są jeszcze pośrednie (_weakly consistent_), jak iterator `ConcurrentHashMap`. Nie jest ani Fail-fast- bo toleruje modyfikacje, ale operuje na oryginale.


## Garbage Collector

JVM stosuja różne podejścia do oczyszczania. Zależy to od implementacji. (HotSpot - najpopularniejsza implementacja)

1. GC znajduje wszystkie żywe obiekty
2. Usuwa pozostałe
    - Mark and Sweep - usuwa i nie reorganizuje pamięci.
    - Mark Sweep and Compact - reorganizacja pamięci na końcu.
    - Mark and Copy - przenoszenie przetrwałych do innego obszaru pamięci (reorganizacja tylko przez kopiowanie)
    - Generational Collection Algorithm - połączenie powyższych. Dzieli pamięć na bloki i używa różnych metod dla różnych bloków.
        - Eden, S1. S2 -> Copy (small survival rate)
        - Old - Sweep/Compact (big survival rate)

**GC Root** - odwołania bezpośrednie, obiekty na pewno żywe (wątek, zmienna lokalna, monitor). Robimy przeszukiwanie grafu w nich, otrzymujemy co jest osiągnalne z danego GC Root.

Jest jeszcze ReferenceCounting Algorithm - liczenie referencji, ale jak zrobimy cykliczną to jest MemoryLeak

Mamy generacje (lepsza wydajność dzięki temu):
- **Young Gen**
    - Eden - tworzone obiekty sa tutaj. Jak w Edenie jest mało miejsca, to wchodzi GC. Przenosi do S1 żywe obiekty z Edenu. W następnej iteracji uwzględnia też S1.
    - Survivor one
    - Survivor two - [Czemu dwa survivor](https://stackoverflow.com/questions/10695298/java-gc-why-two-survivor-regions)
- **Old Generation** - sposób czyszczenia Old zależy od implementacji
- **Permament Generation** - metadane o klasach itd. Czasami jak nie ma miejsca na klasy to może zostać coś wyczyszczone. Jest zawarte w Full GC. 

**Minior GC** - czyszczenie Young Gen. _Stop the World Event_ - zawsze zatrzymuje wszystkie wątki, dopóki nie skończy swojego działania.

**Major GC** - czyszczenie Old Gen. Też _Stop the World Event_.

**Full GC** - zarówno Young i Old Gen.

[Typy GC](https://www.baeldung.com/jvm-garbage-collectors) (możemy zmieniać przy uruchamianiu aplikacji: `java -XX:+UseParallelGC -jar Application.java`):

1. Serial GC
2. Parallel GC (default)
3. CMS GC
4. G1 GC


Materiały:
- [Wprowadzenie (całkiem spoko)](https://bulldogjob.pl/news/404-jvm-garbage-collector-wprowadzenie)
- [Artykuł (pobrany) całkiem spoko](https://bottega.com.pl/pdf/materialy/jvm/jvm2.pdf)
- [Medium (też chyba spoko)](https://medium.com/@alitech_2017/how-does-garbage-collection-work-in-java-cf4e31343e43)

