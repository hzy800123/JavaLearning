package Java.Singleton.Enum;

// 基于 枚举类型 实现的单例，“饿汉”式 单例。（枚举类型 不支持 延迟加载）
// 枚举类型 是线程安全的，而且只会 装载一次。
//
// 当一个Java类 第一次被真正使用到的时候，资源被初始化，
// Java类的加载和初始化过程都是 线程安全的，
// 因为虚拟机在 加载枚举类的时候，会使用 ClassLoader 的 loadClass 方法，
// 而这个方法 使用了 同步代码块 保证了线程安全。所以，创建一个 enum 类型是线程安全的。
//
// 解决 单例 的并发问题，主要解决的就是 初始化过程中的 线程安全 问题。
public enum Singleton {
    INSTANCE;

    int value;

    public int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }
}
