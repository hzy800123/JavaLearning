package Java.Concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Volatile的特征：
 * 原子性： 对任意单个volatile变量的 读/写 具有原子性，但类似于 volatile++ 这种 复合操作 不具有原子性。
 *
 * 所以需要：
 * Solution 1 and 2 - 再使用关键字 synchronized 来保证 方法 或者 代码块内 的操作 是原子的。
 * Solution 3 - 使用原子类 AtomicInteger 保证操作的 原子性。
 */
public class VolatileAtomicTest {
    public static volatile int num = 0;
    // public static AtomicInteger numAtomic = new AtomicInteger(0);    // Solution 3

    // 有的 线程 load 'num' 到工作内存，因为状态变为Invalid，使得某轮的循环结果无效，没有刷回主内存，循环次数-1，自增操作无效。
    public static void increase () {
        num++;
    }

    // --- Solution 1 --- with keyword 'synchronized' in whole method:
    // public static synchronized void increase () {
    //     num++;
    // }

    // --- Solution 2 --- with keyword 'synchronized' in code block:
    // public static void increase () {
    //     synchronized (VolatileAtomicTest.class) {
    //         num++;
    //     }
    // }

    // --- Solution 3 --- 使用 原子类 AtomicInteger 作为 计数器，保证操作 原子性。
    // public static void increase() {
    //     numAtomic.incrementAndGet();
    // }

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
        // System.out.println("numAtomic = " + numAtomic.get());   // Solution 3 --- 1000 * 10 = 10000
    }
}
