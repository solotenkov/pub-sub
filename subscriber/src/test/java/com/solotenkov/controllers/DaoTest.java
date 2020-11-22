package com.solotenkov.controllers;

import com.solotenkov.persistence.dao.MessageDao;
import com.solotenkov.persistence.model.Message;
import com.solotenkov.persistence.model.MessagePurchase;
import com.solotenkov.persistence.model.MessageSubscription;
import com.solotenkov.persistence.model.TypeMessage;
import com.solotenkov.persistence.services.implementations.MessageServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

public class DaoTest {

    @Mock
    MessageDao messageDao;

    @InjectMocks
    MessageServiceImpl messageService;

    static List<Message> messages = new ArrayList<>();

    static {
        Random random = new Random();
        messages.add(new MessagePurchase(random.nextInt(), random.nextInt(TypeMessage.values().length), Instant.now().getEpochSecond()));
        messages.add(new MessageSubscription(random.nextInt(), random.nextInt(TypeMessage.values().length), Instant.now().getEpochSecond()));
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void test1() {
        when(messageDao.getAllMessagePurchase()).thenReturn(messages.stream().filter(message -> message.getAction().toString()
                        .equals("PURCHASE")).collect(Collectors.toList()));

        Assert.assertEquals(messages.stream().filter(message -> message.getAction().toString()
                .equals("PURCHASE")).collect(Collectors.toList()), messageService.allMessage("PURCHASE"));

    }

    @Test
    public void test2()  {
        when(messageDao.getAllMessageSubscription()).thenReturn(messages.stream().filter(message -> message.getAction().toString()
                .equals("SUBSCRIPTION")).collect(Collectors.toList()));

        Assert.assertEquals(messages.stream().filter(message -> message.getAction().toString()
                .equals("SUBSCRIPTION")).collect(Collectors.toList()), messageService.allMessage("SUBSCRIPTION"));

    }

    @Test
    public void test3() {
        Message message = new MessageSubscription(2,1,4);
        messages.add(message);
        when(messageDao.save(message)).thenReturn(messages.get(messages.size()-1));
        Assert.assertEquals(messages.get(messages.size()-1), messageService.addMessage(message));
        System.out.println(messageService.addMessage(message));
    }


    @Test
    public void test4()  {
        Message message = messages.get(1);
        when(messageDao.remove(message)).thenReturn(message.getMsisdn());
        Assert.assertEquals(messages.get(1).getMsisdn(), messageService.removeMessage(message));
    }

}
