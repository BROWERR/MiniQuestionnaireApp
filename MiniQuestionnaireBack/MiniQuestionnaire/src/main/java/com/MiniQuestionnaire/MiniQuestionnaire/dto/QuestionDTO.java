package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String question;
    private QuestionnaireDTO questionnaire;
}
