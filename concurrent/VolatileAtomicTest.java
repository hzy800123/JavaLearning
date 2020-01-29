package Java.concurrent;

/*
 Volatile的特征：
 原子性 ：对任意单个volatile变量的读/写具有原子性，但类似于volatile++这种复合操作不具有原子性。
 */
public class VolatileAtomicTest {
    public static volatile int num = 0;

    public static void increase () {
        num++;
    }

//    public static synchronized void increase () {
//        num++;
//    }

    public static void main(String[] args) throws InterruptedException {

        Thread [] threads = new Thread[10];

        for ( int i = 0; i < threads.length; i++ ) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for ( int i = 0; i < 1000; i++ ) {
                        increase();
                    }
                }
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("num = " + num);     // 1000 * 10 = 10000
    }
}
