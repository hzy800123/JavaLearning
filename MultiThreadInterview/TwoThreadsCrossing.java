package Java.MultiThreadInterview;

public class TwoThreadsCrossing {
    static volatile int number = 0;

    // Thread A - Print number (e.g. 1, 2... 26)
    // Thread B - Print letter (e.g. a, b... z)
    //
    // Expected Output Result:
    // 1 a 2 b ...... 26 z
    public synchronized void printNumberLetter(String threadID) throws InterruptedException {
        if ( threadID.equals("A") ) {
            // System.out.println("Thread A - " + (number +1));
            System.out.println(number+1);
            wait();
            notify();
        } else {
            // System.out.println("Thread B - " + (char)((int)('a') + number));
            System.out.println((char)((int)('a') + number));
            number++;
            notify();
            wait();
        }
    }


}
