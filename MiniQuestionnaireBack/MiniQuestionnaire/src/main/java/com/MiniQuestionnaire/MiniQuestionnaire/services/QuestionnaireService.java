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
import lombok.SneakyThrows;
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

    /**
     * *
     * Добавление анкеты
     *
     * @param allWholeDTO DTO-объект анкеты, которую нужно добавить в БД
     */
    @SneakyThrows
    public String addQuestionnaire(AllWholeDTO allWholeDTO) {
        if (allWholeDTO == null ||
                questionnaireRepository.findQuestionnaireByName(allWholeDTO.getQuestionnaire().getName()) != null) {
            return null;
        }
        questionnaireRepository.save(questionnaireMapper.toQuestionnaire(allWholeDTO.getQuestionnaire()));
        Questionnaire questionnaire = questionnaireRepository.findQuestionnaireByName(allWholeDTO.getQuestionnaire().getName());
        for (int i = 0; i < allWholeDTO.getQuestions().length; i++) {
            Question newQuestion = new Question(
                    allWholeDTO.getQuestions()[i].getQuestionTitle(),
                    allWholeDTO.getQuestions()[i].getAnswer_options(),
                    questionnaire
            );
            if (questionService.addQuestion(newQuestion) == null) {
                return null;
            }
            Question question = questionRepository.findQuestionByQuestion(newQuestion.getQuestion());
            for (int j = 0; j < allWholeDTO.getQuestions()[i].getAnswers().length; j++) {
                Answer answer = new Answer(
                        allWholeDTO.getQuestions()[i].getAnswers()[j].getAnswer(),
                        question
                );
                if (answerService.addAnswer(answer) == null) {
                    return null;
                }
            }
        }
        return "Questionnaire was successfully added";
    }

    /**
     * *
     * Получение всей информации об анкете
     *
     * @param id id анкеты
     * @return анкета
     */
    @SneakyThrows
    public AllWholeDTO getQuestionnaireById(Long id) {
        AllWholeDTO questionnaire = new AllWholeDTO();
        questionnaire.setId(id);
        questionnaire.setQuestionnaire(questionnaireMapper.toQuestionnaireDTO(questionnaireRepository.findById(id).orElseThrow()));
        List<Question> questions = questionRepository.findAllByQuestionnaire(questionnaireRepository.
                findById(id).orElseThrow());
        List<Answer> answers;
        List<QuestionToServer> questionsToClient = new ArrayList<>();
        List<AnswerDTO> updateAnswers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            questionsToClient.add(new QuestionToServer());
            questionsToClient.get(i).setId(questions.get(i).getId());
            questionsToClient.get(i).setAnswer_options(questions.get(i).getAnswer_options());
            questionsToClient.get(i).setQuestionTitle(questions.get(i).getQuestion());
            answers = answerRepository.findAllByQuestion(questions.get(i));

            for (int j = 0; j < answers.size(); j++) {
                updateAnswers.add(new AnswerDTO());
                updateAnswers.get(j).setId(answers.get(j).getId());
                updateAnswers.get(j).setAnswer(answers.get(j).getAnswer());
            }
            questionsToClient.get(i).setAnswers(updateAnswers.stream().toArray(AnswerDTO[]::new));
        }
        questionnaire.setQuestions(questionsToClient.stream().toArray(QuestionToServer[]::new));
        return questionnaire;
    }

    /**
     * *
     * Получение анкеты с ответами пользователя
     *
     * @param id     id анкеты, на основе которого будет формироваться DTO
     * @param idUser id пользователя, ответы которого нужно получить
     * @return DTO-объект, содержащий анкету с вопросами и ответами пользователя с idUser
     */
    public AllWholeDTO getQuestionnaireAnswerById(Long id, Long idUser) {
        AllWholeDTO questionnaire = new AllWholeDTO();
        questionnaire.setId(id);
        questionnaire.setQuestionnaire(questionnaireMapper.toQuestionnaireDTO(questionnaireRepository.findById(id).orElseThrow()));
        List<Question> questions = questionRepository.findAllByQuestionnaire(questionnaireRepository.
                findById(id).orElseThrow());
        List<Answer> answers;
        List<QuestionToServer> questionsToClient = new ArrayList<>();
        List<AnswerDTO> updateAnswers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            questionsToClient.add(new QuestionToServer());
            questionsToClient.get(i).setId(questions.get(i).getId());
            questionsToClient.get(i).setAnswer_options(questions.get(i).getAnswer_options());
            questionsToClient.get(i).setQuestionTitle(questions.get(i).getQuestion());

            answers = answerRepository.findAllByQuestion(questions.get(i));
            List<Answer> userAnswers = new ArrayList<>();
            for (int j = 0; j < answers.size(); j++) {
                if (userAnswerRepository.findUserAnswerByAnswerIdAndUserId(answers.get(j).getId(), idUser).size() > 0)
                    userAnswers.add(userAnswerRepository.findUserAnswerByAnswerIdAndUserId(answers.get(j).getId(), idUser)
                            .get(0).getAnswer());
            }

            for (int j = 0; j < userAnswers.size(); j++) {
                updateAnswers.add(new AnswerDTO());
                updateAnswers.get(j).setId(userAnswers.get(j).getId());
                updateAnswers.get(j).setAnswer(userAnswers.get(j).getAnswer());
            }
            questionsToClient.get(i).setAnswers(updateAnswers.stream().toArray(AnswerDTO[]::new));
        }
        questionnaire.setQuestions(questionsToClient.stream().toArray(QuestionToServer[]::new));
        return questionnaire;
    }

    @SneakyThrows
    public void deleteQuestionnaireById(Long id) {
        questionnaireRepository.delete(questionnaireRepository.findById(id).orElseThrow());
    }

    /**
     * *
     *
     * @param allWholeDTO DTO-объект анкеты, которую нужно обновить
     *                    в DTO находятся анкета, вопросы, ответы
     */
    @SneakyThrows
    public String updateQuestionnaire(AllWholeDTO allWholeDTO) {
        if (questionnaireRepository.findQuestionnaireByName(allWholeDTO.getQuestionnaire().getName()) != null) {
            return null;
        }
        Questionnaire updateQuestionnaire = questionnaireRepository.findById(
                allWholeDTO.getQuestionnaire().getId()).orElseThrow();
        updateQuestionnaire.setName(allWholeDTO.getQuestionnaire().getName());
        questionnaireRepository.save(updateQuestionnaire);

        List<Question> updateQuestions = questionRepository.findAllByQuestionnaire(questionnaireMapper.
                toQuestionnaire(allWholeDTO.getQuestionnaire()));
        List<Answer> updateAnswers;
        for (int i = 0; i < updateQuestions.size(); i++) {
            if (questionRepository.findQuestionByQuestion(updateQuestions.get(i).getQuestion()) != null){
                return null;
            }
            updateQuestions.get(i).setQuestion(allWholeDTO.getQuestions()[i].getQuestionTitle());
            updateQuestions.get(i).setAnswer_options(allWholeDTO.getQuestions()[i].getAnswer_options());
            updateQuestions.get(i).setQuestionnaire(updateQuestionnaire);
            questionRepository.save(updateQuestions.get(i));
            updateAnswers = answerRepository.findAllByQuestion(updateQuestions.get((i)));
            for (int j = 0; j < updateAnswers.size(); j++) {
                if(answerRepository.findByAnswer(updateAnswers.get(j).getAnswer()) != null){
                    return null;
                }
                updateAnswers.get(j).setAnswer(allWholeDTO.getQuestions()[i].getAnswers()[j].getAnswer());
                updateAnswers.get(j).setQuestion(updateQuestions.get(i));
                answerRepository.save(updateAnswers.get(j));
            }
        }
        return "Questionnaire was successfully updated";
    }
}