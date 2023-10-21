package com.example.demojar.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="users")
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String email;
}
