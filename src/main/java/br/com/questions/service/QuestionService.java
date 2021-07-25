package br.com.questions.service;

import br.com.questions.dto.QuestionDto;
import br.com.questions.entity.Question;

import java.util.List;

public interface QuestionService {
    Question insertQuestion(QuestionDto question);
    void deleteQuestion(Long id);
    void updateQuestion(Long id, QuestionDto questionDto);
    List<Question> findAllQuestion();
    Question findById(Long id);
}
