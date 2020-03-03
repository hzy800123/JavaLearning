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

    @Test
    public void test2() {
        String str = " Hello Java 8 - Lambda !";
        System.out.println(str);

        String trimStr = strHandler(str, (strTmp) -> strTmp.trim());
        System.out.println(trimStr);

        String upperCaseStr = strHandler(str, (strTmp) -> strTmp.toUpperCase());
        System.out.println(upperCaseStr);

        String subStr = strHandler(str, (strTmp) -> strTmp.substring(7));
        System.out.println(subStr);
    }

    @Test
    public void test3() {
        operationOnTwoNumbers(200L, 300L, (x, y) -> x + y);
        operationOnTwoNumbers(200L, 300L, (x, y) -> x * y);
    }

    // Requirement:
    // Process the String with multiple method
    public String strHandler(String str, MyFunction mf) {
        return mf.getValue(str);
    }

    // Requirement:
    // Process for 2 'Long' numbers
    public void operationOnTwoNumbers(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }
}
