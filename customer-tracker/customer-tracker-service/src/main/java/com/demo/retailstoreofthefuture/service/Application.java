package com.demo.retailstoreofthefuture.service;

import com.demo.retailstoreofthefuture.configuration.settings.MqttSettings;
import com.demo.retailstoreofthefuture.handlers.IncomingMessageHandler;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
@IntegrationComponentScan
@Configuration
@EnableConfigurationProperties({
        MqttSettings.class
})
public class Application {

    IncomingMessageHandler messageHandler;

    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(Application.class).run(args);
        RulesExecutor.setupKIEContainer();
    }

    @Bean
    public IntegrationFlow mqttInbound(MqttSettings settings,
                                       MqttPahoClientFactory mqttClientFactory) {
        messageHandler = new IncomingMessageHandler(); // Injection is not working as expected.
        return IntegrationFlows.from(
                new MqttPahoMessageDrivenChannelAdapter("mqtt-service", mqttClientFactory, settings.getTopic()))
                .handle(messageHandler)
                .get();
    }
    
    @Bean
    public MqttPahoClientFactory mqttClientFactory(MqttSettings settings) {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] { String.format("tcp://%s:%s", settings.getHostname(), settings.getPort()) });

        if (settings.getUsername() != null && !settings.getUsername().isEmpty()) {
            options.setUserName(settings.getUsername());
        }

        if (settings.getPassword() != null && !settings.getPassword().isEmpty()) {
            options.setPassword(settings.getPassword().toCharArray());
        }

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(MqttSettings settings) {
        MqttPahoMessageHandler messageHandler =
                       new MqttPahoMessageHandler("customer-tracker-client", mqttClientFactory(settings));
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(settings.getTopic());
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MyGateway {
        void sendToMqtt(String data);
    }
}