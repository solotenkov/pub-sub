package com.solotenkov.persistence.dao;

import com.solotenkov.persistence.model.Message;

import java.util.List;

public interface Dao {
    Message save(Message message);
    List<Message> getAllMessageSubscription();
    List<Message> getAllMessagePurchase();
    int remove(Message message);

}
