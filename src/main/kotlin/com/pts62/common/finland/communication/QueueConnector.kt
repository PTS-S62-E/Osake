package com.pts62.common.finland.communication

import com.rabbitmq.client.*
import java.io.IOException

class QueueConnector(
        var config : QueueConfig = QueueConfig()
) {
    var connection: Connection
        private set
    var channel : Channel
        private set


    init {
        val connectionFactory = ConnectionFactory()
        connectionFactory.host = config.QueueAddress
        this.connection = connectionFactory.newConnection()

        this.channel = this.connection.createChannel()

        this.channel.exchangeDeclare(config.RekeningRijdenExchange, BuiltinExchangeType.TOPIC)
    }

    fun publishMessage(routingKey:String, message:String) {
        this.channel.basicPublish(config.RekeningRijdenExchange, routingKey, null, message.toByteArray(config.DefaultCharset))
    }

    fun readMessage(routingKey: String, callback: IQueueSubscribeCallback) {
        channel.exchangeDeclare(config.RekeningRijdenExchange, "topic")
        val queueName = channel.queueDeclare().queue

        channel.queueBind(queueName, config.RekeningRijdenExchange, routingKey)

        val consumer = object : DefaultConsumer(channel) {
            @Throws(IOException::class)
            override fun handleDelivery(consumerTag: String?, envelope: Envelope,
                                        properties: AMQP.BasicProperties?, body: ByteArray?) {
                val message = String(body!!, config.DefaultCharset)
                callback.onMessageReceived(message)
            }
        }
        channel.basicConsume(queueName, true, consumer)
    }

    fun readMessage(routingKey: String, callback: (msg:String) -> Unit) {
        this.readMessage(routingKey, object : IQueueSubscribeCallback {
            override fun onMessageReceived(message: String) {
                callback(message)
            }
        })
    }
}