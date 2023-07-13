package ru.pupov.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.pupov.service.QuestionExtractor;
import ru.pupov.service.QuizPrinter;
import ru.pupov.service.impl.QuestionExtractorImpl;
import ru.pupov.service.impl.QuizPrinterImpl;

@Configuration
public class AppConfig {

    @Bean
    QuizPrinter quizPrinter() {
        return new QuizPrinterImpl();
    }

    @Bean
    QuestionExtractor questionExtractor() {
        return new QuestionExtractorImpl();
    }
}
