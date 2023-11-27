package com.example.demojar.services;

import com.example.demojar.entities.Bid;
import com.example.demojar.entities.ChatMessage;

import java.util.List;
import java.util.Optional;

public interface DefaultChatMessageService {
    ChatMessage save(ChatMessage chatMessage);
    Optional<ChatMessage> findChatMessageById(Integer id);
    List<ChatMessage> findAllChatMessages();
}
