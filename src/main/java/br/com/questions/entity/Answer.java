package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class Answer extends Auditing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @NotNull(message = "User obrigatorio")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_question")
    @NotNull(message = "Question obrigatorio")
    private Question question;

    @Column
    @NotNull(message = "Comment obrigatorio")
    private String comment;

}
