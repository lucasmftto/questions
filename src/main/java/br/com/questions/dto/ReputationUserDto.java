package br.com.questions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReputationUserDto {

    @NotNull(message = "User obrigatorio")
    private Long idUser;

    @NotNull(message = "Score obrigatorio")
    private Long score;
}
