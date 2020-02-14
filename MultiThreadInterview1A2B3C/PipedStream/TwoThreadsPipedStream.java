package Java.MultiThreadInterview1A2B3C.PipedStream;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

// PipedInputStream 和 PipedOutputSteam - 是java语言中线程间传输数据的一种方式。
// 线程A使用 PipedOutputSteam 写数据进到管道Pipe里。（以 程序 为参照对象）
// 线程B使用 PipedInputStream 从Pipe里 接收数据。（以 程序 为参照对象）
// Thread A --(output) --> [ Pipe ] --(Input)--> Thread B
// URL: https://zhuanlan.zhihu.com/p/31352578
public class TwoThreadsPipedStream {
    static volatile int number = 0;

    public static void main(String[] args) throws IOException {
        PipedInputStream input1 = new PipedInputStream();
        PipedInputStream input2 = new PipedInputStream();
        PipedOutputStream output1 = new PipedOutputStream();
        PipedOutputStream output2 = new PipedOutputStream();

        input1.connect(output2);
        input2.connect(output1);

        String msg = "Your Turn";   // The length of 'msg' is 9 here.

        Thread t1 = new Thread(() -> {
            byte[] buffer = new byte[9];
            for( int i = 0; i < 26; i++ ) {
                try {
                    System.out.println(number+1);
                    output1.write(msg.getBytes());
                    input1.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            byte[] buffer = new byte[9];

            for( int i = 0; i < 26; i++ ) {
                try {
                    synchronized (TwoThreadsPipedStream.class) {
                        input2.read(buffer);
                        System.out.println(" -- " + (char) ((int) ('A') + number));
                        number++;
                        output2.write(msg.getBytes());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
