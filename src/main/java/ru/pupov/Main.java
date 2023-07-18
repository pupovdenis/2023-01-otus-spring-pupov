package ru.pupov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.pupov.service.QuizService;

@ComponentScan
@PropertySource(value = "classpath:application.properties")
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);
        var quizService = context.getBean(QuizService.class);
        quizService.run();
    }
}
