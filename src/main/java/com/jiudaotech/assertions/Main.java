package com.jiudaotech.assertions;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Main {

    private static Person person = new Person("Jane", "Doe");
    private static Calculator calculator = new Calculator();

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
                () -> assertEquals("Jane", person.getFirstName()),
                () -> assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void arrayEqualsTest() {
        // 测试会通过
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});

        // 测试不通过，因为元素顺序不一样
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 3, 2}, "Array Equal Test");

        // 测试不通过，因为包含元素长度不一致
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4}, () -> "Array Equal Test" + " Fail.");
    }

    @Test
    void doesNotThrow() {
        assertDoesNotThrow(() -> {
            // 将会抛出ArithmeticException
            // calculator.divide(1, 0);
            int i = calculator.add(1, 2);
            System.out.print(i);
        });
    }

    @Test
    void equalsTest() {
        assertEquals(1, 1);
        assertEquals(1, 1, "失败信息。");
        assertEquals(1, 1, () -> "使用lambda表达式方式" + "生成失败信息。");
    }

    @Test
    void falseTest() {
        assertFalse(1 > 2);
        assertFalse(1 > 2, "失败信息。");
        assertFalse(1 > 2, () -> "使用lambda表达式方式" + "生成失败信息。");
    }

    @Test
    void interfaceTest() {
        Person person = new Person("Junyi", "Zhang");
        assertInstanceOf(Object.class, person);
        assertInstanceOf(Object.class, person, "失败信息。");
        assertInstanceOf(Object.class, person, () -> "使用lambda表达式方式" + "生成失败信息。");
    }

    @Test
    void iterableEqualsTest() {
        Iterable<Integer> listOne = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Iterable<Integer> listTwo = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Iterable<Integer> listThree = new ArrayList<>(Arrays.asList(1, 2, 3));
        Iterable<Integer> listFour = new ArrayList<>(Arrays.asList(1, 2, 4, 3));

        //Test will pass
        assertIterableEquals(listOne, listTwo);

        //Test will fail
        assertIterableEquals(listOne, listThree);

        //Test will fail
        assertIterableEquals(listOne, listFour);
    }

    @Test
    void linesMatchTest() {
        List<String> list1 = Arrays.asList("one", "two", "three");
        List<String> list2 = Arrays.asList("one", "two", "three");

        Stream<String> stream1 = Stream.of("one", "two", "three");
        Stream<String> stream2 = Stream.of("one", "two", "three");

        assertLinesMatch(list1, list2);
        assertLinesMatch(stream1, stream2);
    }

    @Test
    void NotEqualsTest() {
        assertNotEquals(1, 2);
        assertNotEquals(1, 2, "失败信息。");
        assertNotEquals(1, 2, () -> "使用lambda表达式方式" + "生成失败信息。");
    }

    @Test
    void notNullTest() {
        assertNotNull(person);
        assertNotNull(person, "失败信息。");
        assertNotNull(person, () -> "使用lambda表达式方式" + "生成失败信息。");
    }

    @Test
    void sameTest() {
        Person personRef = Main.person;

        assertSame(personRef, Main.person);
        assertSame(personRef, Main.person, "失败信息。");
        assertSame(personRef, Main.person, () -> "使用lambda表达式方式" + "生成失败信息。");
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
                calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void throwsTest() {
        Exception exception = assertThrows(Exception.class, () ->
                calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void throwExactly() {
        // 替换异常类型测试将会失败 比如替换为Exception.class
        Exception exception = assertThrowsExactly(ArithmeticException.class, () ->
                calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutTest() {
        // 修改指定时间为100ms，测试就会失败。
        assertTimeout(Duration.ofMillis(300), () -> {
            Thread.sleep(200);
            System.out.println("after sleep");
        });
    }

    @Test
    void timeoutPreemptivelyTest() {
        // 修改指定时间为100ms，测试就会失败，并且控制台不会打印"after sleep"。
        assertTimeoutPreemptively(Duration.ofMillis(300), () -> {
            Thread.sleep(200);
            System.out.println("after sleep");
        });
    }

    @Test
    void trueTest() {
        assertTrue(1 == 1);
        assertTrue(1 == 1, "失败信息。");
        assertTrue(1 == 1, () -> "使用lambda表达式方式" + "生成失败信息。");
    }
}
