package Java.concurrent;

/*
 Volatile的特征：
 可见性：对一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。
 */

public class VolatileVisibilityTest {
    private static boolean initFlag = false;
    // private static volatile boolean initFlag = false;
    static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                refresh();
                // refresh2();
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

    public static void refresh() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Waiting for data...");
        int i = 0;
        while (!initFlag) {
        }
        System.out.println(threadName + " - get the updated 'initFlag' value successfully === !");
    }

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

    public static void prepareData() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " - Prepare data Start...");
        initFlag = true;
        System.out.println(threadName + " - Prepare data complete and update the shared 'initFlag' value...");
    }

}
