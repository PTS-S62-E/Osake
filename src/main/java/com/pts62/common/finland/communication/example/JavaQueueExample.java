package com.pts62.common.finland.communication.example;

import com.pts62.common.finland.communication.IQueueSubscribeCallback;
import com.pts62.common.finland.communication.QueueConnector;
import org.jetbrains.annotations.NotNull;

public class JavaQueueExample {

    private QueueConnector connector = new QueueConnector();

    private void readQueue() {
        connector.readMessage("some.routing.*", new IQueueSubscribeCallback() {
            @Override
            public void onMessageReceived(@NotNull String message) {
                System.out.println(message);
            }
        });

        connector.readMessage("some.routing.*", (IQueueSubscribeCallback) System.out::println);
    }

    private void writeQueue() {
        connector.publishMessage("some.routing.key", "asdf");
    }

}
