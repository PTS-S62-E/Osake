package com.pts62.common.finland.communication.test

import com.pts62.common.finland.communication.QueueConstants
import com.rabbitmq.client.ConnectionFactory
import java.util.*


object QueueSender {

    @JvmStatic
    fun main(argv: Array<String>) {

        val factory = ConnectionFactory()
        factory.host = QueueConstants.QueueAddress
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        channel.exchangeDeclare(QueueConstants.RekeningRijdenExchange, "topic")

        val scanner = Scanner(System.`in`)
        while (true) {
            val s: String = scanner.nextLine() ?: break
            val strings = s.split(" ")
            if (strings.size <= 1) {
                println("Enter a message")
                continue
            }
            val message = strings.drop(1).joinToString("")
            val routingKey = strings[0]

            channel.basicPublish(QueueConstants.RekeningRijdenExchange, routingKey, null, message.toByteArray(QueueConstants.DefaultCharset))
            println(" [x] Sent $routingKey:$message")
        }

        connection.close()
    }

}