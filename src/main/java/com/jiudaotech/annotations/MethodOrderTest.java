package com.jiudaotech.annotations;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//该注解使用在类上面
public class MethodOrderTest {
    @Test
    @Order(1)//该注解使用在方法上
    void test() throws Exception{
        Thread.sleep(1000);
        System.out.println("1");
    }
    @Test
    @Order(2)
    void test2() {
        System.out.println("2");
    }
}
