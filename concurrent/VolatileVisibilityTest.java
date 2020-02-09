package Java.Concurrent;

/*
 * --- Solution 1 ---
 * 利用 Volatile的特征：
 * 可见性：对一个volatile变量的读，总是能看到（其他 任意线程）对这个volatile变量 最后的 写入。
 *
 * --- Solution 2 ---
 * 利用 关键字 synchronized：
 * This 'synchronized' will trigger blocking and to re-load the updated value from memory again.
 * 线程的上下文切换，需要加载最新的值，保存到 TSS任务状态栈（保存现场）。
 *
 */

public class VolatileVisibilityTest {
    private static boolean initFlag = false;
    // private static volatile boolean initFlag = false;    // Solution 1
    static final Object object = new Object();           // Solution 2

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                refresh();      // Solution 1
                // refresh2();      // Solution 2
            }
        }, "Thread A").start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                prepareData();
            }
        },"Thread B").start();
    }


    // Solution 1 ( -- Start --- )
    public static void refresh() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Waiting for data...");
        int i = 0;
        while (!initFlag) {
        }
        System.out.println(threadName + " - get the updated 'initFlag' value successfully === !");
    }
    // Solution 2 ( --- End --- )


    // Solution 2 ( -- Start --- )
    public static void refresh2() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Waiting for data...");
        int i = 0;
        while (!initFlag) {
            // This 'synchronized' will trigger blocking and to re-load the updated value
            // 线程的上下文切换，需要加载最新的值，保存到 TSS任务状态栈（保存现场）
            synchronized (object) {
                i++;
            }
        }
        System.out.println(threadName + " - get the updated 'initFlag' value successfully === !");
    }
    // Solution 2 ( --- End --- )


    public static void prepareData() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Prepare data Start...");
        initFlag = true;
        System.out.println(threadName + " - Prepare data complete and update the shared 'initFlag' value...");
    }

}
