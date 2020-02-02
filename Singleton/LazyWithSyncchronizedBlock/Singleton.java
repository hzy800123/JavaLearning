package Java.Singleton.LazyWithSyncchronizedBlock;

// 线程安全 的“懒汉”式 单例
// 同步代码块 实现，相比 双重检查锁(DCL)，效率较低。
// 它与同步 整个方法 类似。
public class Singleton {
    private static Singleton instance;
    private Singleton(){
    }

    public static Singleton getInstance(){
        synchronized (Singleton.class) {
            if ( instance == null ) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
