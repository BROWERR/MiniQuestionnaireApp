package com.MiniQuestionnaire.MiniQuestionnaire.repository;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findQuestionByQuestion (String question);

    List<Question> findAllByQuestionnaire(Questionnaire questionnaire);

    List<Question> findAllByQuestionnaireId(Long id);
}