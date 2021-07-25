package br.com.questions.repository;

import br.com.questions.entity.VoteAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteAnswerRepository extends JpaRepository<VoteAnswer, Long>{
   void deleteByQuestionId(Long id);

    void deleteByAnswerId(Long id);

    long deleteByAnswerIdAndQuestionId(Long id, Long questionId);

    VoteAnswer findByAnswerIdAndQuestionId(Long answerId, Long questionId);
}
