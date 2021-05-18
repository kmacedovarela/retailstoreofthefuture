package com.demo.retailstoreofthefuture.handlers;

import java.awt.Point;
import java.io.IOException;

import com.demo.retailstoreofthefuture.model.CustomerMovementEvent;
import com.demo.retailstoreofthefuture.model.TrackedCustomer;
import com.demo.retailstoreofthefuture.service.Application;
import com.demo.retailstoreofthefuture.service.RulesExecutor;
import com.demo.retailstoreofthefuture.service.Application.MyGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
    private static final Logger logger = LoggerFactory.getLogger(IncomingMessageHandler.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    RulesExecutor rulesExecutor;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
         try {
            logger.debug("Received Message: " + message.getPayload().toString());
            CustomerMovementEvent customerMovementEvent = buildCustomerMovementEvent(message);
            logger.debug("Converted to CustomerMovementEvent object: "+ customerMovementEvent.toString());

            TrackedCustomer resultingTrackedCustomer = rulesExecutor.fireRulesForNewEvent(customerMovementEvent);

            //TODO: emit mqtt message in case of browsing or focused customer is identified
            if(resultingTrackedCustomer!= null && resultingTrackedCustomer.getIsBrowsing()){
                logger.debug("Emit message to browsing topic for CustomerID "+resultingTrackedCustomer.getCustomerId());
            }
            if(resultingTrackedCustomer!= null && resultingTrackedCustomer.getIsFocused()){
                logger.debug("Emit message to focused topic for CustomerID "+resultingTrackedCustomer.getCustomerId());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CustomerMovementEvent buildCustomerMovementEvent(Message<?> message) throws JsonProcessingException, JsonMappingException {
        CustomerMovementEvent customerMovementEvent = mapper.readerFor(CustomerMovementEvent.class).readValue(message.getPayload().toString());
        customerMovementEvent.setLocation(new Point(customerMovementEvent.getX(), customerMovementEvent.getY()));
       
        return customerMovementEvent;
    }
}
