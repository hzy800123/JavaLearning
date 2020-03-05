package Java.Java8_Stream_API;

import Java.Java8_Lumbda.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class StreamAPITest5 {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "John", 30, 6000, Employee.Status.BUSY),
            new Employee(102, "Ken", 32, 6500, Employee.Status.BUSY),
            new Employee(103, "Jane", 28, 7000, Employee.Status.FREE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE),
            new Employee(105, "Mike", 33, 5000, Employee.Status.ONLEAVE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE),
            new Employee(104, "Kate", 28, 8000, Employee.Status.FREE)
    );

    /*
     * 1. 给定一个数字列表，返回一个有每个数的平方构成的列表。
     * E.g.
     * Input:   1, 2, 3, 4, 5
     * Output:  1, 4, 9, 16, 25
     */
    @Test
    public void test1() {
       Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
       Stream<Integer> resultNums = Arrays.stream(nums)
                                .map((x) -> x * x);
       resultNums.forEach(System.out::println);
    }

    /*
     * 2. 用Map和Reduce方法，数一数流中有多少个Employee。
     */
    @Test
    public void test2() {
        Optional<Integer> count = emps.stream()
                .map((e) -> 1)
//                .reduce(0, (x, y) -> x + y);
                .reduce(Integer::sum);
        System.out.println(count.get());
    }
}
