package com.solotenkov.persistence.dao;

import com.solotenkov.persistence.model.Message;
import com.solotenkov.persistence.model.MessagePurchase;
import com.solotenkov.persistence.model.MessageSubscription;
import com.solotenkov.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDao implements Dao{
    public Message save(Message message) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(message);
        tx.commit();
        session.close();
        return message;
    }

    public List<Message> getAllMessagePurchase() {
        String sql = "From " + MessagePurchase.class.getSimpleName();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Message> messages = session.createQuery(sql).list();
        tx.commit();
        session.close();
        return messages;
    }
    public List<Message> getAllMessageSubscription() {
        String sql = "From " + MessageSubscription.class.getSimpleName();
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Message> messages = session.createQuery(sql).list();
        tx.commit();
        session.close();
        return messages;
    }

    public int remove(Message message) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Message persistentObject = session.get(message.getClass(), message.getMsisdn());
        if(persistentObject!=null){
            session.remove(persistentObject);
        }

        tx.commit();
        session.close();
        return message.getMsisdn();
    }


}
