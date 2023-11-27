package com.example.demojar.services.impl;

import com.example.demojar.entities.Bid;
import com.example.demojar.entities.ChatMessage;
import com.example.demojar.repositories.BidRepository;
import com.example.demojar.repositories.ChatMessageRepository;
import com.example.demojar.services.DefaultBidService;
import com.example.demojar.services.DefaultChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultChatMessageServiceImpl implements DefaultChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public Optional<ChatMessage> findChatMessageById(Integer id) {
        return chatMessageRepository.findById(id);
    }

    @Override
    public List<ChatMessage> findAllChatMessages() {
        return chatMessageRepository.findAll();
    }
}