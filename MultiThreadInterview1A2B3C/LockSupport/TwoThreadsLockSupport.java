package Java.MultiThreadInterview1A2B3C.LockSupport;

import java.util.concurrent.locks.LockSupport;

// LockSupport（park/unpark）源码分析:
// URL: https://www.jianshu.com/p/e3afe8ab8364
//
// park函数 是将当前Thread阻塞。
// unpark函数 则是将另一个Thread唤醒。
// 与Object类的 wait/notify 机制相比，park/unpark 有两个优点：
// (1) 以thread为操作对象更符合阻塞线程的直观定义
// (2) 操作更精准，可以准确地唤醒某一个线程（notify随机唤醒一个线程，notifyAll唤醒所有等待的线程），增加了灵活性。
//
// 有一点比较难理解的:
// 是unpark操作可以再park操作之前。也就是说，先提供许可。
// 当某线程调用park时，如果已经有许可了，它就消费这个许可，然后还可以继续运行。
// 当某线程调用park时，如果还没有许可，当前线程被立即阻塞，不可以继续运行。
public class TwoThreadsLockSupport {
    static volatile int number = 0;
    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for( int i = 0; i < 26; i++ ) {
                System.out.println(number+1);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for( int i = 0; i < 26; i++ ) {
                synchronized (TwoThreadsLockSupport.class) {
                    LockSupport.park();
                    System.out.println(" -- " + (char) ((int) ('A') + number));
                    number++;
                    LockSupport.unpark(t1);
                }
            }
        }, "t2");

        t2.start();
        t1.start();
    }
}
