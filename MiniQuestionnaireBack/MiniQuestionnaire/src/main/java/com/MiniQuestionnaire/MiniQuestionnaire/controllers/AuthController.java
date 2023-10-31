package com.MiniQuestionnaire.MiniQuestionnaire.controllers;

import com.MiniQuestionnaire.MiniQuestionnaire.payload.request.LoginRequest;
import com.MiniQuestionnaire.MiniQuestionnaire.payload.request.SignupRequest;
import com.MiniQuestionnaire.MiniQuestionnaire.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@PreAuthorize("permitAll()")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/api/auth/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        return authService.authenticateUser(loginRequest, bindingResult);
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        return authService.registerUser(signupRequest, bindingResult);
    }
}
