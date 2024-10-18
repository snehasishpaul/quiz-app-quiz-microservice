package com.snehasish.microservice.quiz.controller;

import com.snehasish.microservice.quiz.dto.QuizDto;
import com.snehasish.microservice.quiz.dto.Response;
import com.snehasish.microservice.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/ping")
    public String ping() {
        return "pong from quiz";
    }

    @GetMapping("")
    public ResponseEntity<Response<List<QuizDto>>> getAllQuiz() {
        Response<List<QuizDto>> res = quizService.getAllQuiz();
        return new ResponseEntity<>(res, res.getStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<Response<QuizDto>> getQuizById(@PathVariable Long id) {
        Response<QuizDto> res = quizService.getQuizById(id);
        return new ResponseEntity<>(res, res.getStatus());
    }

    @PostMapping("")
    public ResponseEntity<Response<QuizDto>> addQuiz(@RequestBody QuizDto quizDto) {
        Response<QuizDto> res = quizService.addQuiz(quizDto);
        return new ResponseEntity<>(res, res.getStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<QuizDto>> updateQuiz(@PathVariable Long id ,@RequestBody QuizDto quizDto) {
        Response<QuizDto> res = quizService.updateQuiz(id, quizDto);
        return new ResponseEntity<>(res, res.getStatus());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<String>> deleteQuiz(@PathVariable Long id) {
        Response<String> res = quizService.deleteQuiz(id);
        return new ResponseEntity<>(res, res.getStatus());
    }

}
