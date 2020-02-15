package Java.HashMap;

import java.util.HashMap;
import java.util.Map;

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
