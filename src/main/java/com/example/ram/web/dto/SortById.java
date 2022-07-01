package com.example.ram.web.dto;

import java.util.Comparator;

public class SortById implements Comparator<MessageRegistrationDto> {

    @Override
    public int compare(MessageRegistrationDto o1, MessageRegistrationDto o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
