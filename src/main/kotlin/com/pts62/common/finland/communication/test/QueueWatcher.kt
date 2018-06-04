import com.pts62.common.finland.communication.QueueConfig
import com.rabbitmq.client.*

import java.io.IOException
import java.util.*

object QueueWatcher {

    @JvmStatic
    fun main(argv: Array<String>) {

        System.out.println("What queue should be watched?")
        val inputArgs = Scanner(System.`in`).nextLine()

        val config = QueueConfig()

        val factory = ConnectionFactory()
        factory.host = config.QueueAddress
        val connection = factory.newConnection()
        val channel = connection.createChannel()

        channel.exchangeDeclare(config.RekeningRijdenExchange, "topic")
        val queueName = channel.queueDeclare().queue

        channel.queueBind(queueName, config.RekeningRijdenExchange, inputArgs)
        println(" [*] Waiting for messages. To exit press CTRL+C")

        val consumer = object : DefaultConsumer(channel) {
            @Throws(IOException::class)
            override fun handleDelivery(consumerTag: String?, envelope: Envelope,
                                        properties: AMQP.BasicProperties?, body: ByteArray?) {
                val message = String(body!!, config.DefaultCharset)
                println(" [x] Received '" + envelope.routingKey + "':'" + message + "'")
            }
        }
        channel.basicConsume(queueName, true, consumer)
    }
}