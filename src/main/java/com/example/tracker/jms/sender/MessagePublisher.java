package com.example.tracker.jms.sender;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MessagePublisher {


    JmsTemplate jmsTemplate;

    public MessagePublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

//    @Scheduled(fixedRate = 2000)
    public  void sendClientRegisteredMessage(String message) {

        System.out.println("Sending message...");
        MessageObjectSend messageObjectSend = new MessageObjectSend(UUID.randomUUID(),
                "New client registered: "+message,
                LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfiguration.TRACKER_QUEUE, messageObjectSend);
        System.out.println("Message sent!");

    }
 public  void sendClientUnRegisteredMessage(String message) {

        System.out.println("Sending message...");
        MessageObjectSend messageObjectSend = new MessageObjectSend(UUID.randomUUID(),
                "A client ended subscription: "+message,
                LocalDateTime.now());
        jmsTemplate.convertAndSend(JmsConfiguration.TRACKER_QUEUE, messageObjectSend);
        System.out.println("Message sent!" );

    }

}
