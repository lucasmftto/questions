package br.com.questions.service;

import br.com.questions.dto.VoteAnswerDto;
import br.com.questions.entity.ReputationUser;
import br.com.questions.entity.VoteAnswer;
import br.com.questions.repository.VoteAnswerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VoteAnswerServiceImpl implements VoteAnswerService{

    Logger logger = LoggerFactory.getLogger(VoteAnswerServiceImpl.class);

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private ReputationUserService reputationUserService;

    @Autowired
    private VoteAnswerRepository voteAnswerRepository;

    @Override
    public VoteAnswer insertVoteAnswer(VoteAnswerDto voteAnswerDto) {
        VoteAnswer voteAnswer = new VoteAnswer();
        logger.info("Inserindo  VoteAnswer " + voteAnswerDto.toString());

        voteAnswer.setScore(voteAnswerDto.getScore());
        voteAnswer.setUser(this.userService.findById(voteAnswerDto.getUserId()));
        voteAnswer.setQuestion(this.questionService.findById(voteAnswerDto.getQuestionId()));
        voteAnswer.setAnswer(this.answerService.findById(voteAnswerDto.getAnswerId()));

        return this.voteAnswerRepository.save(voteAnswer);
    }

    @Override
    public void deleteAVoteAnswer(Long id) {
        this.voteAnswerRepository.findById(id).map(
                voteAnswer -> {
                    logger.info("Deletanto objeto VoteAnswer: " + voteAnswer.toString());

                    ReputationUser ru = reputationUserService.findByUserId(voteAnswer.getUser().getId());
                    ru.setScore(ru.getScore() - voteAnswer.getScore());
                    reputationUserService.updateReputationUser(ru);

                    this.voteAnswerRepository.delete(voteAnswer);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "VoteAnswer não encontrado"));
    }

    @Override
    public void updateVoteAnswer(Long id, VoteAnswerDto voteAnswerDto) {
        logger.info("Atualizando objeto VoteAnswer: " + voteAnswerDto.toString());
        this.voteAnswerRepository.findById(id).map(
                voteAnswer -> {
                    voteAnswer.setScore(voteAnswerDto.getScore());
                    voteAnswer.setUser(this.userService.findById(voteAnswerDto.getUserId()));
                    voteAnswer.setQuestion(this.questionService.findById(voteAnswerDto.getQuestionId()));
                    voteAnswer.setAnswer(this.answerService.findById(voteAnswerDto.getAnswerId()));
                    this.voteAnswerRepository.save(voteAnswer);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "VoteAnswer não encontrado"));
    }

    @Override
    public List<VoteAnswer> findAllVoteAnswer() {
        return voteAnswerRepository.findAll();
    }

    @Override
    public VoteAnswer findById(Long id) {
        logger.info("Pesquisando VoteAnswer por Id: " + id);
        return this.voteAnswerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "VoteAnswer não encontrado"));
    }

    @Override
    public void deleteVoteAnswerByQuestion(Long questionId) {
        logger.info("Deletetando VoteAnswer por questionId: " + questionId);
        this.voteAnswerRepository.deleteByQuestionId(questionId);
    }

    @Override
    public void deleteVoteAnswerByAnswer(Long id, Long questionId) {
        logger.info("Deletetando VoteAnswer por Answer: " + id);
        this.voteAnswerRepository.deleteByAnswerIdAndQuestionId(id, questionId);
    }

    @Override
    public VoteAnswer findByAnswerAndQuestion(Long answerId, Long questionId) {
        logger.info("Find VoteAnswer por Answer and Question: " + questionId);
        return this.voteAnswerRepository.findByAnswerIdAndQuestionId(answerId, questionId);
    }
}
