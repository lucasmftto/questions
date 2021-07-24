package br.com.questions.service;

import br.com.questions.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public User insertUser(User user);
    public void deleteUser(Long id);
    public void updateUser(Long id, User newUser);
    public List<User> findAllUsers();
    public User findById(Long id);
    public Page<User> findPaginableUsers(Integer page, Integer pageSize);
}
