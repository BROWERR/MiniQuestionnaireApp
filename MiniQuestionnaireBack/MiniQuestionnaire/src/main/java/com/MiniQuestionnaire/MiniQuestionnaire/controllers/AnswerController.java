package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/answer/all")
    public List<AnswerDTO> getAllAnswers(){
        return answerService.getAllAnswers();
    }

    @GetMapping("/answer/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable(value ="id") long id){
        return new ResponseEntity<>(answerService.getAnswerById(id), HttpStatus.OK);
    }

    @PostMapping("/admin/answer/delete/{id}")
    public void deleteUserAnswer(@PathVariable(value = "id") long id) {
        answerService.deleteAnswerById(id);
    }

    @PostMapping("/admin/answer/update")
    public void updateAnswer(@RequestBody AnswerDTO answerDTO){
        answerService.updateAnswer(answerDTO);
    }
}