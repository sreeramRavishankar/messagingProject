package com.example.ram.repository;

import com.example.ram.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByEmail(String email);
    List<Message> findByReceiverEmail(String receiverEmail);
}
