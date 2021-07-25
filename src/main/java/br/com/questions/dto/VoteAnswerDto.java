package br.com.questions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class VoteAnswerDto {

    @NotNull(message = "User obrigatorio")
    private Long userId;

    @NotNull(message = "Question obrigatorio")
    private Long questionId;

    @NotNull(message = "Answer obrigatorio")
    private Long answerId;

    @NotNull(message = "score obrigatorio")
    private Long score;
}
