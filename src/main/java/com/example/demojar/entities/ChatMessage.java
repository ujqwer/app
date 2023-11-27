package com.example.demojar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int forProductId=0;
    private String senderUserEmail;
    private String receiverUserEmail;
    private String content="";
    private String timestamp="";
    private String roomId="";
    private MessageType type = MessageType.CHAT;

    public enum MessageType {
        CHAT, LEAVE, JOIN
    }


}