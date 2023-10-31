package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserAnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.services.UserAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class UserAnswerController {
    @Autowired
    private UserAnswerService userAnswerService;

    @PostMapping("/userAnswer/add")
    public void addUserAnswer(@RequestBody UserAnswerDTO userAnswerDTO){
        userAnswerService.addUserAnswer(userAnswerDTO);
    }

    @PostMapping("/userAnswer/delete/{id}")
    public void deleteUserAnswer(@PathVariable(value = "id") long id) {
        userAnswerService.deleteUserAnswerById(id);
    }
}