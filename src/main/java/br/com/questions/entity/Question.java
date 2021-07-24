package br.com.questions.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
public class Question extends Auditing{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Usuario obrigatorio")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @NotNull(message = "Flag obrigatorio")
    @OneToMany(mappedBy = "question")
    private Set<QuestionsFlags> questionsFlags;

    @Column
    @NotNull(message = "Comentario obrigatorio")
    private String comment;

    @Column
    private Boolean resolved;
}
