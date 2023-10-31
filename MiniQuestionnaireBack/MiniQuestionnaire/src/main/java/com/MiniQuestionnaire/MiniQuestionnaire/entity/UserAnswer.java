package com.MiniQuestionnaire.MiniQuestionnaire.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_answer")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Answer answer;

    public UserAnswer(Answer answer, User user) {
        this.answer = answer;
        this.user = user;
    }

    public UserAnswer() {
    }
}