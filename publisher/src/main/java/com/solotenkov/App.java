package com.solotenkov;

import com.solotenkov.entity.Message;
import com.solotenkov.utils.Generator;
import com.solotenkov.utils.MessagesSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.apache.log4j.Logger;

import java.io.IOException;

public class App 
{
    static final Logger logger = Logger.getLogger(App.class);
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Generator generator = context.getBean(Generator.class);
        MessagesSender messagesSender = context.getBean(MessagesSender.class);
        Thread thread = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    logger.error("Interrupt thread");
                }
                try {
                    messagesSender.sendMessage(generator.getMessage());
                } catch (IOException e) {
                    logger.error("Error send message!", e);
                }
            }
        });
        logger.info("Начинаем отправлять сообщения: ");
        thread.start();
    }
}
