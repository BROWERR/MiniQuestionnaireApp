package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserAnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.UserAnswer;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.UserAnswerMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAnswerService {
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private UserAnswerMapper userAnswerMapper;

    public List<UserAnswerDTO> getAllUserAnswers(){
        List<UserAnswer> userAnswers = userAnswerRepository.findAll();
        return userAnswers.stream().map(userAnswerMapper::toUserAnswerDTO)
                .collect(Collectors.toList());
    }

    public void addUserAnswer(UserAnswerDTO userAnswerDTO){
        UserAnswer newAnswer = new UserAnswer(
                userAnswerDTO.getAnswer(),
                userAnswerDTO.getUser()
        );
        userAnswerRepository.save(newAnswer);
    }

    public UserAnswerDTO getUserAnswerById(Long id){
        return userAnswerMapper.toUserAnswerDTO(userAnswerRepository.findById(id).orElseThrow());
    }

    public void deleteUserAnswerById(Long id){
        userAnswerRepository.delete(userAnswerRepository.findById(id).orElseThrow());
    }

    public void updateUserAnswer(UserAnswerDTO userAnswerDTO){
        UserAnswer updateAnswer = userAnswerRepository.findById(userAnswerDTO.getId()).orElseThrow();
        updateAnswer.setAnswer(userAnswerDTO.getAnswer());
        updateAnswer.setUser(userAnswerDTO.getUser());
        userAnswerRepository.save(updateAnswer);
    }
}
