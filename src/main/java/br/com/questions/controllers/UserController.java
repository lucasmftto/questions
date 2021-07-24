package br.com.questions.controllers;

import br.com.questions.entity.User;
import br.com.questions.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid User user) {
        MDC.clear();
        MDC.put("Insert User: ", String.valueOf(UUID.randomUUID()));
        return service.insertUser(user);
    }

    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete User: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteUser(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody User newUser) {
        MDC.clear();
        MDC.put("Update User: ", String.valueOf(UUID.randomUUID()));
        this.service.updateUser(id, newUser);
    }

    @GetMapping
    public Page<User> list(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
    ){
        return this.service.findPaginableUsers(page, pageSize);
    }
}
