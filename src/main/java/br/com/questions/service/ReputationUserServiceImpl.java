package br.com.questions.service;

import br.com.questions.dto.ReputationUserDto;
import br.com.questions.entity.ReputationUser;
import br.com.questions.repository.ReputationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ReputationUserServiceImpl implements ReputationUserService{

    Logger logger = LoggerFactory.getLogger(ReputationUserServiceImpl.class);

    @Autowired
    private ReputationUserRepository reputationUserRepository;

    @Autowired
    private UserService userService;

    @Override
    public ReputationUser insertReputationUser(ReputationUserDto reputationUserDto) {
        ReputationUser reputationUser = new ReputationUser();
        logger.info("ReputationUser  ReputationUser " + reputationUserDto.toString());

        reputationUser.setScore(reputationUserDto.getScore());
        reputationUser.setUser(this.userService.findById(reputationUserDto.getIdUser()));

        return reputationUserRepository.save(reputationUser);
    }

    @Override
    public void deleteReputationUser(Long id) {
        this.reputationUserRepository.findById(id).map(
                reputationUser -> {
                    logger.info("Deletanto objeto ReputationUser: " + reputationUser.toString());
                    this.reputationUserRepository.delete(reputationUser);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ReputationUser não encontrado"));
    }

    @Override
    public void updateReputationUser(Long id, ReputationUserDto reputationUserDto) {
        logger.info("Atualizando objeto ReputationUser: " + reputationUserDto.toString());
        reputationUserRepository.findById(id).map(
                reputationUser -> {
                    reputationUser.setScore(reputationUserDto.getScore());
                    reputationUser.setUser(this.userService.findById(reputationUserDto.getIdUser()));
                    reputationUserRepository.save(reputationUser);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ReputationUser não encontrado"));
    }

    @Override
    public List<ReputationUser> findAllReputationUser() {
        return reputationUserRepository.findAll();
    }

    @Override
    public ReputationUser findByIdReputationUser(Long id) {
        logger.info("Pesquisando ReputationUser por Id: " + id);
        return this.reputationUserRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ReputationUser não encontrado"));
    }
}
