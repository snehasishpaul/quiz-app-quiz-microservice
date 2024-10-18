package com.snehasish.microservice.quiz.service.impl;

import com.snehasish.microservice.quiz.dto.QuizDto;
import com.snehasish.microservice.quiz.dto.Response;
import com.snehasish.microservice.quiz.entity.Quiz;
import com.snehasish.microservice.quiz.exception.NotFoundException;
import com.snehasish.microservice.quiz.repository.QuizRepository;
import com.snehasish.microservice.quiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;


    @Override
    public Response<QuizDto> addQuiz(QuizDto quizDto) {
        try {
            Quiz quiz = new Quiz();
            quiz.setQuizTitle(quizDto.getQuizTitle());
            Quiz savedQuiz = quizRepository.save(quiz);
            log.info("Saved Quiz: {}", savedQuiz);
            QuizDto dto = QuizDto.builder().id(savedQuiz.getId()).quizTitle(savedQuiz.getQuizTitle()).createdAt(savedQuiz.getCreatedAt()).build();
            return Response.<QuizDto>builder()
                    .content(dto)
                    .message("Quiz created successfully")
                    .status(HttpStatus.CREATED)
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Response<List<QuizDto>> getAllQuiz() {
        try {
            List<Quiz> quizList = quizRepository.findAll();
            List<QuizDto> quizDtoList = quizList.stream().map(quiz -> {
                return QuizDto.builder().id(quiz.getId()).quizTitle(quiz.getQuizTitle()).createdAt(quiz.getCreatedAt()).build();
            }).toList();

            return Response.<List<QuizDto>>builder()
                    .content(quizDtoList)
                    .message(null)
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Response<QuizDto> getQuizById(Long id) {
        try {
            Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new NotFoundException("quiz id not found"));
            QuizDto dto = QuizDto.builder().id(quiz.getId()).quizTitle(quiz.getQuizTitle()).createdAt(quiz.getCreatedAt()).build();
            return Response.<QuizDto>builder()
                    .content(dto)
                    .message(null)
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Response<QuizDto> updateQuiz(Long id, QuizDto quizDto) {
        try {
            Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new NotFoundException("quiz id not found"));
            quiz.setQuizTitle(quizDto.getQuizTitle());
            Quiz savedQuiz = quizRepository.save(quiz);
            log.info("Updated Quiz: {}", savedQuiz);
            QuizDto dto = QuizDto.builder()
                    .quizTitle(savedQuiz.getQuizTitle())
                    .build();
            return Response.<QuizDto>builder()
                    .content(dto)
                    .message("Quiz created successfully")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Response<String> deleteQuiz(Long id) {
        try {
            quizRepository.deleteById(id);
            log.info("Deleted Quiz: {}", id);
            return Response.<String>builder()
                    .content(null)
                    .message("Quiz deleted successfully")
                    .status(HttpStatus.OK)
                    .build();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
