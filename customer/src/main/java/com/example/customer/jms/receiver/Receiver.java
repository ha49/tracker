package com.example.customer.jms.receiver;

import com.example.tracker.jms.sender.MessageObjectSend;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @JmsListener(destination = JmsConfig.TRACKER_QUEUE)
    public void listen(@Payload MessageObjectSend messageObjectSend) {
        System.out.println("I got a message");
        System.out.println(messageObjectSend);
    }

}
