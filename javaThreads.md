# Java Thread

Cykl życia wątku:
- new - po utworzeniu obiektu
- runnable
    - ready - gotowy do bycia wykonywanym, nie ma tylko dostępu do CPU
    - running - jest w trakcie wykonywania przez CPU
- waiting - _sleep()_, _join()_, _wait()_
- terminated - ukończenie metody _run()_

Tworzenie wątku:
- `extends Thread` -> override `run()` -> `obj.start()`
- `implements Runnable` -> override `run()` -> `Thread(obj).start()`

Raczej używaj `Runnable` - loosely coupling, większa elastyczność.

- `yield()` - uruchomienie mechanizmu szeregowania, wywłaszczenie obecnego wątku i uaktywnienie innego spośród gotowych do pracy.
Ale pamiętaj, że wątek generalnie może zostać wywłaszczony w każdym momencie! 
- `sleep()` - uśpienie wątku. Po uśpieniu wątek przechodzi w stan _ready_ (niekoniecznie running). Nie używaj do szeregowania operacji.
Wybudzisz przez `interrupt()`. Nie zwalnia trzymanego zasobu.
- `interrupt()` - przerwanie wątku (dlatego sleep w try/catch, bo [rzuca wyjątkiem](https://docs.oracle.com/javase/7/docs/api/java/lang/InterruptedException.html))
- `join()` - czekaj na ten wątek. Możemy anulować czekanie poprzez `interrupt()`
- `wait()` - czekaj na sygnał do reaktywacji. `obj.wait()` - wstrzymaj wątek, aż 'ktoś' wywoła `obj.notify()`. Zwalnia trzymany zasób. Musi być z `synchronized`.
- `notify()` wyślij sygnał do reaktywacji. Musi być z `synchronized`.

Sekcja krytyczna - częśc procesu, która nie może być wykonywana współbieżnie z sekcją krytyczną innego procesu. [Szwed]

Wzajemne wykluczanie (Mutex - mutual exclusion) - +- to samo co sekcja krytyczna. Sekcja krytyczna jest lżejsza + inne subtelne różnice).

Klasa sekcji krytycznych - zbiór sekcji krytycznych, między którymi musi zachodzić wzajemne wykluczanie. Jak robię A.a(), to ty nie zrobisz A.b() -> synchronized. Zabezpiecza dostęp, nie kod.

Sekcja krytyczna != operacje atomiczne.

[Generalnie w trakcie wykonywania sekcji krytycznej wątek może zostać wywłaszczony](https://stackoverflow.com/questions/50677947/what-will-happen-when-insert-the-thread-yield-into-an-synchronized-function)

Dwa sposoby zaimplementowania problemu producenta i konsumera:
- [explicite bloki synchroniczne: synchronized(){}](https://howtodoinjava.com/java/multi-threading/wait-notify-and-notifyall-methods/)
- [metody synchroniczne](https://www.baeldung.com/java-wait-notify)

Właściciel monitora to nie jest koniecznie wątek wykonujący się. - monitor to pojęcie związane z lockowaniem obiektów
(kto jest jakby właścicielem obiektu w danym momencie czasu) - nie z tym co się obecnie wykonuje.

[Atomic ma różna znaczenia:](https://stackoverflow.com/questions/16902224/executing-block-of-code-atomically/16906229#16906229)
> 1. Atomic as in synchronized: only one thread can be executing the code at one time;
> 2. Atomic as in ACID: all of the action/block happens, or none of it does;
> 3. Atomic as in uninterruptible: once the block starts, it can't be interrupted, even by task switching.
>
> ...
>
> The third simply cannot be guaranteed in Java - it doesn't provide access to the "critical sections" primitives required for uninterruptibility. Fortunately, the need for this is pretty much restricted to operating systems and device drivers.

Inne:
- Wątki mają priorytety (niski 1-10 wysoki)
- Demony - wątki niepierwszoplanowe. Jak są same wątki demony, to kończymy pracę. GC jest demonem.
- Thread.currentThread() -> zwraca obecny wątek
- Kiedy main się skończy - kończy się program
- Warunek `wait()`'a musi być w `while` (materiały: wait&notify)

Materiały +-:
- [Wykłady Szwed](http://pszwed.kis.agh.edu.pl/wyklady_java/w10-java-threads.pdf)
- [Pytania 'rekrutacyjne' o +- wątki](https://howtodoinjava.com/java/multi-threading/wait-notify-and-notifyall-methods/)
- [Telusko całkiem chyba ok](https://www.youtube.com/watch?v=Xj1uYKa8rIw&list=PLsyeobzWxl7rmuFYRpkqLanwoG4pQQ7oW&index=2)
- [wait() & notify()](https://stackoverflow.com/questions/2536692/a-simple-scenario-using-wait-and-notify-in-java)
- [Wait&Sleep i inne](https://www.baeldung.com/java-wait-and-sleep) i [Wait&Sleep part 2](https://stackoverflow.com/questions/1036754/difference-between-wait-and-sleep?page=1&tab=votes#tab-top) - rożnica główna w puszczaniu/trzymaniu locka. **Inne rzeczy też tam są!**
- [Notify&Interrupt](https://stackoverflow.com/questions/30471285/what-is-the-difference-between-wait-notify-and-wait-interrupt) - celem interrupt jest wątek, notify monitor. W sumie robią podobną rzecz, ale **są inne!**

Przykłady:

```java
public class TestClass1 {
    public static void main(String[] args){
        new Thread(new ThreadA()).start();
    }
}

class ThreadA implements Runnable
{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("A: " + i);

            try {
                // Rzuci wyjątkiem, bo nie jest w metodzie synchronicznej. Zrób nową metodę synchroniczną, którą wywołasz z run'a
                wait();  // w sumie ten przykład jest z czekaniem na wątku ... A to jest w sumie raczej zwykle bezsensowne
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```

```java
public class TestClass1 {
    public static void main(String[] args) throws Exception {

        Integer I = 2;
        Thread a = new Thread(new ThreadA(I));

        a.start();
        Thread.sleep(1000); // Żeby nie zawołać za wcześnie notify()
        synchronized (I) {
            I.notify();
        }
        a.join();

        System.out.println("End");
    }
}

class ThreadA implements Runnable {
    Integer I;

    public ThreadA(Integer i) {
        this.I = i;
    }

    @Override
    public void run() {
        synchronized (I) {
            System.out.println("A: 0");
            try {
                I.wait();
                System.out.println("After wait");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
```