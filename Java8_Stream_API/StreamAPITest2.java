package Java.Java8_Stream_API;

import Java.Java8_Lumbda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPITest2 {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "John", 30, 6000),
            new Employee(102, "Ken", 32, 6500),
            new Employee(103, "Jane", 33, 7000),
            new Employee(104, "Kate", 28, 8000),
            new Employee(105, "Mike", 33, 5000),
            new Employee(104, "Kate", 28, 8000),
            new Employee(104, "Kate", 28, 8000)
    );

    // 中间操作
    // 筛选 filter: 排除某些元素
    // 内部迭代： 迭代操作由 Steam API 自动完成
    @Test
    public void test1() {
        // 中间操作： 不会执行任何操作
        Stream<Employee> stream = emps.stream()
                                    .filter((e) -> {
                                        System.out.println("------");
                                        System.out.println("Steam API - Middle Operation !");
                                        return e.getAge() > 30;
                                    });
        // 终止操作： 一次性执行 全部内容，即“惰性求值”
        stream.forEach(System.out::println);
    }

    // 外部迭代： 自己手动完成
    @Test
    public void test2() {
        Iterator<Employee> it = emps.iterator();

        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    // limit: 限制元素不超过指定的数量
    @Test
    public void test3() {
        emps.stream()
                .filter((e) -> {
                    System.out.println("------");
                    System.out.println("Middle Operation !"); // e.g. (a && b), (a || b)
                    return e.getSalary() > 5000;
                })
                .limit(2)
                .forEach(System.out::println);
    }

    // skip: 跳过元素，返回一个扔掉了前n个元素的流
    @Test
    public void test4() {
        emps.stream()
                .filter((e) -> e.getSalary() > 5000)
                .skip(2)
                .forEach(System.out::println);
    }

    // distinct: 去除重复，需要通过流所生成的元素的 hashCode() 和 equals() 方法
    //           进行比较是否重复。
    @Test
    public void test5() {
        emps.stream()
                .filter((e) -> e.getSalary() > 5000)
                .distinct()
                .forEach(System.out::println);
    }
}
