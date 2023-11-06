package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/all")
    public List<QuestionDTO> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/question/all/{id}")
    public List<QuestionDTO> getAllQuestionsByQuestionnaireId(@PathVariable(value ="id") long id){
        return questionService.getAllQuestionsByQuestionnaireId(id);
    }

    @GetMapping("/question/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable(value ="id") long id){
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    @PostMapping("/admin/question/delete/{id}")
    public void deleteQuestion(@PathVariable(value = "id") long id) {
        questionService.deleteQuestionById(id);
    }

    @PostMapping("/admin/question/update")
    public void updateQuestion(@RequestBody QuestionDTO questionDTO){
        questionService.updateQuestion(questionDTO);
    }
}