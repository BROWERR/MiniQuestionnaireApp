package com.MiniQuestionnaire.MiniQuestionnaire.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.*;

@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String answer;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_question")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Question question;

    public Answer(String answer, Question question){
        this.answer = answer;
        this.question = question;
    }

    public Answer() {

    }
}