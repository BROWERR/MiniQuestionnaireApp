package com.MiniQuestionnaire.MiniQuestionnaire.mapper;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerDTO toAnswerDTO(Answer answer);
    Answer toAnswer (AnswerDTO answerDTO);
}
