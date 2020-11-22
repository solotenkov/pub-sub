package com.solotenkov.persistence.services.implementations;

import com.solotenkov.persistence.dao.Dao;
import com.solotenkov.persistence.model.Message;
import com.solotenkov.persistence.services.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private Dao dao;

    public Message addMessage(Message message) {
        return dao.save(message); }

    @Override
    public List<Message> allMessage(String table) {
        return ("purchase".equalsIgnoreCase(table.intern())) ?
                dao.getAllMessagePurchase() : dao.getAllMessageSubscription();
    }

    @Override
    public int removeMessage(Message message) {
        return dao.remove(message);
    }

    @Autowired
    public void setMessageRepository(Dao dao) {
        this.dao = dao;
    }
}
