package com.pts62.common.tests.finland.communication

import com.pts62.common.finland.communication.CommunicationBuilder
import org.junit.Assert
import org.junit.Test

class CommunicationBuilderTest {

    @Test
    fun CommunicationBuilderTest() {
        val builder = CommunicationBuilder()
        Assert.assertEquals("*.*.*", builder.build())

        builder.setCountry("FI")
        Assert.assertEquals("FI.*.*", builder.build())

        builder.setApplication("Antaminen")
        Assert.assertEquals("FI.Antaminen.*", builder.build())

        builder.setMessage("TESTMSG")
        Assert.assertEquals("FI.Antaminen.TESTMSG", builder.build())
    }

}