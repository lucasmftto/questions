package br.com.questions.repository;

import br.com.questions.entity.Flag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlagRepository extends JpaRepository<Flag, Integer>{
}
