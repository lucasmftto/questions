package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Entity
public class ReputationUser extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Usuario obrigatorio")
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @Column
    @NotNull(message = "Score obrigatorio")
    private Long score;

}
