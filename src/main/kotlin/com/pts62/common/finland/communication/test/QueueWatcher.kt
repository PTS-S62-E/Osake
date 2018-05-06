import com.pts62.common.finland.communication.QueueConstants
import com.rabbitmq.client.*

import java.io.IOException
import java.util.*

object QueueWatcher {

    @JvmStatic
    fun main(argv: Array<String>) {

        System.out.println("What queue should be watched?")
        val inputArgs = Scanner(System.`in`).nextLine()

        val factory = ConnectionFactory()
        factory.host = QueueConstants.QueueAddress
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        channel.exchangeDeclare(QueueConstants.RekeningRijdenExchange, "topic")
        val queueName = channel.queueDeclare().queue

        channel.queueBind(queueName, QueueConstants.RekeningRijdenExchange, inputArgs)
        println(" [*] Waiting for messages. To exit press CTRL+C")

        val consumer = object : DefaultConsumer(channel) {
            @Throws(IOException::class)
            override fun handleDelivery(consumerTag: String?, envelope: Envelope,
                                        properties: AMQP.BasicProperties?, body: ByteArray?) {
                val message = String(body!!, QueueConstants.DefaultCharset)
                println(" [x] Received '" + envelope.routingKey + "':'" + message + "'")
            }
        }
        channel.basicConsume(queueName, true, consumer)
    }
}