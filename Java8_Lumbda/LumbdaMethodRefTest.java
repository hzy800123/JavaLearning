package Java.Java8_Lumbda;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.*;

/*
 * 一 方法引用： 若Lambda体中的内容，有方法已经实现了，可以使用“方法引用”
 *           （可以理解为 方法引用 是Lambda表达式 的另外一种表现形式）
 *
 * 主要有三种 语法格式：
 *
 * 对象：：实例方法名 （实例方法，即 非静态方法）
 * 类：：静态方法名
 * 类：：实例方法名 （实例方法，即 非静态方法）
 *
 * 注意：
 * 1. Lambda 体中的 调用方法的 参数列表 和 返回值类型，
 *    要与 函数式接口中的 抽象方法的 参数列表 和 返回值类型 保持一致。
 * 2. 若 Lambda 参数列表 中的 第一个参数，是 实例方法的调用者，
 *    而且 第二个参数，是 实例方法的参数 时，
 *    可以使用 ClassName::Method
 *
 *
 * 二 构造器 引用
 *    格式：
 *    ClassName::new
 *
 * 注意：需要调用的 构造器的 参数列表，要与函数式接口的 抽象方法的 参数列表
 *       保持一致。
 *
 *
 * 三 数组 引用
 *    格式：
 *    Type[]::new
 *
 */
public class LumbdaMethodRefTest {
    // 对象：：实例方法名
    @Test
    public void test1() {
        Consumer<String> consumer = (x) -> System.out.println(x);

        PrintStream printStream = System.out;
        Consumer<String> consumer2 = printStream::println;

        Consumer<String> consumer3 = System.out::println;
        consumer3.accept("Lumbda Method Ref !");

        Employee emp = new Employee();
        Supplier<String> supplier =  () -> emp.getName();
        String str = supplier.get();
        System.out.println(str);

        Supplier<Integer> supplier2 = emp::getAge;
        Integer age = supplier2.get();
        System.out.println(age);
    }

    // -----------------------------------------------------------

    // 类：：静态方法名
    @Test
    public void test2() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator2 = Integer::compare;

        System.out.println(comparator2.compare(1, 2));
    }

    // -----------------------------------------------------------

    // 类：：实例方法名
    @Test
    public void test3() {
        BiPredicate<String, String> biPredicate = (str1, str2) -> str1.equals(str2);
        BiPredicate<String, String> biPredicate2 = String::equals;

        System.out.println(biPredicate.test("abc", "ABC"));
        System.out.println(biPredicate2.test("abc", "abc"));
    }

    // -----------------------------------------------------------

    // 构造器 引用
    @Test
    public void test4() {
        Supplier<Employee> supplier = () -> new Employee();
        System.out.println(supplier.get());
        System.out.println("----------");

        // 构造器方法 引用方式
        Supplier<Employee> supplier2 = Employee::new;
        Employee emp = supplier2.get();
        System.out.println(emp);

        Function<Integer, Employee> function = (id) -> new Employee(id);
        Function<Integer, Employee> function2 = Employee::new;
        Employee emp2 = function2.apply(101);
        System.out.println(emp2);

        BiFunction<String, Integer, Employee> biFunction = Employee::new;
        Employee emp3 = biFunction.apply("Test", 20);
        System.out.println(emp3);
    }

    // -----------------------------------------------------------

    // 数组 引用
    @Test
    public void test5() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] strs = function.apply(10);
        System.out.println(strs.length);

        Function<Integer, String[]> function2 = String[]::new;
        String[] strs2 = function2.apply(20);
        System.out.println(strs2.length);
    }
}
