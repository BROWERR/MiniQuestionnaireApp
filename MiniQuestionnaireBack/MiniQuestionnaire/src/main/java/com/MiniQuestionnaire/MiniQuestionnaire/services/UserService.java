package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.UserAnswer;
import com.MiniQuestionnaire.MiniQuestionnaire.exceptions.UserExistException;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.User;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.UserMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.payload.request.SignupRequest;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.AnswerRepository;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.QuestionRepository;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.UserAnswerRepository;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    public static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserAnswerRepository userAnswerRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(AnswerRepository answerRepository, QuestionRepository questionRepository,
                       UserRepository userRepository, BCryptPasswordEncoder passwordEncoder,
                       UserAnswerRepository userAnswerRepository, UserMapper userMapper) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAnswerRepository = userAnswerRepository;
        this.userMapper = userMapper;
    }

    public User getCurrentUser(Principal principal) {
        return userRepository.findUserByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException(
                "User not found with username:" + principal.getName()
        ));
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException(
                "User not found"
        ));
    }

    public UserDTO[] getListUsersById(Long id) {
        List<Question> listQuestions = questionRepository.findAllByQuestionnaireId(id);
        List<Answer> listAnswers = new ArrayList<>();
        for (int i = 0; i < listQuestions.size(); i++) {
            List<Answer> an = answerRepository.findAllByQuestion(listQuestions.get(i));
            listAnswers.addAll(an);
            an = null;
        }
        List<User> listUser = new ArrayList<>();
        for (int i = 0; i < listAnswers.size(); i++) {
            List<UserAnswer> usr = userAnswerRepository.findUserAnswerByAnswerId(listAnswers.get(i).getId());
            for (int j = 0; j < usr.size(); j++) {
                listUser.add(usr.get(j).getUser());
            }
            usr = null;
        }

        Set<UserDTO> userSet = listUser.stream().map(userMapper::toUserDTO)
                .collect(Collectors.toSet());

        return userSet.stream().toArray(UserDTO[]::new);
    }

    public void createUser(SignupRequest userIn) {
        User user = new User();
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setRole(0);

        try {
            LOG.info("Saving User {}", userIn.getUsername());
            userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }
}