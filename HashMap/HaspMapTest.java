package Java.HashMap;

import java.util.HashMap;
import java.util.Map;

// HashMap
// URL: https://blog.csdn.net/qunqunstyle99/article/details/86500028
// 对 Key 求 Hash 值，然后再计算下标
// 如果没有碰撞，直接放入桶中（碰撞的意思是计算得到的 Hash 值相同，需要放到同一个 bucket 中）
// 如果碰撞了，以链表的方式链接到后面
// 当链表的长度 大于 8 ( 即 >= 9 ) 的时候，会使用红黑树；如果链表长度很短的话，根本不需要引入红黑树，引入反而会慢。
// Note: 阀值（TREEIFY THRESHOLD == 8）
//
// 在Java 8 之前，HashMap和其他基于map的类都是通过链地址法解决冲突，它们使用单向链表来存储相同索引值的元素。
// 在最坏的情况下，这种方式会将HashMap的get方法的性能从 O(1) 降低到 O(n)。
// 在Java 8 开始，为了解决在频繁冲突时hashmap性能降低的问题，Java 8中使用 平衡树（红黑树） 来替代链表存储冲突的元素。
// 这意味着我们可以将最坏情况下的性能从 O(n) 提高到 O(logn)。
public class HaspMapTest {
    private String name;
    private int size;

    public HaspMapTest(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        HaspMapTest that = (HaspMapTest) o;
//        return size == that.size &&
//                name.equals(that.name);
        return false;
    }

    @Override
    public int hashCode() {
        //return Objects.hash(name, size);
        return size;
    }


    public static void main(String[] args) {
        Map<HaspMapTest, String> map = new HashMap<>(64);
        HaspMapTest haspMapTest1 = new HaspMapTest("Key_1", 1);
        HaspMapTest haspMapTest2 = new HaspMapTest("Key_2", 2);
        HaspMapTest haspMapTest3 = new HaspMapTest("Key_3", 2);
        HaspMapTest haspMapTest4 = new HaspMapTest("Key_4", 2);
        HaspMapTest haspMapTest5 = new HaspMapTest("Key_5", 2);
        HaspMapTest haspMapTest6 = new HaspMapTest("Key_6", 2);
        HaspMapTest haspMapTest7 = new HaspMapTest("Key_7", 2);
        HaspMapTest haspMapTest8 = new HaspMapTest("Key_8", 2);
        HaspMapTest haspMapTest9 = new HaspMapTest("Key_9", 2);
        HaspMapTest haspMapTest10 = new HaspMapTest("Key_10", 2);

        map.put(haspMapTest1, "Value_1");
        map.put(haspMapTest2, "Value_2");
        map.put(haspMapTest3, "Value_3");
        map.put(haspMapTest4, "Value_4");
        map.put(haspMapTest5, "Value_5");
        map.put(haspMapTest6, "Value_6");
        map.put(haspMapTest7, "Value_7");
        map.put(haspMapTest8, "Value_8");
        map.put(haspMapTest9, "Value_9");
        map.put(haspMapTest10, "Value_10");

        String resultValueinHashMap = map.get(haspMapTest3);

        System.out.println("result Value in haspMapTest3 = " + resultValueinHashMap);


    }
}
