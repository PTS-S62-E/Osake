package com.pts62.common.finland.communication

import com.rabbitmq.client.BuiltinExchangeType
import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import java.nio.charset.Charset

class QueueConnector {
    var connection: Connection
        private set
    var channel : Channel
        private set

    init {
        val connectionFactory = ConnectionFactory()
        connectionFactory.host = QueueConstants.QueueAddress
        this.connection = connectionFactory.newConnection()

        this.channel = this.connection.createChannel()

        this.channel.exchangeDeclare(QueueConstants.RekeningRijdenExchange, BuiltinExchangeType.TOPIC)
    }

    fun publishMessage(routingKey:String, message:String) {
        this.channel.basicPublish(QueueConstants.RekeningRijdenExchange, routingKey, null, message.toByteArray(QueueConstants.DefaultCharset))
    }
}
