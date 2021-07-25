package br.com.questions.service;

import br.com.questions.dto.UserDto;
import br.com.questions.entity.User;
import br.com.questions.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User insertUser(UserDto userDto) {
        logger.info("Inseringo objeto User: " + userDto.toString());
        User user = new User();

        user.setEnabled(userDto.getEnabled());
        user.setEmail(userDto.getEmail());
        user.setBirthDate(userDto.getBirthDate());
        user.setRole(this.roleService.findById(userDto.getRoleId()));
        user.setName(userDto.getName());

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
    public void updateUser(Long id, UserDto userDto) {
        logger.info("Atualizando objeto User: " + userDto.toString());
        this.userRepository.findById(id).map(
                user -> {
                    user.setRole(this.roleService.findById(userDto.getRoleId()));
                    user.setName(userDto.getName());
                    user.setEnabled(userDto.getEnabled());
                    user.setBirthDate(userDto.getBirthDate());
                    userRepository.save(user);
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

    @Override
    public Page<User> findByNomeAndEmailAndBirthDate(Integer page, Integer pageSize, String nome, String email, LocalDate birthDate) {
        Integer isBirthDate = 0;

        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.ASC, "id");

        if (birthDate == null){
            isBirthDate = 1;
        }

        return userRepository.findByNomeAndEmailAndBirthDate(pageable, nome, email,  isBirthDate, birthDate);
    }
}
