package com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.repository;


import com.guilhermesantos.cadastro_usuario_testeunit.infrastructure.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);


}
