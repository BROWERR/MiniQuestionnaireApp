package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.User;
import lombok.Data;

@Data
public class UserAnswerDTO {
    private Long id;
    private Answer[] answer;
    private User user;
}
