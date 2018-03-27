package com.pts62.common.finland.communication

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

class QueueConnector(
        queue : String
) {
    private val connection: Connection
    val channel : Channel

    init {
        val connectionFactory = ConnectionFactory()
        connectionFactory.host = QueueConstants.QueueAddress
        this.connection = connectionFactory.newConnection()

        this.channel = this.connection.createChannel()
        this.channel.queueDeclare(queue, false, false, false, null)
    }
}
