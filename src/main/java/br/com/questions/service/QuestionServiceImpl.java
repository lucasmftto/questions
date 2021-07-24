package br.com.questions.service;

import br.com.questions.entity.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements  QuestionService{
    @Override
    public Question insertQuestion(Question question) {
        return null;
    }

    @Override
    public void deleteQuestion(Long id) {

    }

    @Override
    public void updateQuestion(Long id, Question newQuestion) {

    }

    @Override
    public List<Question> findAllQuestion() {
        return null;
    }

    @Override
    public Question findById(Long id) {
        return null;
    }
}
