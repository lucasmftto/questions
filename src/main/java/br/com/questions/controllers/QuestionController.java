package br.com.questions.controllers;

import br.com.questions.dto.QuestionDto;
import br.com.questions.entity.Question;
import br.com.questions.service.QuestionService;
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
@RequestMapping("/question")
@RequiredArgsConstructor
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService service;

    @ApiOperation(value = "Insere nova Pergunta")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question save(@RequestBody @Valid QuestionDto question) {
        MDC.clear();
        MDC.put("Insert Question: ", String.valueOf(UUID.randomUUID()));
        return service.insertQuestion(question);
    }

    @ApiOperation(value = "Pesquida Pergunta por ID")
    @GetMapping("{id}")
    public Question findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @ApiOperation(value = "Pesquisa todas Perguntas")
    @GetMapping
    public List<Question> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllQuestion();
    }

    @ApiOperation(value = "Deleta Pergunta")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete Question: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteQuestion(id);
    }

    @ApiOperation(value = "Atualiza Pergunta")
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody QuestionDto questionDto) {
        MDC.clear();
        MDC.put("Update Question: ", String.valueOf(UUID.randomUUID()));
        this.service.updateQuestion(id, questionDto);
    }

    @ApiOperation(value = "Atualiza Pergunta Resolvida")
    @PutMapping("resolved/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateResolved(@PathVariable Long id, @Valid @RequestBody Boolean resolved) {
        MDC.clear();
        MDC.put("Update Question Resolvida: ", String.valueOf(UUID.randomUUID()));
        this.service.updateResolved(id, resolved);
    }
}
