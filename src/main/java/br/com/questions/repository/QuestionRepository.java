package br.com.questions.repository;

import br.com.questions.entity.Question;
import br.com.questions.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

    @Query(
            value = "SELECT q.* FROM Question q  inner join User u on q.id_user = u.id " +
                    " where upper(q.comment) like upper( :pergunta)  and upper(u.name) like upper(:nome) and upper(u.email) like upper(:email) " +
                    "  ORDER BY id",
            countQuery = "SELECT count(*) FROM Question q  inner join User u on q.id_user = u.id " +
            " where upper(q.comment) like upper( :pergunta)  and upper(u.name) like upper(:nome) and upper(u.email) like upper(:email) " +
            "  ORDER BY id",
            nativeQuery = true)
    Page<Question> findByAnswerAndNomeAndEmail(Pageable pageable,
                                              @Param("pergunta") String pergunta,
                                              @Param("nome") String nome,
                                              @Param("email") String email);
}
