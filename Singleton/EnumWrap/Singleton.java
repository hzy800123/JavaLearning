package Java.Singleton.EnumWrap;

public class Singleton {
    private Singleton() {
    }

    // 枚举类型 是线程安全的，而且只会 装载一次。
    private enum EnumSingleton {
        INSTANCE;

        private final Singleton instance;
        
        EnumSingleton() {
            instance = new Singleton();
        }

        private Singleton getInstance() {
            return instance;
        }
    }

    public static Singleton getInstance() {
        return EnumSingleton.INSTANCE.getInstance();
    }
}
