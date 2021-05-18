package com.demo.retailstoreofthefuture.configuration;

import com.demo.retailstoreofthefuture.configuration.settings.MqttSettings;
import com.demo.retailstoreofthefuture.handlers.IncomingMessageHandler;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

@Configuration
@EnableConfigurationProperties({
        MqttSettings.class
})
public class MqttConfiguration {
        
        // @Bean
        // public IntegrationFlow mqttInbound(MqttSettings settings,
        //                                    MqttPahoClientFactory mqttClientFactory) {
        //     IncomingMessageHandler messageHandler = new IncomingMessageHandler(); // Injection is not working as expected.
        //     return IntegrationFlows.from(
        //             new MqttPahoMessageDrivenChannelAdapter("mqtt-service", mqttClientFactory, settings.getTopic()))
        //             .handle(messageHandler)
        //             .get();
        // }
        
        // @Bean
        // public MqttPahoClientFactory mqttClientFactory(MqttSettings settings) {
        //     MqttConnectOptions options = new MqttConnectOptions();
        //     options.setServerURIs(new String[] { String.format("tcp://%s:%s", settings.getHostname(), settings.getPort()) });
    
        //     if (settings.getUsername() != null && !settings.getUsername().isEmpty()) {
        //         options.setUserName(settings.getUsername());
        //     }
    
        //     if (settings.getPassword() != null && !settings.getPassword().isEmpty()) {
        //         options.setPassword(settings.getPassword().toCharArray());
        //     }
    
        //     DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        //     factory.setConnectionOptions(options);
    
        //     return factory;
        // }
 
}
