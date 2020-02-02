package Java.Singleton.HungerSafe;

// 线程安全的 “饿汉式” 单例
public class Singleton {
    // 指向自己实例的 私有静态引用，主动创建
    private static Singleton instance = new Singleton();

    // 私有的构造方法
    private Singleton() {
    }

    // 以 自己实例 为返回值的 静态的公有方法，静态工厂方法
    public static Singleton getInstance() {
        return instance;
    }
}
