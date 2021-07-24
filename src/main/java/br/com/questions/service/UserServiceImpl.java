package br.com.questions.service;

import br.com.questions.entity.User;
import br.com.questions.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User insertUser(User user) {
        logger.info("Inseringo objeto User: " + user.toString());
        return this.userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        this.userRepository.findById(id).map(
                user -> {
                    logger.info("Deletanto objeto User: " + user.toString());
                    this.userRepository.delete(user);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    @Override
    public void updateUser(Long id, User newUser) {
        logger.info("Atualizando objeto User: " + newUser.toString());
        this.userRepository.findById(id).map(
                role -> {
                    newUser.setId(role.getId());
                    userRepository.save(newUser);
                    return Void.TYPE;
                }
        ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        logger.info("Pesquisando objeto User: " + id);
        return this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    @Override
    public Page<User> findPaginableUsers(Integer page, Integer pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(page, pageSize, sort);
        return userRepository.findAll(pageRequest);
    }
}
