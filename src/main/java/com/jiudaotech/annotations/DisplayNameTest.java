package com.jiudaotech.annotations;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("测试类展示名")
public class DisplayNameTest {

    @Test
    @DisplayName("测试方法展示名")
    public void function() {
        // ...
    }
}
