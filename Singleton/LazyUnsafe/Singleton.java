package Java.Singleton.LazyUnsafe;

// 线程不安全的 “懒汉”式 单例
// 因为当 线程1 执行到判断instance == null 的时候 (Code 1)，被CPU切换到 线程2 开始执行。
// 这时候 线程2 会创建一个新的对象 (Code 1, Code 2)，
// 然后 线程1 被CPU切换回来又继续会创建另一个新的对象 (Code 2)。
public class Singleton {
    private static Singleton instance;
    private Singleton() {
    }

    public static Singleton getInstance() {
        if ( instance == null ) {           // Code 1
            instance = new Singleton();     // Code 2
        }
        return instance;
    }
}
