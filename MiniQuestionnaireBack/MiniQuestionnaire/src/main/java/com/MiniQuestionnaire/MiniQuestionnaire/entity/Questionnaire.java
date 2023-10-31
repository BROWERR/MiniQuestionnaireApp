package com.MiniQuestionnaire.MiniQuestionnaire.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String name;
}