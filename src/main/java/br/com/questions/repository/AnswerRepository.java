package br.com.questions.repository;

import br.com.questions.dto.ForumDto;
import br.com.questions.entity.Answer;
import br.com.questions.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{
    void deleteByQuestionId(Long questionId);

    List<Answer> findByQuestionId(Long id);

//    @Query(
//            value = "SELECT a.* FROM Answer a inner join Question q on a.id_question = q.id " +
//                    " inner join User u on q.id_user = u.id " +
//                    " where upper(q.comment) like upper( :pergunta)  and upper(u.name) like upper(:nome) and upper(u.email) like upper(:email) " +
//                    "  ORDER BY id",
//            countQuery = "SELECT count(*) FROM Answer a inner join Question q on a.id_question = q.id " +
//                    " inner join User u on q.id_user = u.id " +
//                    " where upper(q.comment) like upper( :pergunta)  and upper(u.name) like upper(:nome) and upper(u.email) like upper(:email) " +
//                    "  ORDER BY id",
//            nativeQuery = true)
//    Page<Answer> findByAnswerAndNomeAndEmail(Pageable pageable,
//                                               @Param("pergunta") String pergunta,
//                                               @Param("nome") String nome,
//                                               @Param("email") String email);

    @Query(
            value = "SELECT a.comment as resposta, q.comment as pergunta, u.name, u.email, ru.score FROM questions.Answer a " +
                    " inner join Question q on a.id_question = q.id   " +
                    " inner join User u on a.id_user = u.id " +
                    " inner join reputation_user ru on ru.id_user = u.id " +

                    " where upper(q.comment) like upper( :pergunta)  and upper(u.name) like upper(:nome) and upper(u.email) like upper(:email) " +
                    "  ORDER BY 1",
            countQuery = "SELECT count(*) FROM questions.Answer a " +
                    " inner join Question q on a.id_question = q.id   " +
                    " inner join User u on a.id_user = u.id " +
                    " inner join reputation_user ru on ru.id_user = u.id " +
                    " where upper(q.comment) like upper( :pergunta)  and upper(u.name) like upper(:nome) and upper(u.email) like upper(:email) " +
                    "  ORDER BY 1",
            nativeQuery = true)
    Page<Object[]> findByAnswerAndNomeAndEmail(Pageable pageable,
                                               @Param("pergunta") String pergunta,
                                               @Param("nome") String nome,
                                               @Param("email") String email);
}
