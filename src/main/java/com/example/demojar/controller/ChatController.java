package com.example.demojar.controller;

import com.example.demojar.Util.ActiveUserManager;
import com.example.demojar.Util.ChatRoom;
import com.example.demojar.Util.ChatRoomService;
import com.example.demojar.entities.ChatMessage;
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

@RestController
public class ChatController {

    @Autowired
    ChatRoomService chatRoomService;
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
        if (room != null) {
            messagingTemplate.convertAndSend(String.format("/topic/chat/%s", roomId), chatMessage);
        }

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

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            activeUserManager.removeUser(username);
            // Notify others if needed
        }
    }

}

