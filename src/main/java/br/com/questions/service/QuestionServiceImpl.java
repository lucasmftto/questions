package br.com.questions.service;

import br.com.questions.dto.QuestionDto;
import br.com.questions.entity.Flag;
import br.com.questions.entity.Question;
import br.com.questions.entity.User;
import br.com.questions.repository.FlagRepository;
import br.com.questions.repository.QuestionRepository;
import br.com.questions.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private FlagService flagService;

    @Autowired
    private UserService userService;

    @Override
    public Question insertQuestion(QuestionDto questionDto) {
        Question question = new Question();
        logger.info("Inserindo  Question " + questionDto.toString());

        question.setComment(questionDto.getComment());
        question.setResolved(false);
        question.setUser(this.userService.findById(questionDto.getIdUser()));
        question.setFlag(this.flagService.findById(questionDto.getIdFlag()));

        return questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        this.questionRepository.findById(id).map(
                question -> {
                    logger.info("Deletanto objeto Question: " + question.toString());
                    this.questionRepository.delete(question);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question não encontrado"));
    }

    @Override
    public void updateQuestion(Long id, QuestionDto questionDto) {
        logger.info("Atualizando objeto Question: " + id);
        questionRepository.findById(id).map(
                question -> {
                    question.setComment(questionDto.getComment());
                    question.setResolved(questionDto.getResolved());
                    question.setUser(this.userService.findById(questionDto.getIdUser()));
                    question.setFlag(this.flagService.findById(questionDto.getIdFlag()));
                    questionRepository.save(question);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question não encontrado"));
    }

    @Override
    public List<Question> findAllQuestion() {
        return questionRepository.findAll();
    }

    @Override
    public Question findById(Long id) {
        logger.info("Pesquisando objeto Question: " + id);
        return this.questionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question não encontrado"));
    }
}
