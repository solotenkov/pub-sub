package com.solotenkov.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.solotenkov.persistence.model.Message;
import com.solotenkov.persistence.model.MessagePurchase;
import com.solotenkov.persistence.services.interfaces.MessageService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-app-config.xml" })
@WebAppConfiguration
@FixMethodOrder
public class ControllerTest {
    @Autowired
    private MessageService messageService;
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    @Before
    public void setup(){mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test(expected = NoSuchBeanDefinitionException.class)
    public void textContext(){
        Assert.assertNotNull(wac.getBean("Controller"));
    }
    @Test
    public void testControllerGetHelloViewName() throws Exception{
       mockMvc.perform(get("/messages/hello")).andDo(print()).andExpect(view().name("index.jsp"));;
    }

    @Test
    public void testController() throws Exception {
        mockMvc.perform(get("/messages/hello")).andExpect(status().isOk()).andExpect(view().name("index.jsp"));
    }
    @Test
    public void testControllerGetStatus() throws Exception {
        mockMvc.perform(get("/messages/hello")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testControllerPost() throws Exception {
        Message message = new MessagePurchase();

        ObjectMapper mapper = new ObjectMapper();
        String requestJson=mapper.writeValueAsString(message);
        mockMvc.perform(MockMvcRequestBuilders.post("/messages/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andExpect(status().isOk());

        Assert.assertEquals(message.getMsisdn(), messageService.removeMessage(message));


    }
}
