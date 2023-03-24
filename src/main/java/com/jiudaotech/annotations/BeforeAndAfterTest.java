package com.jiudaotech.annotations;

import org.junit.jupiter.api.*;

public class BeforeAndAfterTest {
    @Test
    public void test1() {
        System.out.println("Test1");
    }

    @Test
    public void test2() {
        System.out.println("Test2");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("Before Each.");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("After Each.");
    }

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before All.");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After All.");
    }
}
