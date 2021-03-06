package Java.Java8_Stream_API;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPITestTransaction {

    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }


    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test1() {
        Stream<Transaction> stream = transactions.stream()
                                .filter((t) -> t.getYear() == 2011)
                                .sorted((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
//                                .sorted(Comparator.comparingInt(Transaction::getValue));
        stream.forEach(System.out::println);
    }

    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test2() {
        Stream<String> stream =transactions.stream()
                .map((t) -> t.getTrader().getCity())
                .distinct();
//                .collect(Collectors.toSet());
        stream.forEach(System.out::println);
    }

    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test3() {
        Stream<Trader> stream = transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName()))
//                .sorted(Comparator.comparing(Trader::getName))
                .distinct();
        stream.forEach(System.out::println);
    }

    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test4() {
        Stream<String> stream = transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
//                .sorted((t1, t2) -> t1.compareTo(t2))
//                .sorted(String::compareTo)
                .distinct();
        stream.forEach(System.out::println);
        System.out.println("-------------");

        String str = transactions.stream()
                .map(t -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.println(str);
        System.out.println("-------------");

        Stream<String> stream2 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(StreamAPITestTransaction::filterCharacter)
                .map(Object::toString)
//                .map(String::valueOf)
                .sorted(String::compareToIgnoreCase);
        stream2.forEach(System.out::print);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();

        for(Character ch : str.toCharArray()){
            list.add(ch);
        }
        return list.stream();
    }

    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test5() {
        boolean checking = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(checking);

        List<String> list = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Milan"))
                .map(t -> t.getTrader().getName())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(list);
    }

    //6. 打印生活在剑桥的交易员的所有交易额
    @Test
    public void test6() {
        Integer sum = transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(t -> t.getValue())
                .reduce(0, Integer::sum);
        System.out.println(sum);
        System.out.println("-------------");

        Stream<Transaction> stream = transactions.stream()
                    .filter(t -> t.getTrader().getCity().equals("Cambridge"));
        stream.forEach(
                (item) -> System.out.println(item.getTrader().getName() + " - " + item.getValue())
        );
    }

    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test7() {
        Optional<Integer> optional = transactions.stream()
                .max(Comparator.comparingInt(Transaction::getValue))
                .map(Transaction::getValue);
        System.out.println(optional.get());
    }

    //8. 找到交易额最小的交易
    @Test
    public void test8() {
        Optional<Transaction> optional = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
//                .min(Comparator.comparingInt(Transaction::getValue));
        System.out.println(optional.get());
    }
}