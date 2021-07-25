package br.com.questions.controllers;

import br.com.questions.dto.ForumDto;
import br.com.questions.entity.Answer;
import br.com.questions.entity.Question;
import br.com.questions.entity.User;
import br.com.questions.service.AnswerService;
import br.com.questions.service.QuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ForumController {

    @Autowired
    private QuestionService questionservice;

    @Autowired
    private AnswerService answerService;

    @ApiOperation(value = "Pesquida Paginada Perguntas")
    @GetMapping("/questions")
    public Page<Question> listByAnswerAndNomeAndEmai(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pergunta", required = false, defaultValue = "") String pergunta,
            @RequestParam(value = "nomeUsuario", required = false, defaultValue = "") String nomeUsuario,
            @RequestParam(value = "email", required = false, defaultValue = "") String email
    ){
        return this.questionservice.findByAnswerAndNomeAndEmail(page, pageSize, "%"+pergunta+"%", "%"+nomeUsuario+"%", "%"+email+"%");
    }

    @ApiOperation(value = "Pesquida Paginada Respostas")
    @GetMapping("/answer")
    public Page<Object[]> listByNomeAndEmailAndBirthDate(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pergunta", required = false, defaultValue = "") String pergunta,
            @RequestParam(value = "nomeUsuario", required = false, defaultValue = "") String nomeUsuario,
            @RequestParam(value = "email", required = false, defaultValue = "") String email
    ){
        return this.answerService.findByAnswerAndNomeAndEmail(page, pageSize, "%"+pergunta+"%", "%"+nomeUsuario+"%", "%"+email+"%");
    }

}
