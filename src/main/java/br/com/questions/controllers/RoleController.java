package br.com.questions.controllers;

import br.com.questions.entity.Role;
import br.com.questions.service.RoleService;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Role save(@RequestBody @Valid Role role) {
        MDC.clear();
        MDC.put("Insert Role: ", String.valueOf(UUID.randomUUID()));
        return service.insertRole(role);
    }

    @GetMapping("{id}")
    public Role findById(@PathVariable Integer id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @GetMapping
    public List<Role> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllRoles();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        MDC.clear();
        MDC.put("Delete Rone: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteRole(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @Valid @RequestBody Role newRole) {
        MDC.clear();
        MDC.put("Update Rone: ", String.valueOf(UUID.randomUUID()));
        this.service.updateRole(id, newRole);
    }

}
