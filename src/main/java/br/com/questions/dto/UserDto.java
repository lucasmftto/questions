package br.com.questions.dto;

import br.com.questions.entity.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "Nome obrigatorio")
    private String name;

    @NotNull(message = "E-mail obrigatorio")
    private String email;

    @NotNull(message = "Data de nascimento obrigatorio")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @NotNull(message = "Role obrigatorio")
    private Long roleId;

    private Boolean enabled;
}
