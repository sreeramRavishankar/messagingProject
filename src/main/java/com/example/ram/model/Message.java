package com.example.ram.model;

import javax.persistence.*;

@Entity
@Table(name =  "message_details", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Message {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "receiver_email")
    private String receiverEmail;

    @Column(name = "text_message")
    private String textMessage;

    @Column(name = "status")
    private String status;

    @Column(name = "time_stamp")
    private String timeStamp;

    public Message() {

    }

    public Message(String email, String receiverEmail, String textMessage, String status, String timeStamp) {
        this.email = email;
        this.receiverEmail = receiverEmail;
        this.textMessage = textMessage;
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
