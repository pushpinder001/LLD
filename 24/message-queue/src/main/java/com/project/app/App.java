package com.project.app;

import com.project.app.broker.MessageBroker;
import com.project.app.client.Consumer;
import com.project.app.client.Producer;
import com.project.app.model.Message;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Random random = new Random();
        MessageBroker messageBroker = new MessageBroker(new ArrayList<>(), new ArrayList<>(), new HashMap<>(),
                    new HashMap<>());
        for(int i=0; i<5; i++) {
            new Thread(()-> {
                Consumer consumer = new Consumer(messageBroker,  random.nextInt(1000));
            }).start();
        }

        for(int i=0; i<3; i++) {
            new Thread(()-> {
                Producer producer = new Producer(messageBroker);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                producer.publish(new Message(UUID.randomUUID().toString(), random.nextInt(100), random.nextInt(100)));
            }).start();
        }
    }
}
