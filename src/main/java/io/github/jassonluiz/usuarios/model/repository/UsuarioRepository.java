package io.github.jassonluiz.usuarios.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jassonluiz.usuarios.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
