package com.pts62.common.finland.communication

import com.rabbitmq.client.*
import java.io.IOException

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

    fun readMessage(routingKey: String, callback: IQueueSubscribeCallback) {
        val consumer = object : DefaultConsumer(channel) {
            @Throws(IOException::class)
            override fun handleDelivery(consumerTag: String?, envelope: Envelope,
                                        properties: AMQP.BasicProperties?, body: ByteArray?) {
                val message = String(body!!, QueueConstants.DefaultCharset)
                println(" [x] Received '" + envelope.routingKey + "':'" + message + "'")
                callback.onMessageReceived(message)
            }
        }
        this.channel.basicConsume(routingKey, consumer)
    }

    fun readMessage(routingKey: String, callback: (msg:String) -> Unit) {
        channel.exchangeDeclare(QueueConstants.RekeningRijdenExchange, "topic")
        val queueName = channel.queueDeclare().queue

        channel.queueBind(queueName, QueueConstants.RekeningRijdenExchange, routingKey)

        val consumer = object : DefaultConsumer(channel) {
            @Throws(IOException::class)
            override fun handleDelivery(consumerTag: String?, envelope: Envelope,
                                        properties: AMQP.BasicProperties?, body: ByteArray?) {
                val message = String(body!!, QueueConstants.DefaultCharset)
                callback(message)
            }
        }
        channel.basicConsume(queueName, true, consumer)
    }
}