package Java.locking;

public class DeadLockExample {
    public static void main(String[] args) {

        // Object resource_a = new Object();
        // Object resource_b = new Object();
        String resource_a = "a";
        String resource_b = "b";

        Thread threadA = new Thread(() -> {
            synchronized (resource_a) {
                System.out.println("Thread A Get resource a");
                try {
                    Thread.sleep(2000);
                    synchronized (resource_b) {
                        System.out.println("Thread A Get resource b");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (resource_b) {
                System.out.println("Thread B Get resource b");
                synchronized (resource_a) {
                    System.out.println("Thread B Get resource a");
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
