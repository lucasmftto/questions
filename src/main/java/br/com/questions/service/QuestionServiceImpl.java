package br.com.questions.service;

import br.com.questions.dto.QuestionDto;
import br.com.questions.entity.*;
import br.com.questions.repository.FlagRepository;
import br.com.questions.repository.QuestionRepository;
import br.com.questions.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
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

    @Autowired
    private  AnswerService answerService;

    @Autowired
    private  VoteAnswerService voteAnswerService;

    @Autowired
    private  ReputationUserService reputationUserService;

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
    @Transactional
    public void deleteQuestion(Long id) {
        List<Answer> answerList = this.answerService.findAnswerByQuestion(id);

        for (Answer answer : answerList) {
            VoteAnswer voteAnswer = this.voteAnswerService.findByAnswerAndQuestion(answer.getId(), id);
            ReputationUser reputationUser = this.reputationUserService.findByUserId(voteAnswer.getUser().getId());
            reputationUser.setScore(reputationUser.getScore() - voteAnswer.getScore());
            this.reputationUserService.updateReputationUser(reputationUser);
            this.voteAnswerService.deleteAVoteAnswer(voteAnswer.getId());
        }

        answerList.stream().forEach(answer -> this.answerService.deleteAnswer(answer.getId()) );

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

    @Override
    public void updateResolved(Long id, Boolean resolved) {
        logger.info("Atualizando objeto Question para Resolved : " + id);
        questionRepository.findById(id).map(
                question -> {
                    question.setResolved(resolved);
                    questionRepository.save(question);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question não encontrado"));
    }

    @Override
    public Page<Question> findByAnswerAndNomeAndEmail(Integer page, Integer pageSize, String pergunta, String nomeUsuario, String email) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");

        return this.questionRepository.findByAnswerAndNomeAndEmail(pageable, pergunta, nomeUsuario, email);
    }
}
