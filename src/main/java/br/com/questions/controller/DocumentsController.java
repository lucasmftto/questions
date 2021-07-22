package br.com.questions.controller;

import br.com.questions.entity.Customer;
import br.com.questions.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/docs")
@RequiredArgsConstructor
@CrossOrigin("*")
public class DocumentsController {

    @Autowired
    private CustomerRepository repository;

    Logger logger = LoggerFactory.getLogger(DocumentsController.class);


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Customer customer) {
        MDC.put("Object queue and topic: ", String.valueOf(UUID.randomUUID()));


        MDC.clear();
    }
}
