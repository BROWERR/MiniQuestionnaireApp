package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserAnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.*;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.AnswerMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.UserAnswerMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.UserMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.UserAnswerRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserAnswerService {
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * *
     * Добавление ответов пользователя
     *
     * @param userAnswerDTO DTO-объект, содержащий ответы пользователя
     */
    @SneakyThrows
    public void addUserAnswer(UserAnswerDTO userAnswerDTO) {
        if (userAnswerDTO != null) {
            List<UserAnswer> userAnswerList = new ArrayList<>();
            for (int i = 0; i < userAnswerDTO.getAnswer().length; i++) {
                UserAnswer newAnswer = new UserAnswer(
                        answerMapper.toAnswer(userAnswerDTO.getAnswer()[i]),
                        userMapper.toUser(userAnswerDTO.getUser())
                );
                userAnswerList.add(newAnswer);
            }
            userAnswerRepository.saveAll(userAnswerList);
        }
    }

    @SneakyThrows
    public void deleteUserAnswerById(Long id) {
        userAnswerRepository.delete(userAnswerRepository.findById(id).orElseThrow());
    }
}