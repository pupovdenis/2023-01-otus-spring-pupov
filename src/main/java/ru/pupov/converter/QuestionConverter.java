package ru.pupov.converter;

import org.springframework.stereotype.Component;
import ru.pupov.domain.Question;

public interface QuestionConverter {

    String toQuizString(Question question);
}
