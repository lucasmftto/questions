package br.com.questions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
