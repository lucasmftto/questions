package br.com.questions.service;

import br.com.questions.dto.QuestionDto;
import br.com.questions.entity.Question;
import br.com.questions.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionService {
    Question insertQuestion(QuestionDto question);
    void deleteQuestion(Long id);
    void updateQuestion(Long id, QuestionDto questionDto);
    List<Question> findAllQuestion();
    Question findById(Long id);
    void updateResolved(Long id, Boolean resolved);

    Page<Question> findByAnswerAndNomeAndEmail(Integer page, Integer pageSize, String pergunta, String nomeUsuario, String email);
}
