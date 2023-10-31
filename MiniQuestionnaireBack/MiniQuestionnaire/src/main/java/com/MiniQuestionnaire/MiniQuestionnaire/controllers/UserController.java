package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.User;
import com.MiniQuestionnaire.MiniQuestionnaire.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("api/user/{id}")
    public User getUserById(@PathVariable(value="id") long id){
        return userService.getUserById(id);
    }

    @GetMapping("api/user/list/{id}")
    public UserDTO[] getListUsersById(@PathVariable(value="id") long id){
        return userService.getListUsersById(id);
    }

    @GetMapping("api/user/")
    public User getCurrentUser(Principal principal){
        return userService.getCurrentUser(principal);
    }
}