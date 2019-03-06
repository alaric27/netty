package io.netty.example.test;

import io.netty.util.concurrent.FastThreadLocal;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaiyanan
 * @date 2019/3/6 22:24
 */
public class FastThreadLocalTest {

    public final static AtomicInteger number = new AtomicInteger();

    public static void main(String[] args) throws Exception{


        for (int j=0 ;j<8;j++){
            new Thread(){
                @Override
                public void run() {
                    for (long i = 0;i< 2147483647L ; i++){

                        final FastThreadLocal<String> local = new FastThreadLocal<String>() {
                            @Override
                            protected String initialValue() throws Exception {
                                return "fad";
                            }
                        };
                        local.set("ddddd");
                        System.out.println(number.incrementAndGet());
                    }

                }
            }.start();
        }

        Thread.sleep(100000000000000000L);
    }
}
