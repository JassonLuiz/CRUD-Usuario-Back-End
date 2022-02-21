package io.github.jassonluiz.usuarios.rest.exception;

import java.util.Arrays;
import java.util.List;


import lombok.Getter;

public class ApiErros {

	@Getter
	private List<String> erros;
	
	public ApiErros(List<String> erros) {
		this.erros = erros;
	}
	
	public ApiErros(String mensagem) {
		this.erros = Arrays.asList(mensagem);
	}
}
