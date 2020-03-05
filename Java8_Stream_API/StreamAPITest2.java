package Java.Java8_Stream_API;

import Java.Java8_Lumbda.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class StreamAPITest2 {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "John", 30, 6000),
            new Employee(102, "Ken", 32, 6500),
            new Employee(103, "Jane", 28, 7000),
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


    // Map: 映射
    //      接受Lambda，将元素转换成其他形式 或 提取信息。
    //      接收 一个函数 作为参数，该函数会被应用到 每个元素上，
    //      并将其映射成 一个新的元素。
    @Test
    public void test6() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);

        System.out.println("------");

        emps.stream()
                .map(Employee::getName)     // 类：：实例方法名
                .forEach(System.out::println);
    }


    // flatMap：扁平化 映射 (把 几个小的List 转换到一个 大的List)
    //          接收 一个函数 作为参数，将流中的每个值（小的集合/List) 都换成另一个流，
    //          然后把 所有流 连接成 一个流。
    //          E.g.
    //          [ [1,2,3], [4,5,6], [7,8,9] ] => [ 1,2,3,4,5,6,7,8,9 ]
    //
    // URL: https://www.jianshu.com/p/8d80dcb4e7e0
    //
    @Test
    public void test7() {
        List<String> list = Arrays.asList("aa", "bb", "cc");

        Stream<Stream<Character>> stream = list.stream()
                                                .map(StreamAPITest2::filterCharacter);

        stream.forEach((stm) -> {
            stm.forEach(System.out::println);
        });
        System.out.println("------");

        Stream<Character> stream2 = list.stream()
                                        .flatMap(StreamAPITest2::filterCharacter);

        stream2.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for(Character ch: str.toCharArray()) {
            list.add(ch);
        }

        return list.stream();
    }


    // 排序
    // sorted() -- 自然排序（Comparable）
    // sorted(Comparator com) -- 定制排序（Comparator）
    @Test
    public void test8() {
        List<String> list = Arrays.asList("ccc", "bbb", "aaa", "eee", "eee");

        // 自然排序（Comparable）
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("------");

        // 定制排序（Comparator）
        emps.stream()
                .sorted((e1, e2) -> {
                    if(e1.getAge() == e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }
                    return e1.getAge() - e2.getAge();
                }).forEach(System.out::println);
    }
}
