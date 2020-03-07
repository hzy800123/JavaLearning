package Java.JavaForkJoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THRESHHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if(length <= THRESHHOLD) {
            long sum = 0;

            for(long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;

            ForkJoinCalculate left = new ForkJoinCalculate(start, middle);
            left.fork();    // 拆分子任务，同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(middle+1, end);
            right.fork();   // 拆分子任务，同时压入线程队列

            return left.join() + right.join();
        }
    }
}
