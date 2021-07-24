package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "Nome obrigatorio")
    private String name;

    @Column(updatable = false)
    @Email
    @NotNull(message = "E-mail obrigatorio")
    private String email;

    @Column
    @NotNull(message = "Data de nascimento obrigatorio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Column
    private Boolean enabled;

    @PrePersist
    public void prePersist() {
        setEnabled(true);
    }
}
