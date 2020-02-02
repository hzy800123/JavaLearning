package Java.Singleton.Enum;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SingletonMultiThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Set<String> resultSet = new HashSet<>();
        int totalThread = 1000;
        Thread[] threads = new Thread[totalThread];

        for( int i = 0; i < threads.length; i++ ) {
            int j = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    Singleton instance = Singleton.INSTANCE;

                    resultSet.add(Integer.toString(instance.hashCode()));
                    printSingletonInstance(j, instance);

                    System.out.println("Thread " + j + " - value = " + instance.getValue());
                    instance.setValue(j);
                    System.out.println("Thread " + j + " - value = " + instance.getValue());
                }
            });
        }

        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("------");
        System.out.println("The resultSet of 'Instance Hash Code': " + Collections.singletonList(resultSet));
        System.out.println("Total running time: " + (endTime - startTime) + " ms");
        Singleton instance = Singleton.INSTANCE;
        System.out.println(" Final value = " + instance.getValue());
    }

    private static void printSingletonInstance(int j, Singleton instance) {
        System.out.println("Thread " + (j + 1) + " - " + instance.toString() + " - Hash Code: " + instance.hashCode());
    }
}
