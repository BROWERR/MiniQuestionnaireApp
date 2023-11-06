package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.UserDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.UserAnswer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.enums.EROLE;
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

    public UserDTO getCurrentUser(Principal principal) {
        return userMapper.toUserDTO(userRepository.findUserByUsername(principal.getName()).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username:" + principal.getName()))
        );
    }

    public UserDTO getUserById(Long id) {
        return userMapper.toUserDTO(userRepository.findUserById(id).orElseThrow(() -> new UsernameNotFoundException(
                "User not found"
        )));
    }

    public List<UserDTO> getListUsersById(Long id) {
        List<Question> listQuestions = questionRepository.findAllByQuestionnaireId(id);
        List<Answer> listAnswers = answerRepository.findByQuestionIn(listQuestions);
        List<Long> answerId = new ArrayList<>();
        for (int i = 0; i < listAnswers.size(); i++) {
            answerId.add(listAnswers.get(i).getId());
        }
        List<User> listUser = new ArrayList<>();
        List<UserAnswer> usr = userAnswerRepository.findByAnswerIdIn(answerId);
        for (int i = 0; i < listAnswers.size(); i++) {
            for (int j = 0; j < usr.size(); j++) {
                listUser.add(usr.get(j).getUser());
            }
        }

        Set<UserDTO> userSet = listUser.stream().map(userMapper::toUserDTO)
                .collect(Collectors.toSet());

        return new ArrayList<>(userSet);
    }

    public void createUser(SignupRequest userIn) {
        User user = new User();
        user.setUsername(userIn.getUsername());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setRole(0);
        user.getRoles().add(EROLE.ROLE_USER);

        try {
            LOG.info("Saving User {}", userIn.getUsername());
            userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration. {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }
}