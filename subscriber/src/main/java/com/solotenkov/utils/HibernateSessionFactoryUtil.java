package com.solotenkov.utils;

import com.solotenkov.persistence.model.MessagePurchase;
import com.solotenkov.persistence.model.MessageSubscription;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private  HibernateSessionFactoryUtil(){}
    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
                sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                        .addAnnotatedClass(MessagePurchase.class)
                        .addAnnotatedClass(MessageSubscription.class)
                        .buildSessionFactory();
        }
        return sessionFactory;
    }
}
