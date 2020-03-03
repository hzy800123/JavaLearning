package Java.Java8_Lumbda;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/*
 * Java 8 内置的 四大核心 函数式接口:
 * (1)
 * Consumer<T>: 消费型 接口
 *      void accept(T t);
 *
 * (2)
 * Supplier<T>: 供给型 接口
 *      T get();
 *
 * (3)
 * Function<T, R>: 函数型 接口
 *      R apply(T t);
 *
 * (4)
 * Predicate<T>: 断言型 接口
 *      boolean test(T t);
 */
public class LumbdaTest2 {
    // Consumer<T> 消费型 接口
    @Test
    public void test1() {
        shopping(1000, (m) -> System.out.print("Shopping to cost " + m + " RMB."));
    }

    // Requirement: Consume some amount in shopping.
    public void shopping(double amount, Consumer<Double> consumer) {
        consumer.accept(amount);
    }

    // **************************************************************************************

    // Supplier<T> 供给型 接口
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int)(Math.random()*100));

        for(Integer num : numList) {
            System.out.println(num);
        }
    }

    // Requirement: Generate the Integer Numbers, and put into one List.
    public List<Integer> getNumList(int numCount, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < numCount; i++) {
           Integer e = supplier.get();
           list.add(e);
        }
        return list;
    }

    // **************************************************************************************

    // Function<T, R> 函数型 接口
    @Test
    public void test3() {
        String newStr = strHandler(" Hello Java 8 - Lambda !", (str) -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("Hello Java 8 - Lambda !", (str) -> str.substring(6, 12));
        System.out.println(subStr);
    }

    // Requirement: Process the String.
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    // **************************************************************************************

    //Predicate<T> 断言型 接口
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "Java8", "Scala", "GO", "Python");

        // int count = 3; // Get from Control record from MySQL DB ???
        // list = filterStr(list, (str) -> str.length() > count);
        list = filterStr(list, (str) -> str.length() > 3);
        list.forEach(System.out::println);
    }

    // Requirement: Put the String into List, which are fulfilled the requirement.
    public List<String> filterStr(List<String> stringList, Predicate<String> predicate) {
        List<String> resultList = new ArrayList<>();

        for (String str : stringList) {
            if(predicate.test(str)) {
                resultList.add(str);
            }
        }
        return resultList;
    }
}
