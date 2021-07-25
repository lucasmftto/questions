package br.com.questions.service;

import br.com.questions.dto.UserDto;
import br.com.questions.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User insertUser(UserDto userDto);
    void deleteUser(Long id);
    void updateUser(Long id, UserDto userDto);
    List<User> findAllUsers();
    User findById(Long id);
    Page<User> findPaginableUsers(Integer page, Integer pageSize);
}
