package Java.Singleton.LazyWithDCL;

// --- 使用了 volatile 关键字 ---
// volatile关键字的特性，它不仅可以使变量在多个线程之间可见，而且它还具有禁止JVM进行 指令重排序 的功能。
// volatile可以防止出现“指令重排序 之后，新创建的对象可能还没有 初始化完成” 的情况，否则 有些线程可能获得了空对象。
// 创建一个对象 的过程分为以下 3步：
// memory = allocate();         //1.分配对象的内存空间
// ctorInstance(memory);        //2.初始化对象
// instance = memory;           //3.设置instance指向刚分配的内存地址。
//
// --- 使用了 synchronized 关键字 ---
// 这种写法在 getSingleton() 方法中对singleton进行了 两次判空，
// 第一次是为了 不必要的同步，
// 第二次是 在singleton等于null的情况下才 创建实例。
//
// --- >= Java 1.5 supported ---
// 还有就是在 java1.4 及以前版本中，很多JVM对于volatile关键字的实现的问题，
// 会导致“双重检查加锁”的失败，
// 因此“双重检查加锁”机制只只能用在 java1.5 及以上的版本。( >= Java 1.5 Supported )
public class Singleton {
    private volatile static Singleton instance;
    private Singleton() {
    }

    public static Singleton getInstance() {
        if ( instance == null ) {
            synchronized (Singleton.class) {
                if ( instance == null ) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
