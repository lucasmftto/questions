package br.com.questions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class QuestionDto {

    private Boolean resolved;

    @NotNull(message = "Comentario Obrigatorio")
    private String comment;

    @NotNull(message = "Codigo da Flag Obrigatorio")
    private Long idFlag;

    @NotNull(message = "Codigo do Usuario Obrigatorio")
    private Long idUser;
}
