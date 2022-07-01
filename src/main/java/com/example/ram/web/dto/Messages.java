package com.example.ram.web.dto;

import java.util.ArrayList;
import java.util.List;

public class Messages {
    private List<MessageRegistrationDto> messages;

    public Messages() {
        this.messages = new ArrayList<>();
    }

    public Messages(List<MessageRegistrationDto> messages) {
        this.messages = messages;
    }

    public List<MessageRegistrationDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageRegistrationDto> messages) {
        this.messages = messages;
    }

    public  void addMessage(MessageRegistrationDto messageRegistrationDto) {
        this.messages.add(messageRegistrationDto);
    }

    public  void addAllMessage(List<MessageRegistrationDto> messages) {
        this.messages.addAll(messages);
    }
}
