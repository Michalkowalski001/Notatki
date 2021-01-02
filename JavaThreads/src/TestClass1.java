public class TestClass1 {
    public static void main(String[] args) {
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
                wait();  // w sumie ten przykład jest zły też chyba z innych powodów xD
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}