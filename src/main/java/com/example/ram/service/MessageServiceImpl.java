package com.example.ram.service;

import com.example.ram.model.Message;
import com.example.ram.repository.MessageRepository;
import com.example.ram.web.MainController;
import com.example.ram.web.dto.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    public MessageServiceImpl(MessageRepository messageRepository) {
        super();
        this.messageRepository = messageRepository;
    }

    public Messages createMessages() {
        return new Messages();
    }
    

    @Override
    public Message save(MessageRegistrationDto messageRegistrationDto) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Message message = new Message(messageRegistrationDto.getEmail(), messageRegistrationDto.getReceiverEmail(),
                messageRegistrationDto.getTextMessage(), "false", dtf.format(now));
        return messageRepository.save(message);
    }

    @Override
    public Messages loadMessageBySender(String email) {
        List<Message> messageData = messageRepository.findByEmail(email);
        Messages messagesOfSender = createMessages();
        for(Message message: messageData) {
            MessageRegistrationDto messageRegistrationDto = new MessageRegistrationDto(message.getId(), message.getEmail(), message.getReceiverEmail(), message.getTextMessage(), message.getStatus(), message.getTimeStamp());
            messagesOfSender.addMessage(messageRegistrationDto);
        }
        return messagesOfSender;
    }

    @Override
    public Messages loadMessageByReceiver(String email) {
        List<Message> messageData = messageRepository.findByReceiverEmail(email);
        Messages messagesOfReceiver = createMessages();
        for(Message message: messageData) {
            MessageRegistrationDto messageRegistrationDto = new MessageRegistrationDto(message.getId(), message.getEmail(), message.getReceiverEmail(), message.getTextMessage(), message.getStatus(), message.getTimeStamp());
            messagesOfReceiver.addMessage(messageRegistrationDto);
        }
        return messagesOfReceiver;
    }

    @Override
    public MessageList viewAllMessages(String email) {
        Messages messageOfSender = loadMessageBySender(email);
        Messages messageOfReceiver = loadMessageByReceiver(email);

        MessageList messageList = new MessageList();
        Messages allMessages = new Messages();
        Messages conversation = new Messages();

        allMessages.setMessages(messageOfSender.getMessages());
        allMessages.addAllMessage(messageOfReceiver.getMessages());

        for(int i = 0; i < allMessages.getMessages().size(); i++) {
            MessageRegistrationDto messageRegistrationDtoCopy = allMessages.getMessages().get(i);
            for(int j = 0; j < allMessages.getMessages().size() && j != i; j++) {
                if(messageRegistrationDtoCopy.getEmail().equals(allMessages.getMessages().get(j).getReceiverEmail())
                        && messageRegistrationDtoCopy.getReceiverEmail().equals(allMessages.getMessages().get(j).getEmail())) {
                    if(!conversation.getMessages().contains(allMessages.getMessages().get(j))) {
                        conversation.addMessage(allMessages.getMessages().get(j));
                    }
                    if(!conversation.getMessages().contains(messageRegistrationDtoCopy)) {
                        conversation.addMessage(messageRegistrationDtoCopy);
                    }
                }
            }
        }

        Messages nonConversations = allMessages;
        nonConversations.getMessages().removeAll(conversation.getMessages());

        while(!nonConversations.getMessages().isEmpty()) {
            Messages messages = new Messages();
            MessageRegistrationDto messageRegistrationDto = nonConversations.getMessages().get(0);
            nonConversations.getMessages().remove(0);
            messages.addMessage(messageRegistrationDto);

            for (int i = 0; i < nonConversations.getMessages().size(); i++) {
                if (messageRegistrationDto.getEmail().equals(nonConversations.getMessages().get(i).getEmail())
                        && messageRegistrationDto.getReceiverEmail().equals(nonConversations.getMessages().get(i).getReceiverEmail())) {
                    messages.addMessage(nonConversations.getMessages().get(i));
                }
            }
            nonConversations.getMessages().removeAll(messages.getMessages());
            messages.getMessages().sort(new SortById());
            messageList.addMessageList(messages);
        }

        while(!conversation.getMessages().isEmpty()) {
            List<MessageRegistrationDto> oneConvo = new ArrayList<>();
            Messages userConversations = new Messages();

            MessageRegistrationDto messageRegistrationDto = conversation.getMessages().get(0);
            oneConvo.add(messageRegistrationDto);
            if(!conversation.getMessages().isEmpty() ) {
                for(int i = 1; i < conversation.getMessages().size(); i++) {
                    if((messageRegistrationDto.getEmail().equals(conversation.getMessages().get(i).getEmail()) && messageRegistrationDto.getReceiverEmail().equals(conversation.getMessages().get(i).getReceiverEmail()))
                            || (messageRegistrationDto.getReceiverEmail().equals(conversation.getMessages().get(i).getEmail()) && messageRegistrationDto.getEmail().equals(conversation.getMessages().get(i).getReceiverEmail()))) {
                        oneConvo.add(conversation.getMessages().get(i));
                    }
                }
            }
            conversation.getMessages().removeAll(oneConvo);
            oneConvo.sort(new SortById());
            userConversations.setMessages(oneConvo);
            messageList.addMessageList(userConversations);
        }
        return messageList;
    }

    @Override
    public void sendMessage(String primaryUser, String secondaryUser, MessageRegistrationDto messageRegistrationDto) {
        MessageRegistrationDto newMessage = new MessageRegistrationDto();
        newMessage.setEmail(primaryUser);
        newMessage.setReceiverEmail(secondaryUser);
        newMessage.setTextMessage(messageRegistrationDto.getTextMessage());

        save(newMessage);

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail(primaryUser + " & "+ secondaryUser);
    }

    @Override
    public Messages displayMessages(MessageList messageList, String subUser) {
        Messages focusedMessages = new Messages();
        if(!messageList.getMessageList().isEmpty()) {
            for (Messages messages : messageList.getMessageList()) {
                for (MessageRegistrationDto messageRegistrationDto : messages.getMessages()) {
                    if (messageRegistrationDto.getReceiverEmail().equals(subUser) || messageRegistrationDto.getEmail().equals(subUser)) {
                        focusedMessages.addMessage(messageRegistrationDto);
                    }
                }
            }
        }
        return focusedMessages;
    }

    @Override
    public MessageRegistrationDto displayUsersToMessage(UserRegistrationDto userRegistrationDto) {
        StringBuilder mainUser = new StringBuilder();
        StringBuilder subUser = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        String dataText = userRegistrationDto.getEmail();
        for(int i = 0; i < dataText.length(); i++) {
            if(Character.isWhitespace(dataText.charAt(i))) {
                break;
            }
            mainUser.append(dataText.charAt(i));
        }
        for(int i = dataText.length() - 1; i >= 0; i--) {
            if(Character.isWhitespace(dataText.charAt(i))) {
                break;
            }
            temp.append(dataText.charAt(i));
        }
        for(int i = temp.length() - 1; i >= 0; i--) {
            subUser.append(temp.charAt(i));
        }

        MessageRegistrationDto messageRegistrationDtoNew = new MessageRegistrationDto();

        messageRegistrationDtoNew.setEmail(mainUser.toString());
        messageRegistrationDtoNew.setReceiverEmail(subUser.toString());
        MainController.setPrimaryUser(mainUser.toString());
        MainController.setSecondaryUser(subUser.toString());
        return messageRegistrationDtoNew;
    }
}
