package com.project.app.broker;

import com.project.app.client.Consumer;
import com.project.app.model.Message;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Map;

public class MessageBroker {
    final List<Consumer> consumers;
    final List<Message> unprocessedMessages;
    final Map<String, Message> underProcessingMessages;
    final Map<Integer, Integer> consumerIdToMessage;

    public MessageBroker(List<Consumer> consumers, List<Message> unprocessedMessages, Map<String, Message> underProcessingMessages, Map<Integer, Integer> consumerIdToMessage) {
        this.consumers = consumers;
        this.unprocessedMessages = unprocessedMessages;
        this.underProcessingMessages = underProcessingMessages;
        this.consumerIdToMessage = consumerIdToMessage;
    }

    public void publish(Message message) {
        synchronized (unprocessedMessages) {
            unprocessedMessages.add(message);
            System.out.println("New Message added");
            unprocessedMessages.notifyAll();
        }
    }

    @SneakyThrows
    public void commit(Consumer consumer, Message message) {
        synchronized(underProcessingMessages) {
            underProcessingMessages.remove(message.id());
        }

        consume(consumer);
    }

    @SneakyThrows
    public void subscribe(Consumer consumer) {
        System.out.println("Starting consumer" + consumer.getId());
        synchronized (consumers) {
            consumers.add(consumer);
        }
        consume(consumer);
    }

    @SneakyThrows
    private void consume(Consumer consumer)  {
        Message message = null;
        synchronized(unprocessedMessages) {
            while(unprocessedMessages.isEmpty()) {
                System.out.println("Consumer " + consumer.getId() + " started waiting");
                unprocessedMessages.wait();
                System.out.println("Consumer " + consumer.getId() + " woke up");
            }
            message = unprocessedMessages.get(0);

            unprocessedMessages.remove(0);
            underProcessingMessages.put(message.id(), message);
            unprocessedMessages.notifyAll();
        }
        consumer.consume(message);
    }
}
