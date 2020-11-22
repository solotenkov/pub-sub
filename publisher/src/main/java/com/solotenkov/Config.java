package com.solotenkov;

import com.solotenkov.entity.Message;
import com.solotenkov.entity.TypeMessage;
import com.solotenkov.utils.Generator;
import com.solotenkov.utils.MessagesSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.Instant;
import java.util.Random;

@Configuration
@ComponentScan
public class Config {
    @Bean
    @Scope(scopeName = "prototype")
    Message message() {
        Random random = new Random();
        return new Message(random.nextInt(), random.nextInt(TypeMessage.values().length), Instant.now().getEpochSecond());
    }
    @Bean
    Generator generator(){
        return this::message;
    }
    @Bean
    MessagesSender messagesSender(){
        String urlName;
        return new MessagesSender("http://localhost:8080/subscriber_war_exploded/messages/add",
                "POST");
    }

}
