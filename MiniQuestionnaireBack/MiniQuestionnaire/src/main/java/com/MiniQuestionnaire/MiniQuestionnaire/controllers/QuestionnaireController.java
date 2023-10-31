package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AllWholeDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionnaireDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class QuestionnaireController {
    @Autowired
    private QuestionnaireService questionnaireService;

    @GetMapping("/questionnaire/all")
    public List<QuestionnaireDTO> getAllQuestionnaires(){
        return questionnaireService.getAllQuestionnaires();
    }

    @PostMapping("/questionnaire/add")
    public void addQuestionnaire(@RequestBody AllWholeDTO allWholeDTO){
        questionnaireService.addQuestionnaire(allWholeDTO);
    }

    @GetMapping("/questionnaire/{id}")
    public AllWholeDTO getQuestionnaireById(@PathVariable(value ="id") long id){
        return questionnaireService.getQuestionnaireById(id);
    }

    @GetMapping("/questionnaire/{id}/{idUser}")
    public AllWholeDTO getQuestionnaireById(@PathVariable(value ="id") long id,@PathVariable(value ="idUser") long idUser){
        return questionnaireService.getQuestionnaireAnswerById(id, idUser);
    }

    @PostMapping("/questionnaire/delete/{id}")
    public void deleteQuestionnaire(@PathVariable(value = "id") long id) {
        questionnaireService.deleteQuestionnaireById(id);
    }

    @PostMapping("/questionnaire/update")
    public void updateQuestionnaire(@RequestBody AllWholeDTO allWholeDTO){
        questionnaireService.updateQuestionnaire(allWholeDTO);
    }
}