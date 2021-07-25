package br.com.questions.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @ManyToOne()
    @JoinColumn(name = "id_flag")
    private Flag flag;

    @Column
    @NotNull(message = "Comentario obrigatorio")
    private String comment;

    @Column
    private Boolean resolved;
}
