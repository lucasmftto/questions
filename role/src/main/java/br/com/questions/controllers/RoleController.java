package br.com.questions.controllers;

import br.com.questions.entity.Role;
import br.com.questions.service.RoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping("/role")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RoleController {

    @Autowired
    private RoleService service;

    @ApiOperation(value = "Insere nova Roles")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role save(@RequestBody()  @Valid Role role) {
        MDC.clear();
        MDC.put("Insert Role: ", String.valueOf(UUID.randomUUID()));
        return service.insertRole(role);
    }

    @ApiOperation(value = "Pesquisa Roles por Id")
    @GetMapping("{id}")
    public Role findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @ApiOperation(value = "Pesquisa Roles")
    @GetMapping
    public List<Role> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllRoles();
    }

    @ApiOperation(value = "Delete Roles por Id")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete Rone: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteRole(id);
    }

    @ApiOperation(value = "Atualiza Roles por Id")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody Role newRole) {
        MDC.clear();
        MDC.put("Update Rone: ", String.valueOf(UUID.randomUUID()));
        this.service.updateRole(id, newRole);
    }

}
