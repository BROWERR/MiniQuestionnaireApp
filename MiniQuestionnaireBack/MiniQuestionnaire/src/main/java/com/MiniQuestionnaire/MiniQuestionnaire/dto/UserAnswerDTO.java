package com.MiniQuestionnaire.MiniQuestionnaire.dto;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.User;
import lombok.Data;

@Data
public class UserAnswerDTO {
    private Long id;
    private AnswerDTO[] answer;
    private UserDTO user;
}
