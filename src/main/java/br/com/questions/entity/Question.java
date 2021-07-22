package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @NotEmpty
    @OneToMany(mappedBy = "question")
    private Set<QuestionsFlags> questionsFlags;

    @Column
    @NotEmpty
    private String comment;

    @Column
    private Boolean resolved;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updateAt;
}
