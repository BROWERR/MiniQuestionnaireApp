package com.MiniQuestionnaire.MiniQuestionnaire.mapper;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionDTO toQuestionDTO(Question question);
    Question toQuestion (QuestionDTO questionDTO);
}
