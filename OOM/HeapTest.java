package Java.OOM;

import java.util.ArrayList;

public class HeapTest {
    // byte[] a = new byte[1024*100];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapList = new ArrayList<>();

        while (true) {
            heapList.add(new HeapTest());
            Thread.sleep(1000);
        }
    }
}
