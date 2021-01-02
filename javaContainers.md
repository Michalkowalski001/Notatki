# Collections

<img alt="Obrazek z wiki (patrz linki)" src="https://upload.wikimedia.org/wikipedia/commons/a/ab/Java.util.Collection_hierarchy.svg" width="400">
<img alt="Obrazek z wiki (patrz linki)" src="https://upload.wikimedia.org/wikipedia/commons/7/7b/Java.util.Map_hierarchy.svg" width="400">


Framework z datastructures. Niby framework ale trochę library.

Zła operacja modifikująca - UnsuportedOperationException.


| |Set|List| Map|
|---|---|---|---|
|**Array**| |ArrayList| |
|**Linked**| |LinkedList| |
|**Linked-Hash**|Linked Hash Set| |Linked Hash Map|
|**Hash**|HashSet | |HashMap|
|**Tree**|TreeSet | |TreeMap|

Nie ma powyżej wszystkich kontenerów!

---

|Kolekcja   | Duplikaty| Synchronized| Nulle| Insertion Order|
|---:|:---:|:---:|:---:|:---:|
|LIST|---|---|---|---|
|ArrayList|Ok|X|Ok|Ok|
|LinkedList|Ok|X|Ok|Ok|
|SET|---|---|---|---|
|HashSet|X|X|Ok|X|
|Linked Hash Set|X|X|Ok|Ok|
|TreeSet|X|X|X|asc|
|MAP|---|---|---|---|
|Hash Map|X|X|Ok|X|
|Linked Hash Map|X|X|K-1 V-Ok|Ok|
|Tree Map|X|X|K-nie V-Ok|asc|
|INNE|---|---|---|---|
|HashTable*|X|Ok|X|X|
|ArrayDeque|Ok|X|X|Ok|
|Vector|Ok|Ok|Ok|Ok?|
|Stack|Ok|Ok (Ex: Vec) |Ok|Ok- no stack xD|

\* HashTable w sumie nie jest z Collections

# Interfejsy

### I: Collection
Ex: Iterable. Podstawowe operacje na kolekcjach, zauważ, że nie ma get'a!
- add
- addAll
- contains
- isEmpty
- size
- ...

### I: Set
Generalnie elementy są unikalne (brak duplikatów). Nie znalazłem żadnych set-specific metod.

### I: List
Może storować null'e, generalnie większość podstawowych metod:
- add (z indexem też)
- get
- set
- indexOf
- przeładowywuje? też niektóre metody Collection (jak add'a)

[Po co używać List interface](https://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java) - większe flexibility

[Niektóre metody nie gwarantują zwrócenia tego co ja chcę](https://www.geeksforgeeks.org/arraylist-sublist-method-in-java-with-examples/):

`ArrayList<Integer> x = list1.subList(0,2); //error`

### I: Queue
Ma specjalne operacje bez Exceptionowe
- FIFO (zazwyczaj, ale nie jest to wymagane)
- add VS offer
- remove VS poll
- element VS peek

# I: Deque
- Double Ended QUEue - generalnie z dwóch stron można

# I: Map
**Map.Entry interface**

To jest w ogóle inne drzewo niż Iterable!

Key, Value. Bez duplikatów. Klucz - jedna wartość. Nulle zależnie od inplementacji
- entrySet
- keySet


# Klasy

## List

### ArrayList
I: List, ...
- Capacity 10 , [_details of the growth policy are not specified (LoadFactor i jak bardzo rośnie)_](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)
- tablica obiektów pod spodem, przy usuwaniu się nie zmniejsza
- manipulacja wolniejsza (niż LinkedList)
- bazuje na indeksach

### LinkedList
I: List, Deque, Queue, ...
- lista dwukierunkowa
- szybka manipulacja

[ArrayList VS LinkedList](https://www.javatpoint.com/array-vs-arraylist-in-java)

## Set

### HashSet
I: Set, ...
- pod spodem HashMap
- Capacity 16, Load Factor: 75%

### LinkedHashSet
I: Set, ...
- pod spodem HashSet z elementamim połączonymi Listą
- Capacity 16, Load Factor: 75%

### TreeSet
I: Set, ...
- TreeMap pod spodem 
- log(n) dla basic operation (add, remove, contains)
- zachowuje natural ordering [compareTo] (tu: rosnący)
- przy tworzeniu można dać Comparator, który określi jak będą sortowane

## Map

### HashMap
I: Map, ...
- podwaja swoją wielkość
- Wewnątrz jest to Array[] of LinkedList of Nodes (Nody o równym hashu klucza)

### LinkedHashMap
I: Map, ...
- pod spodem HashMap z elementamim połączonymi Listą
- Capacity 16, Load Factor: 75%

### TreeMap
I: Map, ...
- Drzewo czerwono-czarne pod spodem

### HashTable - nie jest z tego FrameWorka
- Tablica bucketów (?)
- Capacity ***11***, Load Factor: 75%

[HashMap vs HashTable](https://www.javatpoint.com/difference-between-hashmap-and-hashtable)
- generalnie różnice w tabelce u góry
- HashMap is fast, HashTable slow (no pewnie przez synchronized :P)

## Inne

### ArrayDeque
I: Deque, Queue, ...
- Capacity 16
- likely to be faster than linked list (as queue) or stack (as stack)

### Vector
I: List,
- size 0
- może rosnąć i _maleć_
- generalnie thread safe

[ArrayList vs Vector](https://www.javatpoint.com/difference-between-arraylist-and-vector)
- różnice w tabelce wyżej
- ArrayList zwiększa rozmiar o 50%*, Vector o 100%
- ArrayList fast, Vector slow

\* [The details of the growth policy are not specified beyond the fact that adding an element has constant amortized time cost.](https://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html)


### Stack
I: List, Ex: Vector
- LIFO

## Pozostałe, nie wszystkie

- EnumSet -> wyspecjalizowany w enumach?
- Enum Map -> Jest takie coś xD

# [Comparable vs Comparator](https://www.baeldung.com/java-comparator-comparable)

**Comparable** - definiuje strategię porównywania obiektów tego samego typu. Domyślne sortowanie -> *Natural ordering*. 
Niech to co chcę porównywać implementuje ten interfejs. Jest jedna metoda `compareTo`. `Collections.sort()` w sumie tego używa

**Comparator** - Implementujemy `Comparator` i tam jest metoda `compare`. Do `Collections.sort()` możemy dodać jako argument.
Możemy też tworzyć je przez lambdy: `Comparator<Player> byRanking = (Player player1, Player player2) -> player1.getRanking() - player2.getRanking();`
Albo przez `Comparator.comparing` np: `Comparator<Player> byRanking = Comparator.comparing(Player::getRanking);`

Po co dwa?:
- czasami nie da się znienić modu źródłowego porównywania (pamiętasz C++ Dijkstra i Astar). Na takie rzeczy Comparator
- dzięki Comparator nie musimy dopisywać nowego kodu do klas bazowych
- możemy mieć wiele strategii porównywania

# Linki
- https://en.wikipedia.org/wiki/Java_collections_framework obrazki
- https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html
- https://docs.oracle.com/javase/7/docs/technotes/guides/collections/overview.html
