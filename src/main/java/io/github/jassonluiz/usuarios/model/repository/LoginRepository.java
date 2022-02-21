package io.github.jassonluiz.usuarios.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jassonluiz.usuarios.model.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{

}
