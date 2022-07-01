package com.example.ram.web.dto;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private List<UserRegistrationDto> users;

    public Users() {
        this.users = new ArrayList<>();
    }

    public Users(List<UserRegistrationDto> users) {
        this.users = users;
    }

    public List<UserRegistrationDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserRegistrationDto> users) {
        this.users = users;
    }

    public  void addUser(UserRegistrationDto userRegistrationDto) {
        this.users.add(userRegistrationDto);
    }

    public  void addAllUsers(List<UserRegistrationDto> userRegistrationDtoList) {
        this.users.addAll(userRegistrationDtoList);
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}';
    }
}
