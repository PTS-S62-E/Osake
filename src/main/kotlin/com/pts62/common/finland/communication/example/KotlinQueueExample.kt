package com.pts62.common.finland.communication.example

import com.pts62.common.finland.communication.IQueueSubscribeCallback
import com.pts62.common.finland.communication.QueueConnector

class KotlinQueueExample {

    private val connector = QueueConnector()

    private fun readExample() {
        connector.readMessage("some.routing.*", { print(it) })
    }

    private fun writeExample() {
        connector.publishMessage("some.routing.key", "TestMessage")
    }

}