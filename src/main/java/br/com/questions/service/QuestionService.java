package br.com.questions.service;

import br.com.questions.entity.Question;

import java.util.List;

public interface QuestionService {
    public Question insertQuestion(Question question);
    public void deleteQuestion(Long id);
    public void updateQuestion(Long id, Question newQuestion);
    public List<Question> findAllQuestion();
    public Question findById(Long id);
}
