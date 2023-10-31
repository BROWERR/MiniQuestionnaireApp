package com.MiniQuestionnaire.MiniQuestionnaire.mapper;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.UserAnswer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAnswerMapper {
    UserAnswer toUserAnswerDTO(UserAnswer userAnswer);
    UserAnswer toUserAnswer(UserAnswer toUserAnswerDTO);
}
