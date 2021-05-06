package com.demo.retailstoreofthefuture.handlers;

import java.awt.Point;
import java.io.IOException;

import com.demo.retailstoreofthefuture.model.CustomerMovementEvent;
import com.demo.retailstoreofthefuture.service.RulesExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class IncomingMessageHandler implements MessageHandler {
    private static final Logger LOG = LoggerFactory.getLogger(IncomingMessageHandler.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RulesExecutor rulesExecutor;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
         try {
            LOG.info("Received Message: " + message.getPayload().toString());

            CustomerMovementEvent customerMovementEvent = mapper.readerFor(CustomerMovementEvent.class).readValue(message.getPayload().toString());
            customerMovementEvent.setLocation(new Point(customerMovementEvent.getX(), customerMovementEvent.getY()));
          
           rulesExecutor.fireRulesForNewEvent(customerMovementEvent);

            System.out.println(customerMovementEvent.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
