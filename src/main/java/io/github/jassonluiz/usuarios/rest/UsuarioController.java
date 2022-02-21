package io.github.jassonluiz.usuarios.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.jassonluiz.usuarios.model.entity.Usuario;
import io.github.jassonluiz.usuarios.model.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin("http://localhost:4200")
public class UsuarioController {

	public final UsuarioRepository repository;
	
	public UsuarioController(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping
	public List<Usuario> listaTodos(){
		return repository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario salvar(@RequestBody @Valid Usuario usuario) {
		return repository.save(usuario);
	}
	
	@GetMapping("{id}")
	public Usuario findById(@PathVariable Integer id) {
		return repository.findById(id)
				.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado!"));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		repository.findById(id)
					.map(usuario -> {
						repository.delete(usuario);
						return Void.TYPE;
					})
					.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado!"));
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaTodos() {
		 repository.deleteAll();
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualiza(@PathVariable Integer id, @RequestBody @Valid Usuario usuarioAtualizado) {
		repository.findById(id)
					.map(usuario -> {
						usuario.setNome(usuarioAtualizado.getNome());
						usuario.setLogin(usuarioAtualizado.getLogin());
						usuario.setSenha(usuarioAtualizado.getSenha());
						usuario.setEmail(usuarioAtualizado.getEmail());
						usuario.setTelefone(usuarioAtualizado.getTelefone());
						usuario.setCpf(usuarioAtualizado.getCpf());
						usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
						usuario.setNomeMae(usuarioAtualizado.getNomeMae());
						usuario.setStatus(usuarioAtualizado.getStatus());
						
						return repository.save(usuario);
					})
					.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado!"));
	}

}
