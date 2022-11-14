package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;

public class QuestionToServerDTO {
    private Long id;
    private String questionTitle;
    private AnswerDTO[] answers;
}
