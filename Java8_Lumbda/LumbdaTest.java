package Java.Java8_Lumbda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LumbdaTest {

    List<Employee> emps = Arrays.asList(
            new Employee(101, "John", 30, 6000),
            new Employee(102, "Ken", 32, 6500),
            new Employee(103, "Jane", 33, 7000),
            new Employee(104, "Kate", 28, 8000),
            new Employee(105, "Mike", 33, 5000)
    );

    @Test
    public void test1() {
        Collections.sort(emps, (e1, e2) -> {
            if(e1.getAge() == e2.getAge()) {
                return Double.compare(e1.getSalary(), e2.getSalary());
            }
            return e1.getAge() - e2.getAge();
        });

        emps.forEach(System.out::println);
    }

}
