package Java.MultiThreadInterview1A2B3C.Wait_Notify;

// 面试（Interview)题 - 要求2个线程分别 交替打印 26个 数字和字母。
// Thread A - Print number (e.g. 1, 2... 26)
// Thread B - Print letter (e.g. a, b... z)
// Expected Output Result:
// 1 a 2 b ...... 26 z
//
// URL: https://www.jianshu.com/p/f7d4819b7b24
// wait：线程自动释放其占有的对象锁，并等待notify
// notify：“随机地”唤醒一个正在wait当前对象锁的线程，自己释放锁，并让它拿到对象锁
// notifyAll：唤醒所有正在wait前对象锁的线程
//
// 在调用这3个方法的时候，当前线程必须获得这个对象的锁
// 必须在作用等同于 synchronized(obj){......} 的内部，才能够去调用obj的wait与notify/notifyAll三个方法，否则就会报错
public class TwoThreadsWaitNotifyWithOrder {
    private static volatile int number = 0;
    private static volatile boolean t1Started = false;

    public static void main(String[] args) {
        TwoThreadsWaitNotifyWithOrder crossing = new TwoThreadsWaitNotifyWithOrder();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (crossing) {   // 先获得“锁”
                    for( int i = 0; i < 26; i++ ) {
                        try {
                            System.out.println(number+1);
                            t1Started = true;
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

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (crossing) {    // 先获得“锁”
                    if (!t1Started) {
                        try {
                            crossing.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    for( int i = 0; i < 26; i++ ) {
                        try {
                            System.out.println((char)((int)('a') + number));
                            number++;
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

        threadA.start();
        threadB.start();
    }
}
