package com.snehasish.microservice.quiz.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quiz_title")
    private String quizTitle;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
