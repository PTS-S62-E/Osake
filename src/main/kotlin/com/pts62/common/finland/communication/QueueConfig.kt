package com.pts62.common.finland.communication

import java.nio.charset.Charset

class QueueConfig(
        val QueueAddress: String = "teunwillems.nl",
        val RekeningRijdenExchange: String = "REKENINGRIJDEN_EXCHANGE",
        val DefaultCharset: Charset = Charset.forName("UTF-8")
)