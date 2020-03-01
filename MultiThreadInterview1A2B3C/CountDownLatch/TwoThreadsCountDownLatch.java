package Java.MultiThreadInterview1A2B3C.CountDownLatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

// CountDownLatch - “三二一，芝麻开门！”
// URL: https://www.jianshu.com/p/128476015902
// CountDownLatch 作用是等待其他的线程都执行完任务，然后主线程才继续往下执行。
// CountDownLatch 主要有2个方法：
// (1) countDown() - 用于 使计数器减一
// (2) await()     - 用于 是调用该方法的线程处于等待状态
//
// 这里需要注意的是：
// countDown()方法并没有规定一个线程只能调用一次，当同一个线程调用多次countDown()方法时，
// 每次都会使计数器减一；
// await()方法也并没有规定只能有一个线程执行该方法，如果多个线程同时执行await()方法，
// 那么这几个线程都将处于等待状态，并且以共享模式享有同一个锁。
public class TwoThreadsCountDownLatch {
    private static volatile int number = 0;
    private static CountDownLatch latch = new CountDownLatch(1);    // 初始的计数器为1

    public static void main(String[] args) {
        TwoThreadsCountDownLatch crossing = new TwoThreadsCountDownLatch();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (crossing) {   // 先获得“锁”
                    for (int i = 0; i < 26; i++) {
                        System.out.println(number + 1);
                        latch.countDown();      // 使 计数器 减一
                        try {
                            crossing.notify();
                            crossing.wait();    // 让出“锁”，并自己阻塞
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    crossing.notify();  // 最后一次，需要叫醒对方，否则无法停止程序
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (crossing) {    // 先获得“锁”
                try {
                    latch.await();      // 等待计数器减为0，才继续执行。
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 26; i++) {
                    System.out.println((char) ((int) ('a') + number));
                    number++;
                    try {
                        crossing.notify();
                        crossing.wait();    // 让出“锁”，并自己阻塞
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                crossing.notify();  // 最后一次，需要叫醒对方，否则无法停止程序
            }
        });

        threadA.start();
        threadB.start();

        List<String> t = new ArrayList<>();
        t.add("a");
        t.add("b");
        t.forEach(System.out::println);

        t.stream().filter((e) -> e.hashCode() > 1).forEach(System.out::println);

    }
}
