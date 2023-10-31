package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long id;
    private String answer;
    public AnswerDTO(String answer){
        this.answer = answer;
    }
    public AnswerDTO(){}
}
