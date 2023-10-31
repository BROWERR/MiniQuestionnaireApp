package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.AnswerMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AnswerMapper answerMapper;

    public List<AnswerDTO> getAllAnswers() {
        List<Answer> answers = answerRepository.findAll();
        return answers.stream().map(answerMapper::toAnswerDTO)
                .collect(Collectors.toList());
    }

    public void addAnswer(Answer answer) {
        Answer newAnswer = new Answer(
                answer.getAnswer(),
                answer.getQuestion()
        );
        answerRepository.save(newAnswer);
    }

    public AnswerDTO getAnswerById(Long id) {
        return answerMapper.toAnswerDTO(answerRepository.findById(id).orElseThrow());
    }

    public void deleteAnswerById(Long id) {
        answerRepository.delete(answerRepository.findById(id).orElseThrow());
    }

    public void updateAnswer(AnswerDTO answerDTO) {
        Answer updateAnswer = answerRepository.findById(answerDTO.getId()).orElseThrow();
        updateAnswer.setAnswer(answerDTO.getAnswer());
        answerRepository.save(updateAnswer);
    }
}