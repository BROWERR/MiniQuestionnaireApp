package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.AnswerMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.AnswerRepository;
import lombok.SneakyThrows;
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

    /** **
     * Добавление ответа на вопрос
     * @param answer Ответ на вопрос анкеты
     */
    @SneakyThrows
    public String addAnswer(Answer answer) {
        if(answer == null || answerRepository.findByAnswer(answer.getAnswer()) != null){
            return null;
        }
        Answer newAnswer = new Answer(
                answer.getAnswer(),
                answer.getQuestion()
        );
        answerRepository.save(newAnswer);
        return "Answer successfully added";
    }

    /** **
     * Получение ответа на вопрос по id
     * @param id id ответа, который нужно получить
     * @return  DTO-объект ответа
     */
    public AnswerDTO getAnswerById(Long id) {
        return answerMapper.toAnswerDTO(answerRepository.findById(id).orElseThrow());
    }
    @SneakyThrows
    public void deleteAnswerById(Long id) {
        answerRepository.delete(answerRepository.findById(id).orElseThrow());
    }

    /** **
     *
     * @param answerDTO DTO объект ответа, который нужно обновить
     */
    public void updateAnswer(AnswerDTO answerDTO) {
        Answer updateAnswer = answerRepository.findById(answerDTO.getId()).orElseThrow();
        updateAnswer.setAnswer(answerDTO.getAnswer());
        answerRepository.save(updateAnswer);
    }
}