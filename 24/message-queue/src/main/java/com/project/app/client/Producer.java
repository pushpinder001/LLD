package com.project.app.client;

import com.project.app.broker.MessageBroker;
import com.project.app.model.Message;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Producer {
    final MessageBroker broker;

    public void publish(Message message) {
        System.out.println("Publishing message " + message);
        broker.publish(message);
    }
}
