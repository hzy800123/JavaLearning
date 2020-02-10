package Java.MultiThreadInterview1A2B3C.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// BlockingQueue 接口使用（生产者 - 消费者）
// BlockingQueue 表示一个线程安全的队列
// URL: https://www.jianshu.com/p/b1408e3e3bb4
// 方法 put(Object):
// 把Object加到BlockingQueue队尾,如果BlockQueue没有空间,则调用此方法的线程被阻塞，直到BlockingQueue里面有空间再继续。
//
// 方法 take():
// 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态，直到BlockingQueue有新的对象被加入为止。
//
// java.util.concurrent包 具有以下 BlockingQueue 接口（在Java 6中）的实现：
// - ArrayBlockingQueue
// - LinkedBlockingQueue
public class TwoThreadsBlockingQueue {
    static BlockingQueue<String> q1 = new ArrayBlockingQueue(1);
    static BlockingQueue<String> q2 = new ArrayBlockingQueue(2);

    static volatile int number = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for( int i = 0; i < 26; i++ ) {
                try {
                    System.out.println(number + 1);
                    q1.put("OK");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for( int i = 0; i < 26; i++ ) {
                try {
                    synchronized (TwoThreadsBlockingQueue.class) {
                        q1.take();
                        System.out.println(" -- " + (char) ((int) ('A') + number));
                        number++;
                        q2.put("OK");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
