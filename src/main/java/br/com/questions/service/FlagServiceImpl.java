package br.com.questions.service;

import br.com.questions.entity.Flag;
import br.com.questions.repository.FlagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FlagServiceImpl implements FlagService{

    Logger logger = LoggerFactory.getLogger(FlagServiceImpl.class);

    @Autowired
    private FlagRepository flagRepository;

    @Override
    public Flag insertFlag(Flag flag) {
        logger.info("Inseringo objeto flag: " + flag.toString());
        return this.flagRepository.save(flag);
    }

    @Override
    public void deleteFlag(Long id) {
        this.flagRepository.findById(id).map(
                flag -> {
                    logger.info("Deletanto objeto flag: " + flag.toString());
                    this.flagRepository.delete(flag);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "flag não encontrada"));

    }

    @Override
    public void updateFlag(Long id, Flag newflag) {
        logger.info("Atualizando objeto flag: " + newflag.toString());
        this.flagRepository.findById(id).map(
                flag -> {
                    newflag.setId(flag.getId());
                    flagRepository.save(newflag);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flag não encontrada"));
    }

    @Override
    public List<Flag> findAllFlag() {
        return this.flagRepository.findAll();
    }

    @Override
    public Flag findById(Long id) {
        logger.info("Pesquisando objeto Flag: " + id);
        return this.flagRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flag não encontrada"));
    }
}
