package Java.Java8_Stream_API;

import Java.Java8_Lumbda.Employee;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamAPITest4 {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "John", 30, 6000, Employee.Status.BUSY),
            new Employee(102, "Ken", 32, 6500, Employee.Status.BUSY),
            new Employee(103, "Jane", 28, 7000, Employee.Status.FREE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE),
            new Employee(105, "Mike", 33, 5000, Employee.Status.ONLEAVE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE)
    );

    // 规约
    // Reduce: 可以将流中元素 反复结合起来，得到 一个值。
    //
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        Integer sum = list.stream()
                .reduce(0, (x, y) -> x + y);
//                .reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println("------");

        // 因为没有 初始值0，所以可能reduce的结果为空
        // 所以需要返回Optional类型。
        Optional<Double> sum2 = emps.stream()
                .map(Employee::getSalary)       // 先 提取 工资
                .reduce(Double::sum);           // 再 规约 求和
        System.out.println(sum2.get());
    }


    // 收集
    // Collect: 将流转换为其他形式，接收一个Collector接口的实现，
    //          用于给Steam中的元素做 汇总 的方法。
    @Test
    public void test4() {
        List<String> list = emps.stream()
                                .map(Employee::getName)
                                .collect(Collectors.toList());
        System.out.println(list);
        System.out.println("------");

        Set<String> set = emps.stream()
                                .map(Employee::getName)
                                .collect(Collectors.toSet());
        System.out.println(set);
        System.out.println("------");

        HashSet<String> hashSet = emps.stream()
                                        .map(Employee::getName)
                                        .collect(Collectors.toCollection(HashSet::new));
        System.out.println(hashSet);
        System.out.println("------");
    }


    // 收集
    // collect
    @Test
    public void test5() {
        // 总数
        Long count = emps.stream()
                            .collect(Collectors.counting());
        System.out.println(count);
        System.out.println("------");

        // 平均值
        Double averageSalary = emps.stream()
                                    .collect(Collectors.averagingDouble(Employee::getSalary));

        System.out.println(averageSalary);
        System.out.println("------");

        // 总和
        Double sumSalary = emps.stream()
                                .collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sumSalary);
        System.out.println("------");

        // 最大值
        Optional<Employee> maxSalary = emps.stream()
                                .collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
//                                    .collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(maxSalary.get());
        System.out.println("------");

        // 最小值
        Optional<Double> minSalary = emps.stream()
                                        .map(Employee::getSalary)
                                        .collect(Collectors.minBy(Double::compare));
        System.out.println(minSalary.get());
    }


    // 收集
    // 分组: Collectors.groupingBy()
    @Test
    public void test6() {
        Map<Employee.Status, List<Employee>> resultMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(resultMap);
        System.out.println("------");

        resultMap.keySet().forEach(
                (k) -> {
                    System.out.println(k);
                    resultMap.get(k).forEach(System.out::println);
                    System.out.println("---");
                }
        );
    }

    // 分组： 多级分组 ( 理论上，可以无限地 多级分组 下去）
    // Collectors.groupingBy(ClassName::Method_1, Collectors.groupingBy(ClassName::Method_2))
    @Test
    public void test7() {
        Map<Employee.Status, Map<String, List<Employee>>> resultMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getStatus,
                            Collectors.groupingBy((e) -> {
                                if(e.getAge() <=30) {
                                    return "Junior";
                                } else {
                                    return "Senior";
                                }
                            })));
        System.out.println(resultMap);
        System.out.println("------");

        resultMap.keySet().forEach(
                (k) -> {
                    System.out.println("=== " + k + " ===");
                    resultMap.get(k).keySet().forEach(
                            (subKey) -> {
                                System.out.println(subKey);
                                resultMap.get(k).get(subKey).forEach(System.out::println);
                                System.out.println("---");
                            }
                    );
                }
        );
    }

    // 分区: 只能分为 2个区域
    // Collectors.partitioningBy(Predicate)     -> Predicate = True or False (2个区域)
    @Test
    public void test8() {
        Map<Boolean, List<Employee>> map = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getAge() > 30));
        System.out.println(map);
        System.out.println("------");

        map.keySet().forEach(
                (k) -> {
                    System.out.println("=== " + k + " ===");
                    map.get(k).forEach(System.out::println);
                    System.out.println("---");
                }
        );
    }

    // 统计
    // collect.(Collectors.summarizingDouble(ClassName::Method))
    // =>
    // collect.getAverage()
    // collect.getSum()
    // collect.getMax()
    @Test
    public void test9() {
        DoubleSummaryStatistics collect = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getAverage());
        System.out.println(collect.getSum());
        System.out.println(collect.getMax());
    }

    // 连接
    // .collect(Collectors.joining(","))
    @Test
    public void test10() {
        String jointNames = emps.stream()
                                .map(Employee::getName)
                                .collect(Collectors.joining(", ", "[ ", " ]"));
        System.out.println(jointNames);
    }
}
