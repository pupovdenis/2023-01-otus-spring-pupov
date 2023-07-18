package ru.pupov.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.pupov.dao.QuestionDao;
import ru.pupov.dao.StudentDao;
import ru.pupov.dao.impl.QuestionDaoImpl;
import ru.pupov.dao.impl.StudentDaoImpl;

@Configuration
public class AppConfig {

    @Bean
    QuestionDao questionDao(@Value("${csv-path}") String csvPath) {
        return new QuestionDaoImpl(csvPath);
    }

    @Bean
    StudentDao studentDao() {
        return new StudentDaoImpl();
    }
}
