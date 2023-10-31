package com.MiniQuestionnaire.MiniQuestionnaire.mapper;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionnaireDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Questionnaire;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionnaireMapper {
    QuestionnaireDTO toQuestionnaireDTO(Questionnaire questionnaire);
    Questionnaire toQuestionnaire (QuestionnaireDTO questionnaireDTO);
}
