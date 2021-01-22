//package com.example.customer.jms.receiver;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@Component
//public class Sender {
//
//    JmsTemplate jmsTemplate;
//
//    public Sender(JmsTemplate jmsTemplate) {
//        this.jmsTemplate = jmsTemplate;
//    }
//
//    @Scheduled(fixedRate = 10000)
//    public  void  sendMessage() {
//
//        System.out.println("Sending message...");
//        MessageObject messageObject = new MessageObject(UUID.randomUUID(), "New client registered",
//                LocalDateTime.now());
//        jmsTemplate.convertAndSend(JmsConfig.TRACKER_QUEUE, messageObject);
//        System.out.println("Message sent!");
//
//    }
//
//}
