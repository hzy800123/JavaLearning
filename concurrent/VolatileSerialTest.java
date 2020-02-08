package Java.concurrent;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.*;

/*
 * --- Solution 1 ---
 * 利用关键字 volatile 的 有序性。
 * private static volatile int x = 0;
 * private static volatile int y = 0;
 *
 * a = y;       // Volatile Load, Normal Store
 * x = 1;       // Volatile Store
 * 以上两行代码，分别是 Volatile Load, Normal Store, Volatile Store 三个动作。
 * Volatile 修饰的变量 Volatile Load, Volatile Store 之间给予了 LoadStore Barrier 内存的"读写屏障"，
 * 不允许指令重排，所以达到了 有序性 的目的。
 *
 *
 * --- Solution 2 ---
 * Volatile 可以保证有序性，就是增加 内存屏障，防止指令重排，也可以采用手动 加屏障 的方式，来阻止指令重排。
 * 利用 sun.misc.Unsafe 这个类。
 * unsafe.storeFence()
 *
 * 相关链接：
 * https://iambigboss.top/post/58125_1_1.html
 * https://www.cnblogs.com/chenpi/p/5389254.html
 *
 */

public class VolatileSerialTest {
    private static int x = 0;
    private static int y = 0;
    private static int a = 0;
    private static int b = 0;
    // private static volatile int x = 0;       // Solution 1
    // private static volatile int y = 0;       // Solution 1

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Set<String> resultSet = new HashSet<>();
        Map<String, Integer> resultMap = new HashMap<>();
        int resultCount = 0;

        // Solution 2 ( -- Start --- )
        // 通过反射得到theUnsafe对应的Field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        // 设置该Field为可访问
        field.setAccessible(true);
        // 通过Field得到该Field对应的具体对象，传入null是因为该Field为static的
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);
        // Solution 2 ( --- End --- )

        System.out.println("Calculation Start !");
        for (int i = 0; i < 10000000; i++ ) {
            x = 0;
            y = 0;
            resultMap.clear();

            Thread one = new Thread( new Runnable() {
                @Override
                public void run() {
                    a = y;
                    // unsafe.storeFence();    // Solution 2
                    x = 1;
                    resultMap.put("a", a);
                }
            });

            Thread two = new  Thread( new Runnable() {
                @Override
                public void run() {
                    b = x;
                    // unsafe.storeFence();    // Solution 2
                    y = 1;
                    resultMap.put("b", b);
                }
            });

            one.start();
            two.start();
            one.join();
            two.join();

            resultSet.add("( a=" + resultMap.get("a") + ", " + "b=" + resultMap.get("b") + " )");
            if ( resultSet.size() > resultCount ) {
                System.out.print("Calculation count: " + (i + 1) + " - ");
                System.out.println("The resultSet: " + Arrays.asList(resultSet));
                System.out.println("Calculation continues...");
                // System.out.println(Collections.singletonList(resultSet));
                resultCount = resultSet.size();
            }
        }
        System.out.println("Calculation Complete !");
    }
}
