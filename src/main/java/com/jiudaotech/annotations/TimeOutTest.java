package com.jiudaotech.annotations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class TimeOutTest {

    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void timeoutTest() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }
}
