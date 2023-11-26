package com.example.demojar.Util;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

// A simple holder for connected user sessions
@Component
public class ActiveUserManager {
    private final Set<String> connectedUsers = ConcurrentHashMap.newKeySet();

    public boolean addUser(String username) {
        if (connectedUsers.size() < 2) {
            connectedUsers.add(username);
            return true;
        }
        return false;
    }

    public void removeUser(String username) {
        connectedUsers.remove(username);
    }

    public boolean isFull() {
        return connectedUsers.size() >= 2;
    }
    public boolean isUserActive(String username) {
        return connectedUsers.contains(username);
    }
}
