package br.com.questions.service;

import br.com.questions.dto.AnswerDto;
import br.com.questions.entity.Answer;
import br.com.questions.repository.AnswerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{

    Logger logger = LoggerFactory.getLogger(AnswerServiceImpl.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer insertAnswer(AnswerDto answerDto) {
        Answer answer = new Answer();
        logger.info("Inserindo  Answer " + answerDto.toString());

        answer.setComment(answerDto.getComment());
        answer.setUser(this.userService.findById(answerDto.getIdUser()));
        answer.setQuestion(this.questionService.findById(answerDto.getIdQuestion()));

        return this.answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Long id) {
        this.answerRepository.findById(id).map(
                answer -> {
                    logger.info("Deletanto objeto Answer: " + answer.toString());
                    this.answerRepository.delete(answer);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer não encontrado"));
    }

    @Override
    public void updateAnswer(Long id, AnswerDto answerDto) {
        logger.info("Atualizando objeto Answer: " + answerDto.toString());
        this.answerRepository.findById(id).map(
                answer -> {
                    answer.setComment(answerDto.getComment());
                    answer.setUser(this.userService.findById(answerDto.getIdUser()));
                    answer.setQuestion(this.questionService.findById(answerDto.getIdQuestion()));
                    this.answerRepository.save(answer);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer não encontrado"));
    }

    @Override
    public List<Answer> findAllAnswer() {
        return answerRepository.findAll();
    }

    @Override
    public Answer findById(Long id) {
        logger.info("Pesquisando Answer por Id: " + id);
        return this.answerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Answer não encontrado"));
    }
}
