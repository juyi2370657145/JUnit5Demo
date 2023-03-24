package com.jiudaotech.annotations;

import org.junit.jupiter.api.*;

@TestClassOrder(ClassOrderer.OrderAnnotation.class)
public class ClassOrderTest {
    @Nested
    @Order(1)
    class OderTest1{
        @Test
        void test() throws Exception{
            Thread.sleep(1000);
            System.out.println("1");
        }
    }

    @Nested
    @Order(2)
    class OrderTest2{
        @Test
        void test2 (){
            System.out.println("2");
        }
    }
}
