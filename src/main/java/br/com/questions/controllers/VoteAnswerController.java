package br.com.questions.controllers;

import br.com.questions.dto.VoteAnswerDto;
import br.com.questions.entity.VoteAnswer;
import br.com.questions.service.VoteAnswerService;
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
@RequestMapping("/voteAnswer")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VoteAnswerController {

    @Autowired
    private VoteAnswerService service;

    @ApiOperation(value = "Insere novo Voto Resposta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VoteAnswer save(@RequestBody @Valid VoteAnswerDto voteAnswerDto) {
        MDC.clear();
        MDC.put("Insert VoteAnswer: ", String.valueOf(UUID.randomUUID()));
        return this.service.insertVoteAnswer(voteAnswerDto);
    }

    @ApiOperation(value = "Pesquisa Voto Resposta por id")
    @GetMapping("{id}")
    public VoteAnswer findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @ApiOperation(value = "Pesquisa todas Voto Resposta")
    @GetMapping
    public List<VoteAnswer> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllVoteAnswer();
    }

    @ApiOperation(value = "Deleta Voto Resposta")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete Answer: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteAVoteAnswer(id);
    }

    @ApiOperation(value = "Atualiza Voto Resposta")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody VoteAnswerDto voteAnswerDto) {
        MDC.clear();
        MDC.put("Update Answer: ", String.valueOf(UUID.randomUUID()));
        this.service.updateVoteAnswer(id, voteAnswerDto);
    }
}
