package br.com.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.questions.exception.ApiErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@RestControllerAdvice
public class ApplicationControllerAdvice {

	Logger logger = LoggerFactory.getLogger(ApplicationControllerAdvice.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleValidationErros(MethodArgumentNotValidException ex) {
		logger.info("Error: " + ex);
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages =
		bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ApiErrors(messages);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String msgErro = ex.getReason();
		HttpStatus status = ex.getStatus();
		ApiErrors api =  new ApiErrors(msgErro);
		return new ResponseEntity(api, status);
		
	}
}
