package io.github.jassonluiz.usuarios.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.jassonluiz.usuarios.enuns.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@Column(unique = true)
	@NotEmpty(message = "{campo.login.obrigatorio}")
	private String login;
	
	@NotEmpty(message = "{campo.senha.obrigatorio}")
	private String senha;
	
	@Email(message = "{campo.email.invalido}")
	private String email;
	
	@NotEmpty(message = "{campo.telefone.obrigatorio}")
	private String telefone;
	
	@Column(nullable = false, length = 11, unique = true)
	@NotNull(message = "{campo.cpf.obrigatorio}")
	@CPF(message = "{campo.cpf.invalido}")
	private String cpf;
	
	@Column(name = "data_nascimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@Column(name = "nome_mae")
	@NotEmpty(message = "{campo.nomeMae.obrigatorio}")
	private String nomeMae;
	
	private Status status;
	
	@Column(name = "data_inclusao", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataInclusao;
	
	@Column(name = "data_alteracao", updatable = true)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAlteracao;
	
	@PrePersist
	public void prePersist() {
		setDataInclusao(LocalDate.now());
		setDataAlteracao(LocalDate.now());
	}
}
