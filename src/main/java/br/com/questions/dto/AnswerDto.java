package br.com.questions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AnswerDto {

    @NotNull(message = "User obrigatorio")
    private Long idUser;

    @NotNull(message = "Question obrigatorio")
    private Long idQuestion;

    @NotNull(message = "Comment obrigatorio")
    private String comment;
}
