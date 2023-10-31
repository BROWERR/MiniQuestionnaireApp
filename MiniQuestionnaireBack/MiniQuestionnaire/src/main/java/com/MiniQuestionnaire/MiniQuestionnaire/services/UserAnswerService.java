package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserAnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.*;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAnswerService {
    @Autowired
    private UserAnswerRepository userAnswerRepository;

    public void addUserAnswer(UserAnswerDTO userAnswerDTO) {
        for (int i = 0; i < userAnswerDTO.getAnswer().length; i++) {
            UserAnswer newAnswer = new UserAnswer(
                    userAnswerDTO.getAnswer()[i],
                    userAnswerDTO.getUser()
            );
            userAnswerRepository.save(newAnswer);
        }
    }

    public void deleteUserAnswerById(Long id) {
        userAnswerRepository.delete(userAnswerRepository.findById(id).orElseThrow());
    }
}