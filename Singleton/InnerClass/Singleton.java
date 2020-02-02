package Java.Singleton.InnerClass;

// --- 线程安全 的“懒汉”式 单例， 使用了 内部类 来实现 ---
// 当 被调用 getInstance() 方法时 (Code 1)，会触发 SingletonHolder 类的初始化 (Code 2)。
// 由于 instance 是 SingletonHolder 类的成员变量，因此 JVM 调用 SingletonHolder 类的类构造器
// 对其进行初始化时，虚拟机会保证 一个类的类构造器 在多线程环境中 被正确地加锁、同步，
// 如果有 多个线程 同时去初始化一个类，那么只有 一个线程 去执行这个类的类构造器，其他线程需要阻塞等待，
// 直到 活动的线程 执行方法完毕。其他线程 之后也不会再次 进入/执行 类构造器。
// 因为 在同一个类加载器下，一个类型只会被初始化一次，因此保证了 单例。
// 执行顺序: Code 1 --> Code 2 --> Code 3
public class Singleton {
    private Singleton() {
    }

    public static Singleton getInstance() {     // Code 1
        return SingletonHolder.instance;        // Code 3
    }

    // 使用 静态 私有内部类，用时加载，也就是 延时加载
    private static class SingletonHolder {
        private static final Singleton instance = new Singleton();      // Code 2
    }
}
