package com.demo.retailstoreofthefuture.model;

/**
 * MQTTMessageTemp
 */
public class MQTTMessageTemp {
    private String id;
    private long ts;
    private long x;
    private long y;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTs() {
        return this.ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public long getX() {
        return this.x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public long getY() {
        return this.y;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "MQTTMessageTemp{"+
        "id='"+id+"'"+
        ", ts="+ts+
        ", X="+x+
        ", Y="+y+
        "}";
    }
}