package br.com.questions.controllers;

import br.com.questions.dto.AnswerDto;
import br.com.questions.entity.Answer;
import br.com.questions.service.AnswerService;
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
@RequestMapping("/answer")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AnswerController {

    @Autowired
    private AnswerService service;

    @ApiOperation(value = "Insere nova Resposta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Answer save(@RequestBody @Valid AnswerDto answerDto) {
        MDC.clear();
        MDC.put("Insert Answer: ", String.valueOf(UUID.randomUUID()));
        return service.insertAnswer(answerDto);
    }

    @ApiOperation(value = "Pesquisa Resposta por id")
    @GetMapping("{id}")
    public Answer findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @ApiOperation(value = "Pesquisa todas Respostas")
    @GetMapping
    public List<Answer> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllAnswer();
    }

    @ApiOperation(value = "Deleta Resposta")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete Answer: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteAnswer(id);
    }

    @ApiOperation(value = "Atualiza Resposta")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody AnswerDto answerDto) {
        MDC.clear();
        MDC.put("Update Answer: ", String.valueOf(UUID.randomUUID()));
        this.service.updateAnswer(id, answerDto);
    }
}
