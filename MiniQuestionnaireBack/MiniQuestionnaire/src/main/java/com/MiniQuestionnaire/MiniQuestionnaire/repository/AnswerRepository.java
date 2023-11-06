package com.MiniQuestionnaire.MiniQuestionnaire.repository;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByQuestion(Question question);

    Answer findByAnswer(String answer);

    List<Answer> findByQuestionIn(List<Question> questionList);
}