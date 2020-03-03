package Java.Java8_Stream_API;

import Java.Java8_Lumbda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
 * 一 stream 的 三个 操作步骤：
 * 1. 创建 Steam
 * 2. 中间操作
 * 3. 终止操作（终端操作）
 */
public class StreamAPITest {
    // 创建 Stream
    @Test
    public void test1() {
        // 1. 可以通过 Collection 集合提供的的 Stream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2. 可以通过 Arrays 的静态方法 Stream() 获取数组流
        Employee[] emps = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(emps);

        // 3. 可以通过 Stream 类中的 静态方法 of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        // 4. 可以创建 无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);

        // 生成
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
    }

}
