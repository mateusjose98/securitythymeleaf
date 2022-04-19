package com.example.securitythymeleaf.repositories;

import com.example.securitythymeleaf.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
}
