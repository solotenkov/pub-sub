package com.solotenkov.persistence.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public abstract class Message {
    @Id
    protected int msisdn;
    @Column
    protected TypeMessage action;
    @Column
    protected long timestamp;

    @Override
    public String toString() {
        return "Message{" +
                "msisdn=" + msisdn +
                ", action=" + action +
                ", timestamp=" + timestamp +
                '}';
    }

    public TypeMessage getAction() {
        return action;
    }

    public void setAction(TypeMessage action) {
        this.action = action;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMsisdn(int msisdn) {
        this.msisdn = msisdn;
    }

    public int getMsisdn() {
        return msisdn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return msisdn == message.msisdn &&
                timestamp == message.timestamp &&
                action == message.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(msisdn, action, timestamp);
    }
}

