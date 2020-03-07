package Java.JavaForkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinTest {
    long max = 100000000000L;

    @Test
    public void test1() {
        System.out.println("------ ForkJoin 框架 ------");
        Instant startTime = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, max);
        Long sum = pool.invoke(task);

        Instant endTime = Instant.now();
        System.out.println(sum);
        System.out.println("Cost Time: " + Duration.between(startTime, endTime).toMillis());


        System.out.println("------ Common For Looping ------");
        long sum2 = 0L;
        Instant startTime2 = Instant.now();

        for( long i = 0; i <= max; i++ ) {
            sum2 += i;
        }

        Instant endTime2 = Instant.now();
        System.out.println(sum2);
        System.out.println("Cost Time: " + Duration.between(startTime2, endTime2).toMillis());


        System.out.println("------ Java 8 并行流 ------");
        Instant startTime3 = Instant.now();

        long sum3 = LongStream.rangeClosed(0, max)
                                .parallel()
                                .reduce(0, Long::sum);

        Instant endTime3 = Instant.now();
        System.out.println(sum3);
        System.out.println("Cost Time: " + Duration.between(startTime3, endTime3).toMillis());

        // long:
        // 9,223,372,036,854,775,807
        // 500000000500000000
    }
}
