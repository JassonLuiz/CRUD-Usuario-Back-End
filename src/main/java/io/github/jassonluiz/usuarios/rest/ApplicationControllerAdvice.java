package io.github.jassonluiz.usuarios.rest;

import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import io.github.jassonluiz.usuarios.rest.exception.ApiErros;

@RestControllerAdvice
public class ApplicationControllerAdvice {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErros handleValidationErros( MethodArgumentNotValidException ex ) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> mensagens = bindingResult.getAllErrors()
				.stream()
				.map(erros -> erros.getDefaultMessage())
				.collect(Collectors.toList());
		return new ApiErros(mensagens);
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity handleResponseStatusException(ResponseStatusException ex) {
		String mensagemErro = ex.getMessage();
		HttpStatus codigoStatus = ex.getStatus();
		ApiErros apiErros = new ApiErros(mensagemErro);
		return new ResponseEntity(apiErros, codigoStatus);
	}
}
