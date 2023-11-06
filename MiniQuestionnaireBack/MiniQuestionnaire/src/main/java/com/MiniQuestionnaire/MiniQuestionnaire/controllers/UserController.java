package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.User;
import com.MiniQuestionnaire.MiniQuestionnaire.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/user/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value="id") long id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @GetMapping("api/user/list/{id}")
    public List<UserDTO> getListUsersById(@PathVariable(value="id") long id){
        return userService.getListUsersById(id);
    }

    @GetMapping("api/user/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal){
        return new ResponseEntity<>(userService.getCurrentUser(principal),HttpStatus.OK);
    }
}