package Java.CacheLinePadding;

// 伪共享 (False Sharing), 解决方案－神奇的"缓存行填充" (Cache Line Padding):
// URL: http://ifeve.com/disruptor-cacheline-padding/
//
// 你会看到 Disruptor 消除这个问题，至少对于缓存行大小是64字节或更少的处理器架构来说是这样的,
// 通过增加补全来确保ring buffer的序列号不会和其他东西同时存在于一个缓存行中。
// Quoted from 'Disruptor':
//	 public long p1, p2, p3, p4, p5, p6, p7;      // cache line padding
//   private volatile long cursor = INITIAL_CURSOR_VALUE;
//   public long p8, p9, p10, p11, p12, p13, p14; // cache line padding
// Unquoted
// 因此没有伪共享，就没有和其它任何变量的意外冲突，没有不必要的缓存未命中。
// 典型的 空间 换 时间 ! ! !
public class CacheLinePadding {
    private  static class Padding {
        public volatile long p1, p2, p3, p4, p5, p6, p7;
    }

    private static class T extends Padding {
        public volatile long x = 0L;
    }

//    private static class T {
//        public volatile long x = 0L;
//    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1000_000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1000_000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println((System.nanoTime() - start)/100_0000);
    }
}
