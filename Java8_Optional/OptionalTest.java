package Java.Java8_Optional;

import Java.Java8_Lumbda.Employee;
import org.junit.Test;

import java.util.Optional;

// Optional 容器类的常用方法
public class OptionalTest {
    // Optional.of(T t) - 创建一个Optional实例
    @Test
    public void test1() {
        Optional<Employee> optional = Optional.of(new Employee());
        Employee emp = optional.get();
        System.out.println(emp);
    }

    // Optional.empty() - 创建一个空的Optional实例
    @Test
    public void test2() {
        Optional<Employee> optional = Optional.empty();
        System.out.println(optional.get());
    }

    // Optional.ofNullable(T t) - 若t不为null，创建Optional实例，
    // 否则创建空实例。
    // 它是2个方法的合体：
    // return value == null ? empty() : of(value);
    //
    // isPresent() - 判断是否包含值
    // orElse(T t) - 如果调用对象包含值，返回该值，否则返回t
    // orElseGet(Supplier s) - 如果调用对象包含值，返回该值，否则返回s 获取的值
    @Test
    public void test3() {
        Optional<Employee> optional = Optional.ofNullable(new Employee());
        if(optional.isPresent()) {
            System.out.println(optional.get());
            System.out.println("Not null");
        }
        System.out.println("------");

        Optional<Employee> optional2 = Optional.ofNullable(null);
        if(optional2.isPresent()) {
            System.out.println(optional2.get());
            System.out.println("Not null");
        } else {
            System.out.println("is null");
        }
        System.out.println("------");

        Optional<Employee> optional3 = Optional.ofNullable(null);
        Employee emp = optional3.orElse(new Employee(101, "John", 30, 6000, Employee.Status.BUSY));
        System.out.println(emp);
        System.out.println("------");

        int indicator = ((int)(Math.random() * 10)) % 2;
        System.out.println("indicator = " + indicator);
        Optional<Employee> optional4 = Optional.ofNullable(null);
        Employee emp2 = optional4.orElseGet(() -> {
            if (indicator == 0) {
                return new Employee(101, "John", 30, 6000, Employee.Status.BUSY);
            } else {
                return new Employee(102, "Ken", 32, 6500, Employee.Status.BUSY);
            }
        });
        System.out.println(emp2);
    }

    // map(Function f) - 如果有值对其处理，并返回处理后的Optional，
    //                   否则返回Optional.empty()
    // flatMap(Function mapper) - 与map类似，要求返回值必须是Optional
    @Test
    public void test4() {
        Optional<Employee> optional = Optional.ofNullable(new Employee(101, "John", 30, 6000, Employee.Status.BUSY));
        Optional<String> str = optional.map((e) -> e.getName());
        System.out.println(str.get());

        Optional<String> str2 = optional.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(str2.get());
    }

    // Exercise on Man and Goddess
    @Test
    public void test5() {
        Man man = new Man();
        String name = getGoddessName(man);
        System.out.println(name);
        System.out.println("------");

        Optional<NewMan> optional1 = Optional.ofNullable(null);
        String name1 = getGoddessName2(optional1);
        System.out.println(name1);
        System.out.println("------");

        Optional<NewMan> optional2 = Optional.ofNullable(new NewMan());
        String name2 = getGoddessName2(optional2);
        System.out.println(name2);
        System.out.println("------");

        Optional<Goddess> goddess = Optional.ofNullable(null);
        Optional<NewMan> optional3 = Optional.ofNullable(new NewMan(goddess));
        String name3 = getGoddessName2(optional3);
        System.out.println(name3);
        System.out.println("------");

        Optional<Goddess> goddess2 = Optional.ofNullable(new Goddess("Lao Shi"));
        Optional<NewMan> optional4 = Optional.ofNullable(new NewMan(goddess2));
        String name4 = getGoddessName2(optional4);
        System.out.println(name4);
    }

    public String getGoddessName2(Optional<NewMan> man) {
        Goddess goddess = man.orElse(new NewMan())
                                .getGoddess()
                                .orElse(new Goddess("Default Goddess !"));
        System.out.println(goddess);
        return goddess.getName();
    }

    public String getGoddessName(Man man) {
        if(man != null) {
            System.out.println("man is Not null !");
            Goddess goddess = man.getGoddess();

            if(goddess != null) {
                System.out.println("goddess is Not null !");
                return goddess.getName();
            }
        }
        return "Default Goddess !";
    }
}
