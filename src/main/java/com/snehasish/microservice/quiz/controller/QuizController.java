package com.snehasish.microservice.quiz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping("/ping")
    public String ping() {
        return "pong from quiz";
    }
}
