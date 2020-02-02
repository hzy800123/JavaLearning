package Java.Singleton.Enum;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SingletonTest {
    public static void main(String[] args) {
        Set<String> resultSet = new HashSet<>();
        int total = 1000;

        Singleton instance2 = Singleton.INSTANCE;
        System.out.println("value = " + instance2.getValue());
        instance2.setValue(2);
        System.out.println("value = " + instance2.getValue());
        System.out.println("instance2 Hash Code: " + instance2.hashCode());

        long startTime = System.currentTimeMillis();
        for( int i = 0; i < total; i++ ){
            Singleton instance = Singleton.INSTANCE;

            resultSet.add(Integer.toString(instance.hashCode()));
            printSingletonInstance(i, instance);
        }

        long endTime = System.currentTimeMillis();
        //判断是否是 同一个对象
        System.out.println("------");
        System.out.println("The resultSet of 'Instance Hash Code': " + Collections.singletonList(resultSet));
        System.out.println("Total running time: " + (endTime - startTime) + " ms");
    }

    private static void printSingletonInstance(int i, Singleton instance) {
        System.out.println("Thread " + (i + 1) + " - " + instance.toString() + " - Hash Code: " + instance.hashCode());
    }
}
