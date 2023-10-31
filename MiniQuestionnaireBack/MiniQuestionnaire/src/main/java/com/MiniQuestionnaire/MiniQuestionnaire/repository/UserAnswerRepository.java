package com.MiniQuestionnaire.MiniQuestionnaire.repository;

import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.User;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

        List<UserAnswer> findUserAnswerByAnswerIdAndUserId(Long id, Long idUser);

        List<UserAnswer> findUserAnswerByAnswerId(Long id);
}