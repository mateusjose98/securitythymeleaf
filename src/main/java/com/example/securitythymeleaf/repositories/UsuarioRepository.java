package com.example.securitythymeleaf.repositories;

import com.example.securitythymeleaf.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
