package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public QuestionDTO getQuestionById(@PathVariable(value ="id") long id){
        return questionService.getQuestionById(id);
    }

    @PostMapping("/question/delete/{id}")
    public void deleteQuestion(@PathVariable(value = "id") long id) {
        questionService.deleteQuestionById(id);
    }

    @PostMapping("/question/update")
    public void updateQuestion(@RequestBody QuestionDTO questionDTO){
        questionService.updateQuestion(questionDTO);
    }
}