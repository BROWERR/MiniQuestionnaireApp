package com.MiniQuestionnaire.MiniQuestionnaire.repository;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    Questionnaire findQuestionnaireByName(String name);
}