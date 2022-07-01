package com.example.ram.service;

import com.example.ram.model.Message;
import com.example.ram.web.dto.MessageList;
import com.example.ram.web.dto.MessageRegistrationDto;
import com.example.ram.web.dto.Messages;
import com.example.ram.web.dto.UserRegistrationDto;

public interface MessageService {
    Message save(MessageRegistrationDto messageRegistrationDto);
    Messages loadMessageBySender(String email);
    Messages loadMessageByReceiver(String email);
    MessageList viewAllMessages(String email);
    void sendMessage(String primaryUser, String secondaryUser, MessageRegistrationDto messageRegistrationDto);
    Messages displayMessages(MessageList messageList, String subUser);
    MessageRegistrationDto displayUsersToMessage(UserRegistrationDto userRegistrationDto);
}
