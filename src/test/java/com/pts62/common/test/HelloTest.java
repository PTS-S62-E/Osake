package com.pts62.common.test;

import com.pts62.common.CommonHello;
import org.junit.Assert;
import org.junit.Test;

public class HelloTest {

    @Test
    public void testHello() {
        Assert.assertEquals(CommonHello.getHello(), "hello");
    }

}
