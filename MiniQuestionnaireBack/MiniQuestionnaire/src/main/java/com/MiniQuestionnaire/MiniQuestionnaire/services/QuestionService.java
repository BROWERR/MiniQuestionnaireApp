package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.QuestionMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.QuestionnaireMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.QuestionRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionnaireMapper questionnaireMapper;

    /**
     * *
     * Получение всех вопросов
     *
     * @return список всех вопросов
     */
    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(questionMapper::toQuestionDTO)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public String addQuestion(Question question) {
        if (question == null) {
            return null;
        }
        if (questionRepository.findQuestionByQuestion(question.getQuestion()) != null) {
            return null;
        }
        questionRepository.save(question);
        return "Question successfully added";
    }

    public QuestionDTO getQuestionById(Long id) {
        return questionMapper.toQuestionDTO(questionRepository.findById(id).orElseThrow());
    }

    @SneakyThrows
    public void deleteQuestionById(Long id) {
        questionRepository.delete(questionRepository.findById(id).orElseThrow());
    }

    /** **
     * Обновление вопроса
     * @param questionDTO DTO-объект вопроса, который нужно обновить
     */
    public void updateQuestion(QuestionDTO questionDTO) {
        Question updateQuestion = questionRepository.findById(questionDTO.getId()).orElseThrow();
        updateQuestion.setQuestion(questionDTO.getQuestion());
        updateQuestion.setQuestionnaire(questionnaireMapper.toQuestionnaire(questionDTO.getQuestionnaire()));
        questionRepository.save(updateQuestion);
    }

    public List<QuestionDTO> getAllQuestionsByQuestionnaireId(Long id) {
        return questionRepository.findAllByQuestionnaireId(id).stream()
                .map(questionMapper::toQuestionDTO).collect(Collectors.toList());
    }
}