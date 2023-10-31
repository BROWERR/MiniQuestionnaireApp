package com.MiniQuestionnaire.MiniQuestionnaire.services;

import com.MiniQuestionnaire.MiniQuestionnaire.dto.AllWholeDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.dto.AnswerDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionnaireDTO;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Answer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Question;
import com.MiniQuestionnaire.MiniQuestionnaire.dto.QuestionToServer;
import com.MiniQuestionnaire.MiniQuestionnaire.entity.Questionnaire;
import com.MiniQuestionnaire.MiniQuestionnaire.mapper.QuestionnaireMapper;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.AnswerRepository;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.QuestionRepository;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.QuestionnaireRepository;
import com.MiniQuestionnaire.MiniQuestionnaire.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    @Autowired
    private QuestionnaireMapper questionnaireMapper;
    @Autowired
    private UserAnswerRepository userAnswerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerRepository answerRepository;

    public List<QuestionnaireDTO> getAllQuestionnaires() {
        List<Questionnaire> questionnaires = questionnaireRepository.findAll();
        return questionnaires.stream().map(questionnaireMapper::toQuestionnaireDTO)
                .collect(Collectors.toList());
    }

    public void addQuestionnaire(AllWholeDTO allWholeDTO) {
        questionnaireRepository.save(allWholeDTO.getQuestionnaire());
        Questionnaire questionnaire = questionnaireRepository.findQuestionnaireByName(allWholeDTO.getQuestionnaire().getName());
        for (int i = 0; i < allWholeDTO.getQuestions().length; i++) {
            Question newQuestion = new Question(
                    allWholeDTO.getQuestions()[i].getQuestionTitle(),
                    allWholeDTO.getQuestions()[i].getAnswer_options(),
                    questionnaire
            );
            questionService.addQuestion(newQuestion);
            Question question = questionRepository.findQuestionByQuestion(newQuestion.getQuestion());
            for (int j = 0; j < allWholeDTO.getQuestions()[i].getAnswers().length; j++) {
                Answer answer = new Answer(
                        allWholeDTO.getQuestions()[i].getAnswers()[j].getAnswer(),
                        question
                );
                answerService.addAnswer(answer);
            }
        }
    }

    public AllWholeDTO getQuestionnaireById(Long id) {
        AllWholeDTO questionnaire = new AllWholeDTO();
        questionnaire.setId(id);
        questionnaire.setQuestionnaire(questionnaireRepository.findById(id).orElseThrow());
        List<Question> questions = questionRepository.findAllByQuestionnaire(questionnaireRepository.
                findById(id).orElseThrow());
        List<Answer> answers;
        QuestionToServer[] questionsToClient = new QuestionToServer[questions.size()];
        AnswerDTO[] updateAnswers;
        for (int i = 0; i < questionsToClient.length; i++) {
            questionsToClient[i] = new QuestionToServer();
            questionsToClient[i].setId(questions.get(i).getId());
            questionsToClient[i].setAnswer_options(questions.get(i).getAnswer_options());
            questionsToClient[i].setQuestionTitle(questions.get(i).getQuestion());
            answers = answerRepository.findAllByQuestion(questions.get(i));
            updateAnswers = new AnswerDTO[answers.size()];
            for (int j = 0; j < answers.size(); j++) {
                updateAnswers[j] = new AnswerDTO();
                updateAnswers[j].setId(answers.get(j).getId());
                updateAnswers[j].setAnswer(answers.get(j).getAnswer());
            }
            questionsToClient[i].setAnswers(updateAnswers);
        }
        questionnaire.setQuestions(questionsToClient);
        return questionnaire;
    }

    public AllWholeDTO getQuestionnaireAnswerById(Long id, Long idUser) {
        AllWholeDTO questionnaire = new AllWholeDTO();
        questionnaire.setId(id);
        questionnaire.setQuestionnaire(questionnaireRepository.findById(id).orElseThrow());
        List<Question> questions = questionRepository.findAllByQuestionnaire(questionnaireRepository.
                findById(id).orElseThrow());
        List<Answer> answers;
        QuestionToServer[] questionsToClient = new QuestionToServer[questions.size()];
        AnswerDTO[] updateAnswers;
        for (int i = 0; i < questionsToClient.length; i++) {
            questionsToClient[i] = new QuestionToServer();
            questionsToClient[i].setId(questions.get(i).getId());
            questionsToClient[i].setAnswer_options(questions.get(i).getAnswer_options());
            questionsToClient[i].setQuestionTitle(questions.get(i).getQuestion());

            answers = answerRepository.findAllByQuestion(questions.get(i));
            List<Answer> userAnswers = new ArrayList<>();
            for (int j = 0; j < answers.size(); j++) {
                if (userAnswerRepository.findUserAnswerByAnswerIdAndUserId(answers.get(j).getId(), idUser).size() > 0)
                    userAnswers.add(userAnswerRepository.findUserAnswerByAnswerIdAndUserId(answers.get(j).getId(), idUser)
                            .get(0).getAnswer());
            }

            updateAnswers = new AnswerDTO[userAnswers.size()];
            for (int j = 0; j < userAnswers.size(); j++) {
                updateAnswers[j] = new AnswerDTO();
                updateAnswers[j].setId(userAnswers.get(j).getId());
                updateAnswers[j].setAnswer(userAnswers.get(j).getAnswer());
            }
            questionsToClient[i].setAnswers(updateAnswers);
        }
        questionnaire.setQuestions(questionsToClient);
        return questionnaire;
    }

    public void deleteQuestionnaireById(Long id) {
        questionnaireRepository.delete(questionnaireRepository.findById(id).orElseThrow());
    }

    public void updateQuestionnaire(AllWholeDTO allWholeDTO) {
        Questionnaire updateQuestionnaire = questionnaireRepository.findById(
                allWholeDTO.getQuestionnaire().getId()).orElseThrow();
        updateQuestionnaire.setName(allWholeDTO.getQuestionnaire().getName());
        questionnaireRepository.save(updateQuestionnaire);

        List<Question> updateQuestions = questionRepository.findAllByQuestionnaire(allWholeDTO.getQuestionnaire());
        List<Answer> updateAnswers;
        for (int i = 0; i < updateQuestions.size(); i++) {
            updateQuestions.get(i).setQuestion(allWholeDTO.getQuestions()[i].getQuestionTitle());
            updateQuestions.get(i).setAnswer_options(allWholeDTO.getQuestions()[i].getAnswer_options());
            updateQuestions.get(i).setQuestionnaire(updateQuestionnaire);
            questionRepository.save(updateQuestions.get(i));
            updateAnswers = answerRepository.findAllByQuestion(updateQuestions.get((i)));
            for (int j = 0; j < updateAnswers.size(); j++) {
                updateAnswers.get(j).setAnswer(allWholeDTO.getQuestions()[i].getAnswers()[j].getAnswer());
                updateAnswers.get(j).setQuestion(updateQuestions.get(i));
                answerRepository.save(updateAnswers.get(j));
            }
        }
    }
}