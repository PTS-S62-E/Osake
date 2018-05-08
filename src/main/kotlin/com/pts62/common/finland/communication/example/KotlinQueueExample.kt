package com.pts62.common.finland.communication.example

import com.pts62.common.finland.communication.CommunicationBuilder
import com.pts62.common.finland.communication.QueueConnector

class KotlinQueueExample {

    private val connector = QueueConnector()

    fun readExample() {
        val builder = CommunicationBuilder()
                .setCountry("FI")
                .setApplication("Antaminen")
                .setMessage("TestMsg")

        connector.readMessage(builder.build(), { print(it) })
    }

    fun writeExample() {
        val builder = CommunicationBuilder()
                .setCountry("FI")
                .setApplication("Antaminen")
                .setMessage("TestMsg")

        connector.publishMessage(builder.build(), "TestMessage")
    }

}

fun main(args: Array<String>) {
    val e = KotlinQueueExample()
    e.readExample()
    e.writeExample()
}