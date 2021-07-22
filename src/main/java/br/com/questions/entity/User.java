package br.com.questions.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty
    private String name;

    @Column
    @Email
    @NotEmpty
    private String email;

    @Column
    @NotEmpty
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate updateAt;

    @Column
    private Boolean enabled;
}
