package Java.concurrent;

import java.util.*;

public class VolatileSerialTest {
    static int x = 0;
    static int y = 0;
//    static volatile int x = 0;
//    static volatile int y = 0;

    public static void main(String[] args) throws InterruptedException {
        Set<String> resultSet = new HashSet<>();
        Map<String, Integer> resultMap = new HashMap<>();
        int resultCount = 0;

        for (int i = 0; i < 1000000; i++ ) {
            x = 0;
            y = 0;
            resultMap.clear();

            Thread one = new Thread( new Runnable() {
                public void run() {
                    int a = y;
                    x = 1;
                    resultMap.put("a", a);
                }
            });

            Thread two = new  Thread( new Runnable() {
                public void run() {
                    int b = x;
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
                System.out.println(Arrays.asList(resultSet));
                // System.out.println(Collections.singletonList(resultSet));
                resultCount = resultSet.size();
            }
        }
    }
}
