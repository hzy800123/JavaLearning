package Java.WriteCombining;

// 合并写 (Write Combining)
// URL: http://ifeve.com/writecombining/
public class WriteCombining {
    public static final int ITERATIONS = Integer.MAX_VALUE;
    public static final int ITEMS = 1 << 24;
    public static final int MASK = ITEMS - 1;

    public static final byte[] arrayA = new byte[ITEMS];
    public static final byte[] arrayB = new byte[ITEMS];
    public static final byte[] arrayC = new byte[ITEMS];
    public static final byte[] arrayD = new byte[ITEMS];

    public static void main(String[] args) {
        for (int i = 1; i <=3; i++) {
            System.out.println(i + " SingleLoop duration (ns) = " + runCaseOne());
            System.out.println(i + " SplitLoop  duration (ns) = " + runCaseTwo());
            System.out.println("------");
        }
    }

    public static long runCaseOne() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while (--i != 0) {
            int slot = i & MASK;    // 位运算符
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
            arrayC[slot] = b;
            arrayD[slot] = b;
        }
        return System.nanoTime() - start;
    }

    public static long runCaseTwo() {
        long start = System.nanoTime();
        int i = ITERATIONS;

        while (--i != 0) {
            int slot = i & MASK;    // 位运算符
            byte b = (byte) i;
            arrayA[slot] = b;
            arrayB[slot] = b;
        }

        i = ITERATIONS;
        while (--i != 0) {
            int slot = i & MASK;    // 位运算符
            byte b = (byte) i;
            arrayC[slot] = b;
            arrayD[slot] = b;
        }
        return System.nanoTime() - start;
    }
}
