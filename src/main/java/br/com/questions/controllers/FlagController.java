package br.com.questions.controllers;

import br.com.questions.entity.Flag;
import br.com.questions.service.FlagService;
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
@RequestMapping("/flag")
@RequiredArgsConstructor
@CrossOrigin("*")
public class FlagController {

    @Autowired
    private FlagService service;

    @ApiOperation(value = "Insere nova Flag")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flag save(@RequestBody @Valid Flag flag) {
        MDC.clear();
        MDC.put("Insert Flag: ", String.valueOf(UUID.randomUUID()));
        return service.insertFlag(flag);
    }

    @ApiOperation(value = "Consulta Flag por id")
    @GetMapping("{id}")
    public Flag findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @ApiOperation(value = "Consulta todas Flag")
    @GetMapping
    public List<Flag> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllFlag();
    }

    @ApiOperation(value = "Deleta Flag")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete Question: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteFlag(id);
    }

    @ApiOperation(value = "Atualiza Flag")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody Flag flag) {
        MDC.clear();
        MDC.put("Update Flag: ", String.valueOf(UUID.randomUUID()));
        this.service.updateFlag(id, flag);
    }
}
