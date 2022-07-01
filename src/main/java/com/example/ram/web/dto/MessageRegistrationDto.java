package com.example.ram.web.dto;

public class MessageRegistrationDto {
    private Long id;

    private String email;

    private String receiverEmail;

    private String textMessage;

    private String status;

    private String timeStamp;

    public MessageRegistrationDto() {
    }

    public MessageRegistrationDto(Long id, String email, String receiverEmail, String textMessage, String status, String timeStamp) {
        this.id = id;
        this.email = email;
        this.receiverEmail = receiverEmail;
        this.textMessage = textMessage;
        this.status = status;
        this.timeStamp = timeStamp;
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

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Override
    public String toString() {
        return "MessageRegistrationDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", textMessage='" + textMessage + '\'' +
                ", status='" + status + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }


}
