package br.com.questions.service;

import br.com.questions.dto.VoteAnswerDto;
import br.com.questions.entity.VoteAnswer;

import java.util.List;

public interface VoteAnswerService {
    VoteAnswer insertVoteAnswer(VoteAnswerDto voteAnswerDto);
    void deleteAVoteAnswer(Long id);
    void updateVoteAnswer(Long id, VoteAnswerDto voteAnswerDto);
    List<VoteAnswer> findAllVoteAnswer();
    VoteAnswer findById(Long id);
}
