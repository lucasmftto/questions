package br.com.questions.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class QuestionsFlags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "flag_id")
    private Flag flag;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
