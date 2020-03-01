package Java.MultiThreadInterview1A2B3C.CountDownLatch;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class TestLambda {
    int num = 0;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            System.out.println("test" + (num++) );
        }
    };

    Thread t1 = new Thread(r);

    Thread t2 = new Thread( () -> System.out.println("test !"));

    Thread t3 = new Thread( () -> System.out.println("test") );

    List<String> list = new ArrayList<>();

    Thread ta= new Thread(new Runnable () {
        @Override
        public void run() {
            System.out.println("test" + (num++));
        }
    });

    Thread tb = new Thread( () -> {
        System.out.println("test");
    });

    Comparator<Integer> com = (x, y) -> {
        System.out.println("test");
        return Integer.compare(x, y);
    };

}
