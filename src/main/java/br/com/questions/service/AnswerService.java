package br.com.questions.service;

import br.com.questions.dto.AnswerDto;
import br.com.questions.dto.ForumDto;
import br.com.questions.entity.Answer;
import br.com.questions.entity.Question;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AnswerService {
    Answer insertAnswer(AnswerDto answerDto);
    void deleteAnswer(Long id);
    void updateAnswer(Long id, AnswerDto answerDto);
    List<Answer> findAllAnswer();
    Answer findById(Long id);

    void deleteAnswerByQuestion(Long id);

    List<Answer> findAnswerByQuestion(Long id);

    Page<Object[]> findByAnswerAndNomeAndEmail(Integer page, Integer pageSize, String pergunta, String nomeUsuario, String email);
}
