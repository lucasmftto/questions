package br.com.questions.repository;

import br.com.questions.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(" select u from User u "
            + " where upper(u.name) like upper( :nome) and upper(u.email) like upper( :email) and (1=:isBirthDate or u.birthDate = :birthDate)  ")
    Page<User> findByNomeAndEmailAndBirthDate(Pageable pageable, @Param("nome") String nome,
                                              @Param("email") String email,
                                              @Param("isBirthDate") Integer isBirthDate,
                                              @Param("birthDate") LocalDate birthDate);
}
