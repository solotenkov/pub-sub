package com.solotenkov.persistence.services.interfaces;

import com.solotenkov.persistence.model.Message;

import java.util.List;

public interface MessageService {

    Message addMessage(Message message);
    List<Message> allMessage(String table);
    int removeMessage(Message message);
}
