package Java.concurrent;

/*
 Volatile的特征：
 可见性：对一个volatile变量的读，总是能看到（任意线程）对这个volatile变量最后的写入。
 */

public class VolatileVisibilityTest {
    // private static boolean initFlag = false;
    private static volatile boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Waiting for data...");
                while (!initFlag) {
                }
                System.out.println("=========== Success !");
            }
        }).start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                prepareData();
            }
        }).start();
    }

    public static void prepareData() {
        System.out.println("Start to prepare data...");
        initFlag = true;
        System.out.println("Prepare data complete...");
    }

}
