package com.example.demojar.repositories;

import com.example.demojar.entities.Bid;
import com.example.demojar.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {



}