package com.example.demojar.Util;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatRoomService {
    private final Map<String, ChatRoom> chatRoomMap = new ConcurrentHashMap<>();

    public ChatRoom getOrCreateRoom(String roomId) {
        return chatRoomMap.computeIfAbsent(roomId, k -> new ChatRoom(roomId));
    }

    public Collection<ChatRoom> getAllRooms() {
        return chatRoomMap.values();
    }

    public void deleteRoom(String roomId) {
        chatRoomMap.remove(roomId);
    }
}

