package Java.Java8_Stream_API;

import Java.Java8_Lumbda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

// 查找 与 匹配
// allMatch - 检查是否匹配 所有元素
// anyMatch - 检查是否至少匹配 一个元素
// nonMatch - 检查是否没有匹配 所有元素
// findFirst - 返回第一个元素
// findAny - 返回当前流的任意元素
// count - 返回流中元素的总个数
// max - 返回流中的最大值
// min - 返回流中的最小值
public class StreamAPITest3 {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "John", 30, 6000, Employee.Status.BUSY),
            new Employee(102, "Ken", 32, 6500, Employee.Status.BUSY),
            new Employee(103, "Jane", 28, 7000, Employee.Status.FREE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE),
            new Employee(105, "Mike", 33, 5000, Employee.Status.ONLEAVE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE)
    );

    @Test
    public void test1() {
        // allMatch - 检查是否匹配 所有元素
        boolean b1 = emps.stream()
                            .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);
        System.out.println("------");

        // anyMatch - 检查是否至少匹配 一个元素
        boolean b2 = emps.stream()
                            .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);
        System.out.println("------");

        // nonMatch - 检查是否没有匹配 所有元素
        boolean b3 = emps.stream()
                            .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);
        System.out.println("------");

        // findFirst - 返回第一个元素
        Optional<Employee> optional = emps.stream()
                .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
//                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .findFirst();
        System.out.println(optional.get());
        System.out.println("------");

        // findAny - 返回当前流的任意元素
        Optional<Employee> optional2 = emps.stream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(optional2.get());
        System.out.println("------");

        Optional<Employee> optional3 = emps.parallelStream()
                .filter((e) -> e.getStatus().equals(Employee.Status.FREE))
                .findAny();
        System.out.println(optional3.get());
    }

    @Test
    public void test2() {
        // count - 返回流中元素的总个数
        Long count = emps.stream()
                            .count();
        System.out.println(count);
        System.out.println("------");

        // max - 返回流中的最大值
        Optional<Employee> optional = emps.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(optional.get());
        System.out.println("------");

        // min - 返回流中的最小值
        Optional<Double> optional2 = emps.stream()
                .map(Employee::getSalary)
                .min(Double::compare);
        System.out.println(optional2.get());
    }
}
