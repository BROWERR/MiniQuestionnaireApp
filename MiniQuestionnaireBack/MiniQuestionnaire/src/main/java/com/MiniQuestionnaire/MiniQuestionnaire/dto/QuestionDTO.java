package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Questionnaire;
import lombok.Data;

@Data
public class QuestionDTO {
    private Long id;
    private String question;
    private Questionnaire questionnaire;
}
