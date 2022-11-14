package com.MiniQuestionnaire.MiniQuestionnaire.mapper;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserAnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.UserAnswer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAnswerMapper {
    UserAnswerDTO toUserAnswerDTO(UserAnswer userAnswer);
    UserAnswer toUserAnswer (UserAnswerDTO userAnswerDTO);
}
