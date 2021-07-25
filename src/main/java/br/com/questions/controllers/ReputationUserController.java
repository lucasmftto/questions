package br.com.questions.controllers;

import br.com.questions.dto.ReputationUserDto;
import br.com.questions.entity.ReputationUser;
import br.com.questions.service.ReputationUserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reputationUser")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReputationUserController {

    @Autowired
    private ReputationUserService service;

    @ApiOperation(value = "Insere nova reputacao do usuario")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReputationUser save(@RequestBody @Valid ReputationUserDto reputationUserDto) {
        MDC.clear();
        MDC.put("Insert ReputationUser: ", String.valueOf(UUID.randomUUID()));
        return service.insertReputationUser(reputationUserDto);
    }

    @ApiOperation(value = "Pesquisa reputacao do usuario por id")
    @GetMapping("{id}")
    public ReputationUser findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findByIdReputationUser(id);
    }

    @ApiOperation(value = "Pesquisa todas Reputacoes")
    @GetMapping
    public List<ReputationUser> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllReputationUser();
    }

    @ApiOperation(value = "Deleta Reputacao")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete ReputationUser: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteReputationUser(id);
    }

    @ApiOperation(value = "Atualiza Reputacao")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody ReputationUserDto reputationUserDto) {
        MDC.clear();
        MDC.put("Update ReputationUser: ", String.valueOf(UUID.randomUUID()));
        this.service.updateReputationUser(id, reputationUserDto);
    }
}
