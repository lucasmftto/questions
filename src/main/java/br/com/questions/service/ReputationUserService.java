package br.com.questions.service;

import br.com.questions.dto.ReputationUserDto;
import br.com.questions.entity.ReputationUser;

import java.util.List;

public interface ReputationUserService {
    ReputationUser insertReputationUser(ReputationUserDto reputationUserDto);
    void deleteReputationUser(Long id);
    void updateReputationUser(Long id, ReputationUserDto reputationUserDto);
    void updateReputationUser(ReputationUser reputationUser);
    List<ReputationUser> findAllReputationUser();
    ReputationUser findByIdReputationUser(Long id);

    ReputationUser findByUserId(Long id);
}
