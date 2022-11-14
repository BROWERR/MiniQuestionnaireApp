package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserAnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.services.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserAnswerController {
    @Autowired
    private UserAnswerService userAnswerService;

    @GetMapping("/userAnswer/all")
    public List<UserAnswerDTO> getAllUserAnswers(){
        return userAnswerService.getAllUserAnswers();
    }

    @PostMapping("/userAnswer/add")
    public void addUserAnswer(@RequestBody UserAnswerDTO userAnswerDTO){
        userAnswerService.addUserAnswer(userAnswerDTO);
    }

    @GetMapping("/userAnswer/{id}")
    public UserAnswerDTO getUserAnswerById(@PathVariable(value ="id") long id){
        return userAnswerService.getUserAnswerById(id);
    }

    @PostMapping("/userAnswer/delete/{id}")
    public void deleteUserAnswer(@PathVariable(value = "id") long id) {
        userAnswerService.deleteUserAnswerById(id);
    }

    @PostMapping("/userAnswer/update")
    public void updateUserAnswer(@RequestBody UserAnswerDTO userAnswerDTO){
        userAnswerService.updateUserAnswer(userAnswerDTO);
    }
}
