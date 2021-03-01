package com.adrian.fakebot;

public class Message {

    private String  content;
    private boolean itsBotMessage;

    public Message(String content, boolean itsBotMessage) {
        this.content = content;
        this.itsBotMessage = itsBotMessage;
    }

    public String getContent() {
        return content;
    }

    public boolean isItsBotMessage() {
        return itsBotMessage;
    }
}
