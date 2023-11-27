package com.example.demojar.Util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor
@Getter
@Setter
public class ChatRoom {
    private String id;
    private Set<String> connectedUsers;

    public ChatRoom (String id)
    {
        this.id=id;
        this.connectedUsers = ConcurrentHashMap.newKeySet();


    }
}
