package br.com.questions.controllers;

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
    public Question save(@RequestBody @Valid Question question) {
        MDC.clear();
        MDC.put("Insert Role: ", String.valueOf(UUID.randomUUID()));
        return service.insertQuestion(question);
    }

    @GetMapping("{id}")
    public Question findById(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Find One: ", String.valueOf(UUID.randomUUID()));
        return service.findById(id);
    }

    @GetMapping
    public List<Question> findAll(){
        MDC.clear();
        MDC.put("Find All: ", String.valueOf(UUID.randomUUID()));
        return this.service.findAllQuestion();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        MDC.clear();
        MDC.put("Delete Question: ", String.valueOf(UUID.randomUUID()));
        this.service.deleteQuestion(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @Valid @RequestBody Question newQuestion) {
        MDC.clear();
        MDC.put("Update Rone: ", String.valueOf(UUID.randomUUID()));
        this.service.updateQuestion(id, newQuestion);
    }
}
