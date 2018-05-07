package com.pts62.common.finland.communication

import java.nio.charset.Charset

class QueueConstants {
    companion object {
        @JvmStatic
        val QueueAddress = "teunwillems.nl"
        @JvmStatic
        val AntaMinenQueue = "AntaMinenQueue"
        @JvmStatic
        val RegistrationQueue = "RegistrationQueue"
        @JvmStatic
        val RekeningRijdenExchange = "REKENINGRIJDEN_EXCHANGE"
        @JvmStatic
        val DefaultCharset = Charset.forName("UTF-8")
    }
}