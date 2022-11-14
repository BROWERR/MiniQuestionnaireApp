package com.MiniQuestionnaire.MiniQuestionnaire.entity;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AnswerDTO;
import lombok.Data;

import javax.persistence.*;

@Data
public class QuestionToServer {
    private Long id;
    private String questionTitle;
    private Integer answer_options;
    private AnswerDTO[] answers;
}
