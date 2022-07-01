package com.example.ram.web.dto;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    private List<Messages> messageList;

    public MessageList() {
        this.messageList = new ArrayList<Messages>();
    }

    public MessageList(List<Messages> messageList) {
        this.messageList = messageList;
    }

    public List<Messages> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Messages> messageList) {
        this.messageList = messageList;
    }

    public  void addMessageList(Messages messages) {
        this.messageList.add(messages);
    }
}
