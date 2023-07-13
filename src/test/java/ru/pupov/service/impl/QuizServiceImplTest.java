package ru.pupov.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("QuizServiceImpl")
@ExtendWith(MockitoExtension.class)
class QuizServiceImplTest {

    @Mock
    QuizServiceImpl quizServiceImpl;

    @DisplayName("Не выбрасывает исключение")
    @Test
    void shouldNotThrowException() {
        quizServiceImpl.run();
    }
}