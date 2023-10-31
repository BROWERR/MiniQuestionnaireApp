package com.MiniQuestionnaire.MiniQuestionnaire.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String question;

    private Integer answer_options;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_questionnaire")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Questionnaire questionnaire;

    public Question (String question, Integer answer_options, Questionnaire questionnaire){
        this.question = question;
        this.answer_options = answer_options;
        this.questionnaire = questionnaire;
    }

    public Question() {

    }
}