package com.pts62.common.tests

import com.pts62.common.europe.facades.ISubInvoiceFacade
import org.junit.Assert
import org.junit.Test

class ISubInvoiceFacadeTest {

    @Test
    fun testConversion() {
        val facade = ISubInvoiceFacade("test", "test", false, "test", 950)
        Assert.assertEquals(facade.price, 9.50, 0.00)

        val facade2 = ISubInvoiceFacade("test", "test", false, "test", 12345)
        Assert.assertEquals(facade2.price, 123.45, 0.00)
    }

}