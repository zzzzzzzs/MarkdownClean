package com.me.markdown;

import org.springframework.util.StopWatch;

/**
 * @author zs
 * @date 2021/11/24
 */
public class StopWatchTest {
    public static void main(String[] args) throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        Thread.sleep(1000);
        sw.stop();
        System.out.println(sw.getTotalTimeMillis());
    }
}
