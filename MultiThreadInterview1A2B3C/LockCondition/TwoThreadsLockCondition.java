package Java.MultiThreadInterview1A2B3C.LockCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Lock Condition
// URL: https://www.jianshu.com/p/43e8e3a8b688
public class TwoThreadsLockCondition {
    private static volatile int number = 0;

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conditionT1 = lock.newCondition();
        Condition conditionT2 = lock.newCondition();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();

                    for (int i = 0; i < 26; i++) {
                        System.out.println(number + 1);
                        conditionT2.signal();
                        conditionT1.await();
                    }
                    conditionT2.signal();
                } catch (InterruptedException e) {
                        e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();

                    for (int i = 0; i < 26; i++) {
                        synchronized (TwoThreadsLockCondition.class) {
                            System.out.println((char) ((int) ('a') + number));
                            number++;
                            conditionT1.signal();
                            conditionT2.await();
                        }
                    }
                    conditionT1.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
