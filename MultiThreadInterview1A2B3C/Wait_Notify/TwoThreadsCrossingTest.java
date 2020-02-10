package Java.MultiThreadInterview1A2B3C.Wait_Notify;

public class TwoThreadsCrossingTest {
    public static void main(String[] args) {
        TwoThreadsWaitNotify crossing = new TwoThreadsWaitNotify();

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i = 0; i < 26; i++ ) {
                    try {
                        crossing.printNumberLetter("A");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for( int i = 0; i < 26; i++ ) {
                    try {
                        crossing.printNumberLetter("B");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
    }
}
