package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @NotEmpty
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_question")
    @NotEmpty
    private Question question;

    @Column
    @NotEmpty
    private String comment;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updateAt;
}
