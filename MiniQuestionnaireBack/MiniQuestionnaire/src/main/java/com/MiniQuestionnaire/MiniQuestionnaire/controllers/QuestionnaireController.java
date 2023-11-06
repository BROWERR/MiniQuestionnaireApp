package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AllWholeDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionnaireDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.services.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/admin/questionnaire/add")
    public ResponseEntity<String> addQuestionnaire(@RequestBody AllWholeDTO allWholeDTO){
        String s = questionnaireService.addQuestionnaire(allWholeDTO);
        if(s == null){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping("/questionnaire/{id}")
    public ResponseEntity<AllWholeDTO> getQuestionnaireById(@PathVariable(value ="id") long id){
        return new ResponseEntity<>(questionnaireService.getQuestionnaireById(id), HttpStatus.OK);
    }

    @GetMapping("/questionnaire/{id}/{idUser}")
    public ResponseEntity<AllWholeDTO> getQuestionnaireById(@PathVariable(value ="id") long id,@PathVariable(value ="idUser") long idUser){
        return new ResponseEntity<>(questionnaireService.getQuestionnaireAnswerById(id, idUser), HttpStatus.OK);
    }

    @PostMapping("/admin/questionnaire/delete/{id}")
    public void deleteQuestionnaire(@PathVariable(value = "id") long id) {
        questionnaireService.deleteQuestionnaireById(id);
    }

    @PostMapping("/admin/questionnaire/update")
    public ResponseEntity<String> updateQuestionnaire(@RequestBody AllWholeDTO allWholeDTO){
        String s = questionnaireService.updateQuestionnaire(allWholeDTO);
        if(s == null){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}