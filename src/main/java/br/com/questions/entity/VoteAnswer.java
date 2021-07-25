package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class VoteAnswer extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional=false)
    @JoinColumn(name = "userId", unique=true)
    private User user;

    @ManyToOne(optional=false)
    @JoinColumn(name = "questionId", unique=true)
    private Question question;

    @ManyToOne(optional=false)
    @JoinColumn(name = "answerId", unique=true)
    private Answer answer;

    @Column
    @NotNull(message = "score obrigatorio")
    private Long score;

    public VoteAnswer() {}
}
