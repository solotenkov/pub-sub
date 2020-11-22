package com.solotenkov.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.Random;

@Entity
@Table(name = "SUBSCRIPTION")
public class MessageSubscription extends Message{


    public MessageSubscription(int msisdn, int action, long timestamp) {
        super();
        super.action = TypeMessage.values()[action];
        super.timestamp = timestamp;
        super.msisdn = msisdn;
    }

    public MessageSubscription() {
    }

}
