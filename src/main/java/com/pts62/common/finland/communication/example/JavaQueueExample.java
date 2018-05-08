package com.pts62.common.finland.communication.example;

import com.pts62.common.finland.communication.CommunicationBuilder;
import com.pts62.common.finland.communication.IQueueSubscribeCallback;
import com.pts62.common.finland.communication.QueueConnector;
import org.jetbrains.annotations.NotNull;

public class JavaQueueExample {

    private QueueConnector connector = new QueueConnector();

    private void readQueue() {
        CommunicationBuilder builder = new CommunicationBuilder()
                .setCountry("FI")
                .setApplication("Antaminen")
                .setMessage("TestMsg");

        connector.readMessage(builder.build(), new IQueueSubscribeCallback() {
            @Override
            public void onMessageReceived(@NotNull String message) {
                System.out.println(message);
            }
        });

        connector.readMessage(builder.build(), (IQueueSubscribeCallback) System.out::println);
    }

    private void writeQueue() {
        CommunicationBuilder builder = new CommunicationBuilder()
                .setCountry("FI")
                .setApplication("Antaminen")
                .setMessage("TestMsg");

        connector.publishMessage(builder.build(), "asdf");
    }

}
