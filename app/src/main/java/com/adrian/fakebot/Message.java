package com.adrian.fakebot;

import java.util.Objects;

public class Message {

    private Integer id;
    private String  content;
    private boolean itsBotMessage;

    public Message(Integer id,String content, boolean itsBotMessage) {
        this.content = content;
        this.itsBotMessage = itsBotMessage;
        this.id=id;
    }

    public String getContent() {
        return content;
    }

    public boolean isItsBotMessage() {
        return itsBotMessage;
    }

    public Integer getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return itsBotMessage == message.itsBotMessage &&
                id.equals(message.id) &&
                content.equals(message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, itsBotMessage);
    }
}
