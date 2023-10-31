package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.payload.request.LoginRequest;
import com.MiniQuestionnaire.MiniQuestionnaire.payload.request.SignupRequest;
import com.MiniQuestionnaire.MiniQuestionnaire.payload.response.JWTTokenSuccessResponse;
import com.MiniQuestionnaire.MiniQuestionnaire.payload.response.MessageResponse;
import com.MiniQuestionnaire.MiniQuestionnaire.security.JWTTokenProvider;
import com.MiniQuestionnaire.MiniQuestionnaire.security.SecurityConstants;
import com.MiniQuestionnaire.MiniQuestionnaire.validations.ResponseErrorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;

@Service
public class AuthService {
    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ResponseErrorValidation responseErrorValidation;
    @Autowired
    private UserService userService;

    public ResponseEntity<Object> registerUser(SignupRequest signupRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        userService.createUser(signupRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<Object> authenticateUser(LoginRequest loginRequest, BindingResult bindingResult){
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt));
    }
}