package Java.Singleton.Enum;

import java.util.Arrays;

// 枚举类型 是线程安全的，而且只会 装载一次。
public enum Singleton {
    INSTANCE;

    int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private Singleton() {
        System.out.println("Here !");
    }

    private final String[] songs = {
            "ABC", "CDE", "FGH"
    };

    public void printSongs() {
        System.out.println(Arrays.toString(songs));
    }
}
