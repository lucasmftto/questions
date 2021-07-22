package br.com.questions.repository;

import br.com.questions.entity.ReputationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReputationUserRepository extends JpaRepository<ReputationUser, Integer>{
}
