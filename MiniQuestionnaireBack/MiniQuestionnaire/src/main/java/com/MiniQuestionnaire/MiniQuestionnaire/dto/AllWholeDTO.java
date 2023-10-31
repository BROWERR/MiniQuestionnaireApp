package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Questionnaire;
import lombok.Data;

@Data
public class AllWholeDTO {
    private Long id;
    private Questionnaire questionnaire;
    private QuestionToServer[] questions;
}
