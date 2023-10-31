package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.QuestionMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.QuestionRepository;
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

    public List<QuestionDTO> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream().map(questionMapper::toQuestionDTO)
                .collect(Collectors.toList());
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }

    public QuestionDTO getQuestionById(Long id) {
        return questionMapper.toQuestionDTO(questionRepository.findById(id).orElseThrow());
    }

    public void deleteQuestionById(Long id) {
        questionRepository.delete(questionRepository.findById(id).orElseThrow());
    }

    public void updateQuestion(QuestionDTO questionDTO) {
        Question updateQuestion = questionRepository.findById(questionDTO.getId()).orElseThrow();
        updateQuestion.setQuestion(questionDTO.getQuestion());
        updateQuestion.setQuestionnaire(questionDTO.getQuestionnaire());
        questionRepository.save(updateQuestion);
    }

    public List<QuestionDTO> getAllQuestionsByQuestionnaireId(Long id) {
        return questionRepository.findAllByQuestionnaireId(id).stream()
                .map(questionMapper::toQuestionDTO).collect(Collectors.toList());
    }
}