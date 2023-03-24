package com.jiudaotech.annotations;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestTest {
    @Test
    void firstNested() {
        System.out.println("第一层--内嵌单元测试");
    }

    @Nested
    class Nested1 {

        @Test
        void secondNested() {
            System.out.println("第二层--内嵌单元测试");
        }

        @Nested
        class Nested2 {

            @Test
            void thirdNested() {
                System.out.println("第三层--内嵌单元测试");
            }
        }
    }
}
