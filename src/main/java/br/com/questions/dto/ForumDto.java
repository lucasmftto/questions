package br.com.questions.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ForumDto {

    private String resposta;

    private String pergunta;

    private String name;

    private String email;

    private Long score;
}
