package com.example.demojar.controller;

import com.example.demojar.Util.ActiveUserManager;
import com.example.demojar.Util.ChatRoom;
import com.example.demojar.Util.ChatRoomService;
import com.example.demojar.entities.ChatMessage;
import com.example.demojar.services.DefaultChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    ChatRoomService chatRoomService;
    @Autowired
    DefaultChatMessageService defaultChatMessageService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private final ActiveUserManager activeUserManager;

    @Autowired
    public ChatController(ActiveUserManager activeUserManager) {
        this.activeUserManager = activeUserManager;
    }

//    @MessageMapping("/chat.register")
//    @SendTo("/topic/public")
//    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
//        if (activeUserManager.isFull()) {
//            chatMessage.setContent("");
//            return chatMessage;
//        }
//
//        boolean added = activeUserManager.addUser(chatMessage.getSender());
//        if (!added) {
//            chatMessage.setContent("");
//            return chatMessage;
//        }
//        System.out.println("gadha");
//        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
//        chatMessage.setContent("Joined the chat");
//        return chatMessage;
//    }


    @MessageMapping("/chat/{roomId}/send")
    @SendTo("/topic/chat/{roomId}")
    public void send(@DestinationVariable String roomId, @Payload ChatMessage chatMessage) {
        ChatRoom room = chatRoomService.getOrCreateRoom(roomId);
        saveMessage(chatMessage);
        if (room != null) {
            messagingTemplate.convertAndSend(String.format("/topic/chat/%s", roomId), chatMessage);
        }

    }


    @GetMapping("/getAllMessages")
    public List<ChatMessage> getAllMessages()
    {
        return this.defaultChatMessageService.findAllChatMessages();
    }

    @GetMapping("/getAllMessagesForProduct")
    public List<ChatMessage> getAllMessagesForProduct(@RequestParam Integer productId)
    {
        List<ChatMessage> messages = this.defaultChatMessageService.findAllChatMessages();
        List<ChatMessage> mess = new ArrayList<>();
        for(ChatMessage m : messages)
        {
            if(m.getForProductId()==productId)
            {
                mess.add(m);
            }
        }
        return mess;
    }
    @GetMapping("/getAllMessagesSentByUser")
    public List<ChatMessage> getAllMessagesSentByUser(@RequestParam String email)
    {
        List<ChatMessage> messages = this.defaultChatMessageService.findAllChatMessages();
        List<ChatMessage> mess = new ArrayList<>();
        for(ChatMessage m : messages)
        {
            if(m.getSenderUserEmail().equals(email))
            {
                mess.add(m);
            }
        }
        return mess;
    }

    @GetMapping("/getAllMessagesSentByUserForProduct")
    public List<ChatMessage> getAllMessagesSentByUserForProduct(@RequestParam String email, @RequestParam Integer productId)
    {
        List<ChatMessage> messages = this.defaultChatMessageService.findAllChatMessages();
        List<ChatMessage> mess = new ArrayList<>();
        for(ChatMessage m : messages)
        {
            if(m.getSenderUserEmail().equals(email) && m.getForProductId()==productId)
            {
                mess.add(m);
            }
        }
        return mess;
    }
    @GetMapping("/getAllMessagesReceivedByUser")
    public List<ChatMessage> getAllMessagesReceivedByUser(@RequestParam String email)
    {
        List<ChatMessage> messages = this.defaultChatMessageService.findAllChatMessages();
        List<ChatMessage> mess = new ArrayList<>();
        for(ChatMessage m : messages)
        {
            if(m.getReceiverUserEmail().equals(email))
            {
                mess.add(m);
            }
        }
        return mess;
    }
    @GetMapping("/getAllMessagesReceivedByUserForProduct")
    public List<ChatMessage> getAllMessagesReceivedByUserForProduct(@RequestParam String email, @RequestParam Integer productId)
    {
        List<ChatMessage> messages = this.defaultChatMessageService.findAllChatMessages();
        List<ChatMessage> mess = new ArrayList<>();
        for(ChatMessage m : messages)
        {
            if(m.getReceiverUserEmail().equals(email) && m.getForProductId()==productId)
            {
                mess.add(m);
            }
        }
        return mess;
    }

    @GetMapping("/getAllMessagesSentOrReceivedByUser")
    public List<ChatMessage> getAllMessagesSentOrReceivedByUser(@RequestParam String email)
    {
        List<ChatMessage> messages = this.defaultChatMessageService.findAllChatMessages();
        List<ChatMessage> mess = new ArrayList<>();
        for(ChatMessage m : messages)
        {
            if(m.getReceiverUserEmail().equals(email) || m.getSenderUserEmail().equals(email))
            {
                mess.add(m);
            }
        }
        return mess;
    }

    @PostMapping("/saveMessage")
    public Boolean saveMessage(@RequestBody ChatMessage chatMessage)
    {
        this.defaultChatMessageService.save(chatMessage);
        return true;
    }




    @GetMapping("/{id}")
    public ResponseEntity<ChatMessage> getAMessageById(@PathVariable Integer id)
    {
        return defaultChatMessageService.findChatMessageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }





    @PostMapping("/chat/rooms")
    public ResponseEntity<ChatRoom> createRoom(@RequestBody String roomId) {
        ChatRoom room = chatRoomService.getOrCreateRoom(roomId);
        return ResponseEntity.ok(room);
    }

    @DeleteMapping("/chat/rooms/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String roomId) {
        chatRoomService.deleteRoom(roomId);
        return ResponseEntity.ok().build();
    }



}

