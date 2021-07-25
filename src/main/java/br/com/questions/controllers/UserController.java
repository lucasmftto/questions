package br.com.questions.controllers;

import br.com.questions.dto.UserDto;
import br.com.questions.entity.User;
import br.com.questions.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Insere novo Usuario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody @Valid UserDto userDto) {
        MDC.clear();
        MDC.put("Insert User: ", String.valueOf(UUID.randomUUID()));
        return service.insertUser(userDto);
    }

    @ApiOperation(value = "Pesquida Usuario por Id")
    @GetMapping("{id}")
    public User findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @ApiOperation(value = "Delete Usuario por Id")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete User: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteUser(id);
    }

    @ApiOperation(value = "Atualiza Usuario")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        MDC.clear();
        MDC.put("Update User: ", String.valueOf(UUID.randomUUID()));
        this.service.updateUser(id, userDto);
    }

//    @ApiOperation(value = "Pesquida Paginada Usuario")
//    @GetMapping
//    public Page<User> list(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "size", defaultValue = "10") Integer pageSize
//    ){
//        return this.service.findPaginableUsers(page, pageSize);
//    }

    @ApiOperation(value = "Pesquida Paginada Usuario  com Filtro")
    @GetMapping
    public Page<User> listByNomeAndEmailAndBirthDate(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "email", required = false, defaultValue = "") String email,
            @RequestParam(value = "birthDate", required = false, defaultValue = "") LocalDate birthDate
    ){
        return this.service.findByNomeAndEmailAndBirthDate(page, pageSize, "%"+nome+"%", "%"+email+"%", birthDate);
    }
}
