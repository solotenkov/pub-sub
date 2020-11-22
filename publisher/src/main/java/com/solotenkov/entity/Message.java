package com.solotenkov.entity;

public class Message {
    private int msisdn;
    private TypeMessage action;
    private long timestamp;


    public Message(int msisdn, int action, long timestamp) {
        this.msisdn = msisdn;
        this.action = TypeMessage.values()[action];
        this.timestamp = timestamp;
    }

    public void setMsisdn(int msisdn) {
        this.msisdn = msisdn;
    }

    public int getMsisdn() {
        return msisdn;
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

    @Override
    public String toString() {
        return "Message{" +
                "msisdn=" + msisdn +
                ", action=" + action +
                ", timestamp=" + timestamp +
                '}';
    }
}
