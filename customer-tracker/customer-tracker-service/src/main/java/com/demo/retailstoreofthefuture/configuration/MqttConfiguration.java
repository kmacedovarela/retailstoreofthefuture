package com.demo.retailstoreofthefuture.configuration;

import com.demo.retailstoreofthefuture.configuration.settings.MqttSettings;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        MqttSettings.class
})
public class MqttConfiguration {

 
}
