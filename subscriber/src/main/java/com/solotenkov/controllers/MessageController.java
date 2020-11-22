package com.solotenkov.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solotenkov.persistence.model.Message;
import com.solotenkov.persistence.model.MessagePurchase;
import com.solotenkov.persistence.model.MessageSubscription;
import com.solotenkov.persistence.services.interfaces.MessageService;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/messages")
public class MessageController {
    private static final Logger LOG = Logger.getLogger(MessageController.class);
    @Autowired
    private MessageService messageService;

    //HttpServletRequest request
    @PostMapping(value = "/add")
    public String addNewMessage(@RequestBody String string) {
        ObjectMapper mapper = new ObjectMapper();
        Message message = null;

        String nameTable = null;
        if (string.contains("SUBSCRIPTION")) {
            try {
                message = mapper.readValue(string, MessageSubscription.class);
                LOG.info("New message id " + string
                        + " was added in table Subscription.");
                nameTable = "Subscription";
            } catch (JsonProcessingException e) {
                LOG.error("Error! Не получилось преобразовать в MessageSubscription.class", e);
            }
        } else {
            try {
                message = mapper.readValue(string, MessagePurchase.class);
                LOG.info("New message id " + string
                        + " was added in table Purchase.");
                nameTable = "Purchase";
            } catch (JsonProcessingException e) {
                LOG.error("Error! Не получилось преобразовать в MessagePurchase.class", e);
            }
        }
        messageService.addMessage(message);
        return nameTable;
    }

    @GetMapping(value = "/hello")
    public ModelAndView hello(ModelAndView modelAndView) {
        modelAndView.setViewName("index.jsp");
        return modelAndView;
    }
}

