package com.jiudaotech.annotations;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

public class Main {

    @Test
    void test() {
        System.out.println("这是一次测试。");
    }

    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    public void parameterizedTest1(String string) {
        System.out.println(string);
        assertTrue(StringUtils.isNotBlank(string));
    }

    @RepeatedTest(10)
    public void repeatedTest() {
        System.out.println("RepeatedTest");
    }
}
