package com.MiniQuestionnaire.MiniQuestionnaire.payload.request;


import com.MiniQuestionnaire.MiniQuestionnaire.annotations.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {
    @NotEmpty(message = "Please enter your username")
    private String username;
    @NotEmpty(message = "Password is required")
    @Size(min = 5)
    private String password;
    private String confirmPassword;
}
