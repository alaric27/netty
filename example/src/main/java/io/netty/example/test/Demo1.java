package io.netty.example.test;

import io.netty.util.NettyRuntime;

/**
 * @author zhaiyanan
 * @date 2019/3/2 17:09
 */
public class Demo1 {
    public static void main(String[] args) {
        System.out.println(NettyRuntime.availableProcessors() * 2);
    }
}
