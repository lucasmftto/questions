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

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User obrigatorio")
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @NotNull(message = "Question obrigatorio")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    @NotNull(message = "Answer obrigatorio")
    private Answer answer;

    @Column
    @NotNull(message = "score obrigatorio")
    private Long score;

}
