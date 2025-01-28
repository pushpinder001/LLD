package com.project.app.client;

import com.project.app.broker.MessageBroker;
import com.project.app.model.Message;
import lombok.Getter;

public class Consumer {
    @Getter
    Integer id;
    MessageBroker broker;

    public Consumer(MessageBroker broker, Integer id) {
        this.broker = broker;
        this.id = id;

        this.broker.subscribe(this);
    }

    public void consume(Message message) throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Consumer " + id + " processing message " + message);
        broker.commit(this, message);
    }
}
