package Java.Singleton.LazyWithSynchronizedMethod;

// 线程安全的 “懒汉”式 单例
// 同步 整个方法，相比 双重检查锁定(DCL)，效率较低。
// 因为这种方式在 getInstance() 方法上加了 同步锁，所以在 多线程 情况下会造成 线程阻塞，
// 把大量的线程锁在外面，只有一个线程执行完毕才会执行下一个线程。
public class Singleton {
    private static Singleton instance;
    private Singleton() {
    }

    // 同步 整个方法 - synchronized the method
    public static synchronized Singleton getInstance() {
        if ( instance == null ) {
            instance = new Singleton();
        }
        return instance;
    }
}
