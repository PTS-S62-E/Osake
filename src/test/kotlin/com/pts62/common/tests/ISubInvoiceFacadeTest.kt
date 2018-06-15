package com.pts62.common.tests

import com.pts62.common.europe.facades.ISubInvoiceFacade
import org.junit.Assert
import org.junit.Test
import java.time.LocalDateTime

class ISubInvoiceFacadeTest {

    @Test
    fun testConversion() {
        val facade = ISubInvoiceFacade("test", "test", false, "test", 950, "abcd-1234-efgh-5678")
        Assert.assertEquals(facade.price, 9.50, 0.00)

        val facade2 = ISubInvoiceFacade("test", "test", false, " test", 12345, "abcd-1234-efgh-5678")
        Assert.assertEquals(facade2.price, 123.45, 0.00)
    }

    @Test
    fun testDates() {
        val facade = ISubInvoiceFacade("test", "test", false, "2018-02-01T11:22:33", 950, "abcd-1234-efgh-5678")
        Assert.assertEquals(facade.getInvoiceAsLocalDateTime(), LocalDateTime.of(2018, 2, 1, 11, 22, 33))
    }

}