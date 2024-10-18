package com.snehasish.microservice.quiz.service;

import com.snehasish.microservice.quiz.dto.QuizDto;
import com.snehasish.microservice.quiz.dto.Response;

import java.util.List;

public interface QuizService {
    Response<QuizDto> addQuiz(QuizDto quizDto);
    Response<List<QuizDto>> getAllQuiz();
    Response<QuizDto> getQuizById(Long id);
    Response<QuizDto> updateQuiz(Long id, QuizDto quizDto);
    Response<String> deleteQuiz(Long id);
}
