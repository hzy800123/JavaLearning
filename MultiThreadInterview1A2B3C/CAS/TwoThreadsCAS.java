package Java.MultiThreadInterview1A2B3C.CAS;

import Java.MultiThreadInterview1A2B3C.LockSupport.TwoThreadsLockSupport;

public class TwoThreadsCAS {

    enum ReadyToRun { T1, T2 }

    static volatile ReadyToRun r = ReadyToRun.T1;
    static volatile int number = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for( int i = 0; i < 26; i++ ) {
                while (r != ReadyToRun.T1) {}
                System.out.println(number + 1);
                r = ReadyToRun.T2;
            }
        });

        Thread t2 = new Thread(() -> {
            for( int i = 0; i < 26; i++ ) {
                synchronized (TwoThreadsLockSupport.class) {
                    while (r != ReadyToRun.T2) {}
                    System.out.println(" -- " + (char) ((int) ('A') + number));
                    number++;
                    r = ReadyToRun.T1;
                }
            }
        });

        t1.start();
        t2.start();
    }
}
