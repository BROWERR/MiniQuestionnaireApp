package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import lombok.Data;

@Data
public class QuestionToServer {
    private Long id;
    private String questionTitle;
    private Integer answer_options;
    private AnswerDTO[] answers;
}