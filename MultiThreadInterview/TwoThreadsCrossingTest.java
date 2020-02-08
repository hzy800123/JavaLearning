package Java.MultiThreadInterview;

public class TwoThreadsCrossingTest {
    public static void main(String[] args) {
        TwoThreadsCrossing crossing = new TwoThreadsCrossing();

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
