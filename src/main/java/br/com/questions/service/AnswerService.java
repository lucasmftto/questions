package br.com.questions.service;

import br.com.questions.dto.AnswerDto;
import br.com.questions.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer insertAnswer(AnswerDto answerDto);
    void deleteAnswer(Long id);
    void updateAnswer(Long id, AnswerDto answerDto);
    List<Answer> findAllAnswer();
    Answer findById(Long id);
}
